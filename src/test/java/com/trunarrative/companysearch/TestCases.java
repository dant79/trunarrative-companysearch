package com.trunarrative.companysearch;

import com.trunarrative.companysearch.client.companysearch.CompanyResult;
import com.trunarrative.companysearch.client.officersearch.DateOfBirth;
import com.trunarrative.companysearch.client.officersearch.OfficerResult;
import com.trunarrative.companysearch.model.Address;

import java.time.LocalDate;

public class TestCases {

    public static CompanyResult aCompanyResult(String prefix, LocalDate date){

        return new CompanyResult()
                .setCompanyNumber(prefix + "1")
                .setCompanyStatus("active")
                .setCompanyType(prefix + "ltd")
                .setAddressSnippet(prefix + "address snippet")
                .setDateOfCreation(date)
                .setDescription(prefix + "description")
                .setKind(prefix + "kind")
                .setTitle(prefix + "title")
                .setAddress(anAddress(prefix));

    }

    public static OfficerResult anOfficer(String prefix, LocalDate date) {
        return new OfficerResult()
                .setAddress(anAddress(prefix))
                .setAppointedOn(date)
                .setName(prefix + "name")
                .setNationality(prefix + "nationality")
                .setOfficerRole(prefix + "officerRole")
                .setCountryOfResidence(prefix + "countryOfResidence")
                .setDateOfBirth(new DateOfBirth()
                        .setMonth(date.getMonthValue())
                        .setYear(date.getYear()))
                .setOccupation(prefix + "occupation");
    }

    public static Address anAddress(String prefix) {
        return new Address()
                .setPremises(prefix + "premises")
                .setAddressLine1(prefix + "addressLine1")
                .setLocality(prefix + "locality")
                .setPostcode(prefix + "postcode")
                .setCountry(prefix + "country");
    }
}
