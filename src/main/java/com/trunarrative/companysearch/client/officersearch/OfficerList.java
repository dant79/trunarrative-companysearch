package com.trunarrative.companysearch.client.officersearch;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class OfficerList {

    private String kind;

    @JsonProperty("items")
    private List<OfficerResult> officers;
    public String getKind() {
        return kind;
    }

    public OfficerList setKind(String kind) {
        this.kind = kind;
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
        return Objects.equals(kind, that.kind) && Objects.equals(officers, that.officers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, officers);
    }

    @Override
    public String toString() {
        return "OfficerList{" +
                "kind='" + kind + '\'' +
                ", officers=" + officers +
                '}';
    }
}
