package com.trunarrative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = CompanySearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({
        @ConfigureWireMock(name = "company-search-service", property = "user-client.url", port = 8090)
})
@ActiveProfiles("ci")
public class CompanyResultSearchIntTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyResultSearchIntTest.class);

    @InjectWireMock("company-search-service")
    private WireMockServer wiremock;

    @Value("${user-client.url}")
    private String wiremockUrl;

    private static final String apiKey = "testApiKey";

    @Autowired
    private CompanySearchService companySearchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnBBCResultsWhenSearchByCompanyNameBBC() throws URISyntaxException, IOException {
//        wiremock.stubFor(get("/Search?Query=BBC%20LIMITED").willReturn(aResponse()
//                .withHeader("Content-Type", "application/json")
//                .withBody(getStringFromFile("/mappings/validCompanyResponse.json"))
//        ));
//
//        wiremock.stubFor(get("/Officers?CompanyNumber=06500244").willReturn(aResponse()
//                .withHeader("Content-Type", "application/json")
//                .withBody(getStringFromFile("/mappings/validOfficer06500244Response.json"))
//        ));

        SearchCriteria searchCriteria = new SearchCriteria().setCompanyName("BBC LIMITED");

        SearchResults results = companySearchService.searchCompanies(apiKey, false, searchCriteria);
        assertThat(results.getTotalResults()).isEqualTo(4);

        LOGGER.error("Search Results: " + toNormalisedJson(results));

        assertJsonMatchesResource(results, "/response/companyNameSearchResponse.json");
    }

    @Test
    void shouldSearchByCompanyNumberWhenProvided() throws IOException {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("06500244").setCompanyName("BBC LIMITED");

        SearchResults results = companySearchService.searchCompanies(apiKey, false, searchCriteria);
        assertThat(results.getTotalResults()).isEqualTo(1);

        LOGGER.error("Search Results: " + toNormalisedJson(results));

        assertJsonMatchesResource(results, "/response/companyNumberSearchResponse.json");
    }
    @Test
    void shouldOnlyReturnActiveCompaniesWhenActiveOnly() throws IOException {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyName("BBC LIMITED");

        SearchResults results = companySearchService.searchCompanies(apiKey, true, searchCriteria);
        assertThat(results.getTotalResults()).isEqualTo(2);

        LOGGER.error("Search Results: " + toNormalisedJson(results));

        assertJsonMatchesResource(results, "/response/companyNameSearchActiveOnlyResponse.json");
    }

    private String getStringFromFile(String fileName) throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(
                Paths.get(CompanyResultSearchIntTest.class.getResource(fileName).toURI())),
                StandardCharsets.UTF_8);
    }

    private <T> T assertJsonMatchesResource(T object, String resourceName) throws IOException {
        assertThat(toNormalisedJson(object)).isEqualTo(toNormalisedJson(resourceToObject(object.getClass(), resourceName)));
        return object;
    }

    private <T> T resourceToObject(Class<T> expectedType, String resourceName) throws IOException {
        return objectMapper.readValue(getClass().getResource(resourceName), expectedType);
    }

    private String toNormalisedJson(Object value) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    }
}
