package com.trunarrative.companysearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Address {

    private String premises;
    @JsonProperty("address_line_1")
    private String addressLine1;
    private String locality;
    @JsonProperty("postal_code")
    private String postcode;
    private String country;

    public String getPremises() {
        return premises;
    }

    public Address setPremises(String premises) {
        this.premises = premises;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public Address setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getLocality() {
        return locality;
    }

    public Address setLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public Address setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(premises, address.premises) && Objects.equals(addressLine1, address.addressLine1) && Objects.equals(locality, address.locality) && Objects.equals(postcode, address.postcode) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premises, addressLine1, locality, postcode, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "premises='" + premises + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", locality='" + locality + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
