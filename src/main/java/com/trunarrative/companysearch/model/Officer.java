package com.trunarrative.companysearch.model;

import java.time.LocalDate;
import java.util.Objects;

public class Officer {

    private String name;
    private String officerRole;
    private LocalDate appointedOn;

    private Address address;

    public String getName() {
        return name;
    }

    public Officer setName(String name) {
        this.name = name;
        return this;
    }

    public String getOfficerRole() {
        return officerRole;
    }

    public Officer setOfficerRole(String officerRole) {
        this.officerRole = officerRole;
        return this;
    }

    public LocalDate getAppointedOn() {
        return appointedOn;
    }

    public Officer setAppointedOn(LocalDate appointedOn) {
        this.appointedOn = appointedOn;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Officer setAddress(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Officer officer = (Officer) o;
        return Objects.equals(name, officer.name) && Objects.equals(officerRole, officer.officerRole) && Objects.equals(appointedOn, officer.appointedOn) && Objects.equals(address, officer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, officerRole, appointedOn, address);
    }

    @Override
    public String toString() {
        return "Officer{" +
                "name='" + name + '\'' +
                ", officerRole='" + officerRole + '\'' +
                ", appointedOn=" + appointedOn +
                ", address=" + address +
                '}';
    }
}
