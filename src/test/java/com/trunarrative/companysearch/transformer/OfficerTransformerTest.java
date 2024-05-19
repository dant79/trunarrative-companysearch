package com.trunarrative.companysearch.transformer;

import com.trunarrative.companysearch.client.officersearch.OfficerResult;
import com.trunarrative.companysearch.model.Officer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.trunarrative.companysearch.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OfficerTransformerTest {

    private final OfficerTransformer officerTransformer = new OfficerTransformer();


    @Test
    public void shouldMapOfficerFromResult() {

        LocalDate date = LocalDate.now();

        OfficerResult officerResult = anOfficer("", date);

        Officer officer = officerTransformer.map(officerResult);
        assertThat(officer.getName()).isEqualTo(OFFICER_NAME);
        assertThat(officer.getOfficerRole()).isEqualTo(OFFICER_ROLE);
        assertThat(officer.getAppointedOn()).isEqualTo(date);
        assertThat(officer.getAddress().getPremises()).isEqualTo(ADDRESS_PREMISES);
        assertThat(officer.getAddress().getAddressLine1()).isEqualTo(ADDRESS_LINE_1);
        assertThat(officer.getAddress().getLocality()).isEqualTo(ADDRESS_LOCALITY);
        assertThat(officer.getAddress().getPostcode()).isEqualTo(ADDRESS_POSTCODE);
        assertThat(officer.getAddress().getCountry()).isEqualTo(ADDRESS_COUNTRY);

    }
}
