package com.trunarrative.companysearch.transformer;

import com.trunarrative.companysearch.client.companysearch.CompanyResult;
import com.trunarrative.companysearch.model.Company;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static com.trunarrative.companysearch.TestData.*;


public class CompanyTransformerTest {

    private final CompanyTransformer companyTransformer = new CompanyTransformer();


    @Test
    public void shouldMapCompanyFromResult() {

        LocalDate date = LocalDate.now();

        CompanyResult companyResult = aCompanyResult("", date);

        Company company = companyTransformer.map(companyResult);
        assertThat(company.getCompanyNumber()).isEqualTo(COMPANY_NUMBER);
        assertThat(company.getCompanyStatus()).isEqualTo(COMPANY_STATUS);
        assertThat(company.getCompanyType()).isEqualTo(COMPANY_TYPE);
        assertThat(company.getDateOfCreation()).isEqualTo(date);
        assertThat(company.getTitle()).isEqualTo(COMPANY_TITLE);
        assertThat(company.getAddress().getPremises()).isEqualTo(ADDRESS_PREMISES);
        assertThat(company.getAddress().getAddressLine1()).isEqualTo(ADDRESS_LINE_1);
        assertThat(company.getAddress().getLocality()).isEqualTo(ADDRESS_LOCALITY);
        assertThat(company.getAddress().getPostcode()).isEqualTo(ADDRESS_POSTCODE);
        assertThat(company.getAddress().getCountry()).isEqualTo(ADDRESS_COUNTRY);

    }
}
