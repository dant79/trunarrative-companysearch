package com.trunarrative;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import com.trunarrative.companysearch.CompanySearchApplication;
import com.trunarrative.companysearch.model.SearchCriteria;
import com.trunarrative.companysearch.model.SearchResults;
import com.trunarrative.companysearch.service.CompanySearchService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CompanySearchApplication.class, TestUtils.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({
        @ConfigureWireMock(name = "company-search-service", property = "user-client.url", port = 8090)
})
@AutoConfigureMockMvc
@ActiveProfiles("ci")
public class CompanyResultSearchIntTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyResultSearchIntTest.class);

    @InjectWireMock("company-search-service")
    private WireMockServer wiremock;

    private static final String apiKey = "testApiKey";

    @Autowired
    private CompanySearchService companySearchService;

    @Autowired
    private TestUtils testUtils;


    @Test
    void shouldReturnBBCResultsWhenSearchByCompanyNameBBC() throws IOException {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyName("BBC LIMITED");

        SearchResults results = companySearchService.searchCompanies(apiKey, false, searchCriteria);
        assertThat(results.getTotalResults()).isEqualTo(4);

        LOGGER.error("Search Results: " + testUtils.toNormalisedJson(results));

        testUtils.assertJsonMatchesResource(results, "/response/companyNameSearchResponse.json");
    }

    @Test
    void shouldSearchByCompanyNumberWhenProvided() throws IOException {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("06500244").setCompanyName("BBC LIMITED");

        SearchResults results = companySearchService.searchCompanies(apiKey, false, searchCriteria);
        assertThat(results.getTotalResults()).isEqualTo(1);

        LOGGER.error("Search Results: " + testUtils.toNormalisedJson(results));

        testUtils.assertJsonMatchesResource(results, "/response/companyNumberSearchResponse.json");
    }

    @Test
    void shouldOnlyReturnActiveCompaniesWhenActiveOnly() throws IOException {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyName("BBC LIMITED");

        SearchResults results = companySearchService.searchCompanies(apiKey, true, searchCriteria);
        assertThat(results.getTotalResults()).isEqualTo(2);

        LOGGER.error("Search Results: " + testUtils.toNormalisedJson(results));

        testUtils.assertJsonMatchesResource(results, "/response/companyNameSearchActiveOnlyResponse.json");
    }


}
