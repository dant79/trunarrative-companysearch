package com.trunarrative.companysearch.transformer;

import com.trunarrative.companysearch.client.companysearch.CompanyResult;
import com.trunarrative.companysearch.model.Address;
import com.trunarrative.companysearch.model.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static com.trunarrative.companysearch.TestCases.*;

import java.time.LocalDate;

public class CompanyTransformerTest {

    private final CompanyTransformer companyTransformer = new CompanyTransformer();


    @Test
    public void shouldMapCompanyFromResult() {

        LocalDate date = LocalDate.now();

        CompanyResult companyResult = aCompanyResult("", date);

        Company company = companyTransformer.map(companyResult);
        assertThat(company.getCompanyNumber()).isEqualTo("1");
        assertThat(company.getCompanyStatus()).isEqualTo("active");
        assertThat(company.getCompanyType()).isEqualTo("ltd");
        assertThat(company.getDateOfCreation()).isEqualTo(date);
        assertThat(company.getTitle()).isEqualTo("title");
        assertThat(company.getAddress().getPremises()).isEqualTo("premises");
        assertThat(company.getAddress().getAddressLine1()).isEqualTo("addressLine1");
        assertThat(company.getAddress().getLocality()).isEqualTo("locality");
        assertThat(company.getAddress().getPostcode()).isEqualTo("postcode");
        assertThat(company.getAddress().getCountry()).isEqualTo("country");

    }
}
