package com.trunarrative;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import com.trunarrative.companysearch.CompanySearchApplication;
import com.trunarrative.companysearch.model.SearchCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CompanySearchApplication.class, TestUtils.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({
        @ConfigureWireMock(name = "company-search-service", property = "user-client.url", port = 8090)
})
@AutoConfigureMockMvc
@ActiveProfiles("ci")
public class CompanySearchServerIntTest {

    @InjectWireMock("company-search-service")
    private WireMockServer wiremock;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestUtils testUtils;

    private static final String API_HEADER_KEY = "x-api-key";
    private static final String ACTIVE_ONLY_HEADER_KEY = "active-only";
    private static final String API_KEY = "testApiKey";
    private static final String NOT_ACTIVE_ONLY = "false";


    @Test
    void shouldReturn404WhenNoCompanyWithNumber() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("00000");

        mvc.perform(post("/companysearch")
                        .header(API_HEADER_KEY, API_KEY)
                        .param(ACTIVE_ONLY_HEADER_KEY, NOT_ACTIVE_ONLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUtils.toNormalisedJson(searchCriteria)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn422WhenNoCriteriaSet() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria();

        mvc.perform(post("/companysearch")
                        .header(API_HEADER_KEY, API_KEY)
                        .param(ACTIVE_ONLY_HEADER_KEY, NOT_ACTIVE_ONLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUtils.toNormalisedJson(searchCriteria)))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    void shouldReturn503WhenTruApiErrors() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyName("ERROR");

        wiremock.stubFor(get("/Search?Query=ERROR").willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("Service Unavailable")
                .withStatus(503)
        ));

        mvc.perform(post("/companysearch")
                        .header(API_HEADER_KEY, API_KEY)
                        .param(ACTIVE_ONLY_HEADER_KEY, NOT_ACTIVE_ONLY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUtils.toNormalisedJson(searchCriteria)))
                .andExpect(status().isServiceUnavailable());

    }
}
