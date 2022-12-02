package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Address;
import com.sg.superhero_pt1.model.Member;

import java.util.List;

public interface AddressDao {
    Address add(Address address);

    List<Address> getAllAddresses();

    Address getAddressById(int add_id);

    void updateAddress(Address address);

    void deleteAddressById(int add_id);

}