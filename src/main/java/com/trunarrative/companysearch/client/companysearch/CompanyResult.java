package com.trunarrative.companysearch.client.companysearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trunarrative.companysearch.model.Address;

import java.time.LocalDate;
import java.util.Objects;

public class CompanyResult {

    @JsonProperty("company_status")
    private String companyStatus;
    @JsonProperty("address_snippet")
    private String addressSnippet;
    @JsonProperty("date_of_creation")
    private LocalDate dateOfCreation;
    private String description;
    @JsonProperty("company_number")
    private String companyNumber;
    private String title;
    @JsonProperty("company_type")
    private String companyType;
    private Address address;
    private String kind;

    public String getCompanyStatus() {
        return companyStatus;
    }

    public CompanyResult setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
        return this;
    }

    public String getAddressSnippet() {
        return addressSnippet;
    }

    public CompanyResult setAddressSnippet(String addressSnippet) {
        this.addressSnippet = addressSnippet;
        return this;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public CompanyResult setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CompanyResult setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public CompanyResult setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CompanyResult setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCompanyType() {
        return companyType;
    }

    public CompanyResult setCompanyType(String companyType) {
        this.companyType = companyType;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public CompanyResult setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public CompanyResult setKind(String kind) {
        this.kind = kind;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyResult company = (CompanyResult) o;
        return Objects.equals(companyStatus, company.companyStatus) && Objects.equals(addressSnippet, company.addressSnippet) && Objects.equals(dateOfCreation, company.dateOfCreation) && Objects.equals(description, company.description) && Objects.equals(companyNumber, company.companyNumber) && Objects.equals(title, company.title) && Objects.equals(companyType, company.companyType) && Objects.equals(address, company.address) && Objects.equals(kind, company.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyStatus, addressSnippet, dateOfCreation, description, companyNumber, title, companyType, address, kind);
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyStatus='" + companyStatus + '\'' +
                ", addressSnipet='" + addressSnippet + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", description='" + description + '\'' +
                ", companyNumber='" + companyNumber + '\'' +
                ", title='" + title + '\'' +
                ", CompanyType='" + companyType + '\'' +
                ", address=" + address +
                ", kind='" + kind + '\'' +
                '}';
    }
}
