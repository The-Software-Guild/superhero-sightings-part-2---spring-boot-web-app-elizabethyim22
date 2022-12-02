package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Organization;

import java.util.List;
public interface OrganizationDao {
    Organization getOrganizationById(int org_id);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int org_id);
}
