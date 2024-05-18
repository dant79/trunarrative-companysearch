package com.trunarrative.companysearch.model;

import jakarta.validation.constraints.Size;

import java.util.Objects;

public class SearchCriteria {

    @Size(max = 30)
    private String companyName;

    @Size(max = 10)
    private String companyNumber;

    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCriteria that = (SearchCriteria) o;
        return Objects.equals(companyName, that.companyName) && Objects.equals(companyNumber, that.companyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, companyNumber);
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "companyName='" + companyName + '\'' +
                ", companyNumber='" + companyNumber + '\'' +
                '}';
    }
}
