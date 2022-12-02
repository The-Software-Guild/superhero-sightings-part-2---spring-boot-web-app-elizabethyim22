package com.sg.superhero_pt1.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Sighting {

    private int id;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 25, message="Name must be fewer than 25 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 100, message="Description must be fewer than 100 characters")
    private String description;

    private double latitude;

    private double longitude;

    private int add_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAdd_id() {
        return add_id;
    }

    public void setAdd_id(int add_id) {
        this.add_id = add_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (id != sighting.id) return false;
        if (Double.compare(sighting.latitude, latitude) != 0) return false;
        if (Double.compare(sighting.longitude, longitude) != 0) return false;
        if (add_id != sighting.add_id) return false;
        if (!Objects.equals(name, sighting.name)) return false;
        return Objects.equals(description, sighting.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + add_id;
        return result;
    }

    @Override
    public String toString() {
        return "Sighting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", add_id=" + add_id +
                '}';
    }
}
