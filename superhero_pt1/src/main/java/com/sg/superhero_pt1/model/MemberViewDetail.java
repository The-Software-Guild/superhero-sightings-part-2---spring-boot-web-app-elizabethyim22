package com.sg.superhero_pt1.model;

import java.util.Objects;

public class MemberViewDetail {
    private int member_id;
    private String member_name;
    private String member_description;
    private String org_name;
    private String powers_name;

    public int getMember_id(){
        return member_id;
    }
    public void setMember_id(int member_id){
        this.member_id = member_id;
    }

    public String getMember_name(){
        return member_name;
    }
    public void setMember_name(String member_name){
        this.member_name = member_name;
    }

    public String getMember_description(){
        return member_description;
    }
    public void setMember_description(String member_description){
        this.member_description = member_description;
    }

    public String getOrg_name(){
        return org_name;
    }
    public void setOrg_name(String org_name){
        this.org_name = org_name;
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
        if (!(o instanceof MemberViewDetail)) return false;
        MemberViewDetail that = (MemberViewDetail) o;
        return getMember_id() == that.getMember_id() && Objects.equals(getMember_name(), that.getMember_name()) && Objects.equals(getMember_description(), that.getMember_description()) && Objects.equals(getOrg_name(), that.getOrg_name()) && Objects.equals(getPowers_name(), that.getPowers_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMember_id(), getMember_name(), getMember_description(), getOrg_name(), getPowers_name());
    }
}
