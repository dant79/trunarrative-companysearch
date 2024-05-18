package com.trunarrative.companysearch.client.companysearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class CompanyList {

    @JsonProperty("total_results")
    private int totalResults;
    private String kind;

    @JsonProperty("items")
    private List<CompanyResult> companyResults;

    public int getTotalResults() {
        return totalResults;
    }

    public CompanyList setTotalResults(int totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public String getKind() {
        return kind;
    }

    public CompanyList setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public List<CompanyResult> getCompanyResults() {
        return companyResults;
    }

    public CompanyList setCompanyResults(List<CompanyResult> companyResults) {
        this.companyResults = companyResults;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyList that = (CompanyList) o;
        return totalResults == that.totalResults && Objects.equals(kind, that.kind) && Objects.equals(companyResults, that.companyResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResults, kind, companyResults);
    }

    @Override
    public String toString() {
        return "CompanyList{" +
                "totalResults=" + totalResults +
                ", kind='" + kind + '\'' +
                ", companyResults=" + companyResults +
                '}';
    }
}
