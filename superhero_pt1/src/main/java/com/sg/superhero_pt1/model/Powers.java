package com.sg.superhero_pt1.model;

import java.util.Objects;

public class Powers {
    private int powers_id;
    private String powers_name;

    public int getPowers_id() {
        return powers_id;
    }

    public void setPowers_id(int powers_id){
        this.powers_id = powers_id;
    }

    public String getPowers_name(){
        return powers_name;
    }

    public void setPowers_name(String powers_name){
        this.powers_name = powers_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Powers)) return false;
        Powers powers = (Powers) o;
        return getPowers_id() == powers.getPowers_id() && getPowers_name().equals(powers.getPowers_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPowers_id(), getPowers_name());
    }
}
