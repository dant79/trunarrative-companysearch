package com.trunarrative.companysearch;

import com.trunarrative.companysearch.client.companysearch.CompanyResult;
import com.trunarrative.companysearch.client.officersearch.DateOfBirth;
import com.trunarrative.companysearch.client.officersearch.OfficerResult;
import com.trunarrative.companysearch.model.Address;

import java.time.LocalDate;

public class TestData {

    public static final String COMPANY_NUMBER = "1";
    public static final String COMPANY_STATUS = "active";
    public static final String COMPANY_TYPE = "type";
    public static final String COMPANY_ADDRESS_SNIPPET = "address snippet";
    public static final String COMPANY_DESCRIPTION = "description";
    public static final String COMPANY_KIND = "kind";
    public static final String COMPANY_TITLE = "title";
    public static final String OFFICER_NAME = "name";
    public static final String OFFICER_NATIONALITY = "nationality";
    public static final String OFFICER_ROLE = "officerRole";
    public static final String OFFICER_COUNTRY_OF_RESIDENCE = "countryOfResidence";
    public static final String OFFICER_OCCUPATION = "occupation";
    public static final String ADDRESS_PREMISES = "premises";
    public static final String ADDRESS_LINE_1 = "addressLine1";
    public static final String ADDRESS_LOCALITY = "locality";
    public static final String ADDRESS_POSTCODE = "postcode";
    public static final String ADDRESS_COUNTRY = "country";

    public static CompanyResult aCompanyResult(String prefix, LocalDate date){

        return new CompanyResult()
                .setCompanyNumber(prefix + COMPANY_NUMBER)
                .setCompanyStatus(COMPANY_STATUS)
                .setCompanyType(prefix + COMPANY_TYPE)
                .setAddressSnippet(prefix + COMPANY_ADDRESS_SNIPPET)
                .setDateOfCreation(date)
                .setDescription(prefix + COMPANY_DESCRIPTION)
                .setKind(prefix + COMPANY_KIND)
                .setTitle(prefix + COMPANY_TITLE)
                .setAddress(anAddress(prefix));

    }

    public static OfficerResult anOfficer(String prefix, LocalDate date) {
        return new OfficerResult()
                .setAddress(anAddress(prefix))
                .setAppointedOn(date)
                .setName(prefix + OFFICER_NAME)
                .setNationality(prefix + OFFICER_NATIONALITY)
                .setOfficerRole(prefix + OFFICER_ROLE)
                .setCountryOfResidence(prefix + OFFICER_COUNTRY_OF_RESIDENCE)
                .setDateOfBirth(new DateOfBirth()
                        .setMonth(date.getMonthValue())
                        .setYear(date.getYear()))
                .setOccupation(prefix + OFFICER_OCCUPATION);
    }

    public static Address anAddress(String prefix) {
        return new Address()
                .setPremises(prefix + ADDRESS_PREMISES)
                .setAddressLine1(prefix + ADDRESS_LINE_1)
                .setLocality(prefix + ADDRESS_LOCALITY)
                .setPostcode(prefix + ADDRESS_POSTCODE)
                .setCountry(prefix + ADDRESS_COUNTRY);
    }
}
