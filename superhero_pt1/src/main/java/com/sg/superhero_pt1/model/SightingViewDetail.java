package com.sg.superhero_pt1.model;

import java.util.Date;
import java.util.Objects;

public class SightingViewDetail {

    int sighting_id;
    String sighting_name;
    String description;
    Double latitude;
    Double longitude;
    String city;
    String member_name;
    Date date;


    public int getSighting_id() {
        return sighting_id;
    }

    public void setSighting_id(int sighting_id) {
        this.sighting_id = sighting_id;
    }

    public String getSighting_name() {
        return sighting_name;
    }

    public void setSighting_name(String sighting_name) {
        this.sighting_name = sighting_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SightingViewDetail)) return false;
        SightingViewDetail that = (SightingViewDetail) o;
        return getSighting_id() == that.getSighting_id() && Objects.equals(getSighting_name(), that.getSighting_name()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getLatitude(), that.getLatitude()) && Objects.equals(getLongitude(), that.getLongitude()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getMember_name(), that.getMember_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSighting_id(), getSighting_name(), getDescription(), getLatitude(), getLongitude(), getCity(), getMember_name());
    }
}
