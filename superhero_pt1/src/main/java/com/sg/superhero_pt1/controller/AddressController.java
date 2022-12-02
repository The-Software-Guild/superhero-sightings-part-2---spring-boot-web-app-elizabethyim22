package com.sg.superhero_pt1.controller;

import com.sg.superhero_pt1.dao.AddressDao;
import com.sg.superhero_pt1.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressDao addressDao;

    //lets us view all the addresses we have created thus far
    @GetMapping("addresses")
    public String getAll(Model model) {
        List<Address> addresses = addressDao.getAllAddresses();
        //add an attribute to reference in the html
        model.addAttribute("addresses", addresses);
        return "addresses";
    }


    @PostMapping("addAddress")
    public String addAddress(String street, String city, String state, String zipcode) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipcode(Integer.parseInt(zipcode));
        addressDao.add(address);
        //once we add an address, redirect back to the addresses page, so we can enter another
        return "redirect:/addresses";
    }

    @GetMapping("addresses/{add_id}")
    public Address getById(@PathVariable int add_id) {
        return addressDao.getAddressById(add_id);
    }

    @GetMapping("deleteAddress")
    public String deleteAddress(Integer add_id) {
        addressDao.deleteAddressById(add_id);
        return "redirect:/addresses";
    }

    //part one tells us to get the add_id when we click on "edit" and if we have that id, then redirect to the edit page
    @GetMapping("editAddress")
    public String editAddress(HttpServletRequest request, Model model) {
        int add_id = Integer.parseInt(request.getParameter("add_id"));
        Address address = addressDao.getAddressById(add_id);
        model.addAttribute("address", address);
        return "editAddress";
    }

    @PostMapping("editAddress")
    public String performEditAddress(HttpServletRequest request) {
        int add_id = Integer.parseInt(request.getParameter("add_id"));
        Address address = addressDao.getAddressById(add_id);
        //now we can set all the fields that the user entered, update the address, and redirect back to the addresses page
        address.setStreet(request.getParameter("street"));
        address.setCity(request.getParameter("city"));
        address.setState(request.getParameter("state"));
        address.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
        addressDao.updateAddress(address);
        return "redirect:/addresses";
    }
}
