package com.trunarrative.companysearch.client.officersearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trunarrative.companysearch.model.Address;

import java.time.LocalDate;
import java.util.Objects;

public class OfficerResult {

    private Address address;
    private String name;
    @JsonProperty("appointed_on")
    private LocalDate appointedOn;
    @JsonProperty("resigned_on")
    private LocalDate resignedOn;
    @JsonProperty("officer_role")
    private String officerRole;
    @JsonProperty("date_of_birth")
    private DateOfBirth dateOfBirth;
    private String occupation;
    @JsonProperty("country_of_residence")
    private String countryOfResidence;
    private String nationality;

    public Address getAddress() {
        return address;
    }

    public OfficerResult setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public OfficerResult setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getAppointedOn() {
        return appointedOn;
    }

    public OfficerResult setAppointedOn(LocalDate appointedOn) {
        this.appointedOn = appointedOn;
        return this;
    }

    public LocalDate getResignedOn() {
        return resignedOn;
    }

    public OfficerResult setResignedOn(LocalDate resignedOn) {
        this.resignedOn = resignedOn;
        return this;
    }

    public String getOfficerRole() {
        return officerRole;
    }

    public OfficerResult setOfficerRole(String officerRole) {
        this.officerRole = officerRole;
        return this;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public OfficerResult setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public OfficerResult setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public OfficerResult setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public OfficerResult setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficerResult that = (OfficerResult) o;
        return Objects.equals(address, that.address) && Objects.equals(name, that.name) && Objects.equals(appointedOn, that.appointedOn) && Objects.equals(resignedOn, that.resignedOn) && Objects.equals(officerRole, that.officerRole) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(occupation, that.occupation) && Objects.equals(countryOfResidence, that.countryOfResidence) && Objects.equals(nationality, that.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, name, appointedOn, resignedOn, officerRole, dateOfBirth, occupation, countryOfResidence, nationality);
    }

    @Override
    public String toString() {
        return "OfficerResult{" +
                "address=" + address +
                ", name='" + name + '\'' +
                ", appointedOn=" + appointedOn +
                ", resignedOn=" + resignedOn +
                ", officerRole='" + officerRole + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", occupation='" + occupation + '\'' +
                ", countryOfResidence='" + countryOfResidence + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
