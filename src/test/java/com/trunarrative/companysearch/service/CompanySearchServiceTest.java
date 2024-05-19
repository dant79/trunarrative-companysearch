package com.trunarrative.companysearch.service;

import com.trunarrative.companysearch.client.TruProxyApi;
import com.trunarrative.companysearch.client.companysearch.CompanyList;
import com.trunarrative.companysearch.client.officersearch.OfficerList;
import com.trunarrative.companysearch.exception.CompanyNotFoundException;
import com.trunarrative.companysearch.exception.InvalidRequestException;
import com.trunarrative.companysearch.model.SearchCriteria;
import com.trunarrative.companysearch.model.SearchResults;
import com.trunarrative.companysearch.transformer.CompanyTransformer;
import com.trunarrative.companysearch.transformer.OfficerTransformer;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.trunarrative.companysearch.TestCases.aCompanyResult;
import static com.trunarrative.companysearch.TestCases.anOfficer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanySearchServiceTest {

    private static final String apiHeaderKey = "x-api-key";
    private static final String activeOnlyHeaderKey = "active-only";
    private static final String apiKey = "testApiKey";
    private static final String activeOnly = "false";

    @Mock
    private TruProxyApi truProxyApi;

    @Spy
    private CompanyTransformer companyTransformer;

    @Spy
    private OfficerTransformer officerTransformer;

    @InjectMocks
    private CompanySearchService companySearchService;

    @Test
    public void shouldThrowCompanyNotFoundWhenEmpty() {
        CompanyList companyList = new CompanyList();

        when(truProxyApi.companySearch(eq(apiKey), anyString())).thenReturn(companyList);

        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("000000");

        assertThrows(CompanyNotFoundException.class, () -> companySearchService.searchCompanies(apiKey, false, searchCriteria));
    }

    @Test
    public void shouldThrowInvalidRequestWhenCriteriaEmpty() {
        SearchCriteria searchCriteria = new SearchCriteria();

        assertThrows(InvalidRequestException.class, () -> companySearchService.searchCompanies(apiKey, false, searchCriteria));
    }

    @Test
    public void shouldReturnActiveCompaniesOnlyWhenParamSet() {
        LocalDate date = LocalDate.now();

        CompanyList companyList = new CompanyList()
                .setCompanyResults(Lists.newArrayList(
                        aCompanyResult("1", date)
                                .setCompanyStatus("dissolved"),
                        aCompanyResult("2", date)
                ));

        when(truProxyApi.companySearch(eq(apiKey), anyString())).thenReturn(companyList);

        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("000000");
        SearchResults results = companySearchService.searchCompanies(apiKey, true, searchCriteria);

        assertThat(results.getItems()).hasSize(1);
    }

    @Test
    public void shouldReturnActiveOfficersOnly() {

        LocalDate date = LocalDate.now();

        CompanyList companyList = new CompanyList()
                .setCompanyResults(Lists.newArrayList(
                        aCompanyResult("1", date)
                ));

        OfficerList officerList = new OfficerList()
                .setOfficers(Lists.newArrayList(
                        anOfficer("1", date),
                        anOfficer("2", date).setResignedOn(date)
                ));

        when(truProxyApi.companySearch(eq(apiKey), anyString())).thenReturn(companyList);
        when(truProxyApi.officerSearch(eq(apiKey), anyString())).thenReturn(officerList);


        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("000000");
        SearchResults results = companySearchService.searchCompanies(apiKey, true, searchCriteria);

        assertThat(results.getItems()).hasSize(1);
        assertThat(results.getItems().get(0).getOfficers()).hasSize(1);
        assertThat(results.getItems().get(0).getOfficers().get(0).getName()).isEqualTo("1name");
    }


}
