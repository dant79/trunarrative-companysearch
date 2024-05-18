package com.trunarrative.companysearch.client;

import com.trunarrative.companysearch.client.companysearch.CompanyList;
import com.trunarrative.companysearch.client.officersearch.OfficerList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "trunarrative-api", url = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1/",
        configuration = FeignClientConfiguration.class)
public interface TruProxyApi {

    @RequestMapping(method = RequestMethod.GET, value = "/Search")
    CompanyList companySearch(
            @RequestHeader("x-api-key") String apiKey,
            @RequestParam(name = "Query") String searchTerm);

    @RequestMapping(method = RequestMethod.GET, value = "/Officers")
    OfficerList officerSearch(
            @RequestHeader("x-api-key") String apiKey,
            @RequestParam("CompanyNumber") String companyNumber);
}
