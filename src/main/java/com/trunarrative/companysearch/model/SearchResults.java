package com.trunarrative.companysearch.model;

import java.util.List;
import java.util.Objects;

public class SearchResults {

    private int totalResults;

    private List<Company> companies;

    public SearchResults(int totalResults, List<Company> companies) {
        this.totalResults = totalResults;
        this.companies = companies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public SearchResults setTotalResults(int totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public List<Company> getItems() {
        return companies;
    }

    public SearchResults setItems(List<Company> companies) {
        this.companies = companies;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResults that = (SearchResults) o;
        return totalResults == that.totalResults && Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResults, companies);
    }

    @Override
    public String toString() {
        return "SearchResults{" +
                "totalResults=" + totalResults +
                ", items=" + companies +
                '}';
    }
}
