package com.sg.superhero_pt1.model;

import java.util.Objects;

public class Organization {

    private int org_id;
    private String org_name;
    private String org_description;
    private String phone;
    private int add_id;

    @Override
    public String toString() {
        return "Organization{" + "org_id=" + org_id + ", org_name=" + org_name + ", org_description=" + org_description + ", phone=" + phone + ",add_id" + add_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.org_id;
        hash = 53 * hash + Objects.hashCode(this.org_name);
        hash = 53 * hash + Objects.hashCode(this.org_description);
        hash = 53 * hash + Objects.hashCode(this.phone);
        hash = 53 * hash + Objects.hashCode(this.add_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.org_id != other.org_id) {
            return false;
        }
        if (!Objects.equals(this.org_name, other.org_name)) {
            return false;
        }
        if (!Objects.equals(this.org_description, other.org_description)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.add_id, other.add_id)) {
            return false;
        }

        return true;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getOrg_description() {
        return org_description;
    }

    public void setOrg_description(String org_description) {
        this.org_description = org_description;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) { this.phone = phone;}

    public int getAdd_id(){ return add_id; }

    public void setAdd_id(int add_id) { this.add_id = add_id; }

}
