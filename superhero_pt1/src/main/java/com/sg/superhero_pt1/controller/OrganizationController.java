package com.sg.superhero_pt1.controller;

import com.sg.superhero_pt1.dao.AddressDao;
import com.sg.superhero_pt1.dao.OrganizationDao;
import com.sg.superhero_pt1.model.Address;
import com.sg.superhero_pt1.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrganizationController {
    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    AddressDao addressDao;


    @GetMapping("organizations")
    public String displayOrganizations(Model model){
        //we want the Address to be a drop-down so we have to show all
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<Address> addresses = addressDao.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "organizations";
    }

    @PostMapping("addOrganizations")
    public String addOrganizations(HttpServletRequest request){
        String org_name = request.getParameter("org_name");
        String org_description = request.getParameter("org_description");
        String phone = request.getParameter("phone");
        String add_id = request.getParameter("add_id");

        Organization organization = new Organization();
        organization.setOrg_name(org_name);
        organization.setOrg_description(org_description);
        organization.setPhone(phone);
        organization.setAdd_id(Integer.parseInt(add_id));
        organizationDao.addOrganization(organization);
        return "redirect:/organizations";
    }

    @GetMapping("organizations/{org_id}")
    public Organization getOrganizationById(@PathVariable int org_id){
        return organizationDao.getOrganizationById(org_id);
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("org_id"));
        organizationDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model){
        //we want the Address to be a drop-down in the edit, so we have to get all instances
        int org_id = Integer.parseInt(request.getParameter("org_id"));
        Organization organization = organizationDao.getOrganizationById(org_id);
        List<Address> addresses = addressDao.getAllAddresses();
        model.addAttribute("addresses", addresses);
        model.addAttribute("organization", organization);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performUpdateOrganization(HttpServletRequest request){
        int org_id = Integer.parseInt(request.getParameter("org_id"));
        Organization organization = organizationDao.getOrganizationById(org_id);
        organization.setOrg_name(request.getParameter("org_name"));
        organization.setOrg_description(request.getParameter("org_description"));
        organization.setPhone(request.getParameter("phone"));
        organization.setAdd_id(Integer.parseInt(request.getParameter("add_id")));

        organizationDao.updateOrganization(organization);
        return "redirect:/organizations";
    }

}