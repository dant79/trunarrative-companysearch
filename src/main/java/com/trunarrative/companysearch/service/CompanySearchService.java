package com.trunarrative.companysearch.service;

import com.trunarrative.companysearch.client.TruProxyApi;
import com.trunarrative.companysearch.client.companysearch.CompanyList;
import com.trunarrative.companysearch.client.companysearch.CompanyResult;
import com.trunarrative.companysearch.client.officersearch.OfficerList;
import com.trunarrative.companysearch.exception.InvalidRequestException;
import com.trunarrative.companysearch.model.Company;
import com.trunarrative.companysearch.model.Officer;
import com.trunarrative.companysearch.model.SearchCriteria;
import com.trunarrative.companysearch.model.SearchResults;
import com.trunarrative.companysearch.transformer.CompanyTransformer;
import com.trunarrative.companysearch.transformer.OfficerTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CompanySearchService {

    private final Logger logger = LoggerFactory.getLogger(CompanySearchService.class);

    private final TruProxyApi truProxyApi;
    private final CompanyTransformer companyTransformer;
    private final OfficerTransformer officerTransformer;

    public CompanySearchService(TruProxyApi truProxyApi, CompanyTransformer companyTransformer, OfficerTransformer officerTransformer) {
        this.truProxyApi = truProxyApi;
        this.companyTransformer = companyTransformer;
        this.officerTransformer = officerTransformer;
    }

    @PostMapping(path = "/companysearch", consumes = "application/json", produces = "application/json")
    public SearchResults searchCompanies(
            @RequestHeader(name = "x-api-key") String apiKey,
            @RequestParam(name = "active-only") boolean activeOnly,
            @RequestBody SearchCriteria searchCriteria
            ) {

        logger.debug("API Key: " + apiKey);
        logger.debug("Active Only: " + activeOnly);
        logger.debug("SearchCriteria: " + searchCriteria);

        CompanyList companyList = truProxyApi.companySearch(apiKey, searchString(searchCriteria));

        if(companyList == null || companyList.getCompanyResults() == null) {
            return buildResults(null);
        }

        logger.debug("Company List:" + companyList);

        List<Company> companies = companyList.getCompanyResults().stream()
                .filter(companyResult -> filterActiveOnly(companyResult, activeOnly))
                .map( companyResult -> mapCompany(companyResult, apiKey))
                .collect(Collectors.toList());

        return buildResults(companies);
    }

    private String searchString(SearchCriteria searchCriteria) {
        String companyNumber = searchCriteria.getCompanyNumber();
        String companyName = searchCriteria.getCompanyName();

        if(companyNumber != null && !companyNumber.isBlank()) {
            return companyNumber;
        } else if (companyName != null && !companyName.isBlank()) {
            return companyName;
        } else {
            throw new InvalidRequestException("Request must contain either a company name or number");
        }

    }

    private boolean filterActiveOnly(CompanyResult companyResult, boolean activeOnly) {
        if(activeOnly && !companyResult.getCompanyStatus().equals("active")) {
            return false;
        }
        return true;
    }

    private Company mapCompany(CompanyResult companyResult, String apiKey) {
        Company company = companyTransformer.map(companyResult);
        company.setOfficers(findOfficers(company.getCompanyNumber(), apiKey));
        return company;
    }

    private List<Officer> findOfficers(String companyNumber, String apiKey) {

        OfficerList officerList = truProxyApi.officerSearch(apiKey, companyNumber);
        if(officerList == null || officerList.getOfficers() == null) {
            return new ArrayList<>();
        }

        logger.debug("Officer List:" + officerList);

        return officerList.getOfficers().stream()
                .filter(officerResult -> officerResult.getResignedOn() != null)
                .map(officerTransformer::map)
                .collect(Collectors.toList());
    }

    private SearchResults buildResults(List<Company> companies) {
        if(companies == null || companies.isEmpty()) {
            return new SearchResults(0, new ArrayList<>());
        }

        return new SearchResults(companies.size(), companies);
    }
}