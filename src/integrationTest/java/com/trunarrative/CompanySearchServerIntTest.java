package com.trunarrative;

import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.trunarrative.companysearch.CompanySearchApplication;
import com.trunarrative.companysearch.model.SearchCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CompanySearchApplication.class, TestUtils.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({
        @ConfigureWireMock(name = "company-search-service", property = "user-client.url", port = 8090)
})
@AutoConfigureMockMvc
@ActiveProfiles("ci")
public class CompanySearchServerIntTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestUtils testUtils;

    private static final String apiHeaderKey = "x-api-key";
    private static final String activeOnlyHeaderKey = "active-only";
    private static final String apiKey = "testApiKey";
    private static final String activeOnly = "false";


    @Test
    void shouldReturn404WhenNoCompanyWithNumber() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria().setCompanyNumber("00000");

        mvc.perform(post("/companysearch")
                        .header(apiHeaderKey, apiKey)
                        .param(activeOnlyHeaderKey, activeOnly)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUtils.toNormalisedJson(searchCriteria)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn422WhenNoCriteriaSet() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria();

        mvc.perform(post("/companysearch")
                        .header(apiHeaderKey, apiKey)
                        .param(activeOnlyHeaderKey, activeOnly)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUtils.toNormalisedJson(searchCriteria)))
                .andExpect(status().isUnprocessableEntity());

    }
}
