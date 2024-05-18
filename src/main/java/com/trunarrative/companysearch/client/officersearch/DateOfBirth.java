package com.trunarrative.companysearch.client.officersearch;

import java.util.Objects;

public class DateOfBirth {

    private int month;
    private int year;

    public int getMonth() {
        return month;
    }

    public DateOfBirth setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public DateOfBirth setYear(int year) {
        this.year = year;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateOfBirth that = (DateOfBirth) o;
        return month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year);
    }

    @Override
    public String toString() {
        return "DateOfBirth{" +
                "month=" + month +
                ", year=" + year +
                '}';
    }
}
