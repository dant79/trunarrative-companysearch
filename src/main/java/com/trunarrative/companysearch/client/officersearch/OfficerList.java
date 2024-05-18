package com.trunarrative.companysearch.client.officersearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class OfficerList {

    private String kind;
    @JsonProperty("items_per_page")
    private int totalItems;

    @JsonProperty("items")
    private List<OfficerResult> officers;
    public String getKind() {
        return kind;
    }

    public OfficerList setKind(String kind) {
        this.kind = kind;
        return this;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public OfficerList setTotalItems(int totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    public List<OfficerResult> getOfficers() {
        return officers;
    }

    public OfficerList setOfficers(List<OfficerResult> officers) {
        this.officers = officers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficerList that = (OfficerList) o;
        return totalItems == that.totalItems && Objects.equals(kind, that.kind) && Objects.equals(officers, that.officers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, totalItems, officers);
    }

    @Override
    public String toString() {
        return "OfficerList{" +
                "kind='" + kind + '\'' +
                ", totalItems=" + totalItems +
                ", officers=" + officers +
                '}';
    }
}
