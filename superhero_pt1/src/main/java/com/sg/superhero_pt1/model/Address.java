package com.sg.superhero_pt1.model;

import java.util.Objects;

public class Address {

    private int add_id;

    private String street;

    private String city;

    private String state;

    private int zipcode;

    public int getAdd_id() {
        return add_id;
    }

    public void setAdd_id(int add_id) {
        this.add_id = add_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getAdd_id() == address.getAdd_id() && getZipcode() == address.getZipcode() && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getState(), address.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdd_id(), getStreet(), getCity(), getState(), getZipcode());
    }
}
