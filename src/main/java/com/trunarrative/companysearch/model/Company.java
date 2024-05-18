package com.trunarrative.companysearch.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Company {

    private String companyNumber;
    private String companyType;
    private String title;
    private String companyStatus;
    private LocalDate dateOfCreation;

    private Address address;

    private List<Officer> officers;

    public String getCompanyNumber() {
        return companyNumber;
    }

    public Company setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
        return this;
    }

    public String getCompanyType() {
        return companyType;
    }

    public Company setCompanyType(String companyType) {
        this.companyType = companyType;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Company setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public Company setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
        return this;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public Company setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Company setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<Officer> getOfficers() {
        return officers;
    }

    public Company setOfficers(List<Officer> officers) {
        this.officers = officers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyNumber, company.companyNumber) && Objects.equals(companyType, company.companyType) && Objects.equals(title, company.title) && Objects.equals(companyStatus, company.companyStatus) && Objects.equals(dateOfCreation, company.dateOfCreation) && Objects.equals(address, company.address) && Objects.equals(officers, company.officers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyNumber, companyType, title, companyStatus, dateOfCreation, address, officers);
    }

    @Override
    public String toString() {
        return "Item{" +
                "companyNumber='" + companyNumber + '\'' +
                ", companyType='" + companyType + '\'' +
                ", title='" + title + '\'' +
                ", companyStatus='" + companyStatus + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", address=" + address +
                ", officers=" + officers +
                '}';
    }
}
