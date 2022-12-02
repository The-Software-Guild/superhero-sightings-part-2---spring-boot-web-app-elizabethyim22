package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.TestApplicationConfiguration;
import com.sg.superhero_pt1.model.Address;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Before;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class AddressDaoDBTest extends TestCase {

    @Autowired
    AddressDao addressDao;

    public AddressDaoDBTest() {}

    @Before
    public void setUp() {
        //deletes all entries of each table in the test database, so we can test properly
        List<Address> addresses = addressDao.getAllAddresses();
        for(Address address : addresses) {
            addressDao.deleteAddressById(address.getAdd_id());
        }

    }

    @Test
    public void testAddAndGetAddress() {
        //create a new address
        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        Address fromDao = addressDao.getAddressById(address.getAdd_id());

        assertEquals(address.getAdd_id(), fromDao.getAdd_id());

    }

    @Test
    public void testGetAllAddresses() {
        //create 2 addresses
        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        Address address2 = new Address();
        address2.setStreet("456 Avenue Blvd");
        address2.setCity("Atlantis");
        address2.setState("CA");
        address2.setZipcode(98765);
        addressDao.add(address2);

        List<Address> addresses = addressDao.getAllAddresses();

        //assert that there are 2 items in the list and that the items are our addresses we created
        assertEquals(2, addresses.size());
        assertTrue(addresses.contains(address));
        assertTrue(addresses.contains(address2));

    }

    @Test
    public void testUpdateAddress() {
        //create a new address
        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        //update one part of it
        address.setStreet("987 Test Ave.");
        addressDao.updateAddress(address);

        //get the new updated version
        Address updated = addressDao.getAddressById(address.getAdd_id());

        //assert that the address got updated correctly and that they are equal now
        assertEquals(address, updated);
    }

    @Test
    public void testDeleteAddressById() {
        //create a new address
        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        //get the address
        Address fromDao = addressDao.getAddressById(address.getAdd_id());

        //assert that the address we created is equal to the one retrieved
        assertEquals(address.getAdd_id(), fromDao.getAdd_id());

        //delete the address
        addressDao.deleteAddressById(address.getAdd_id());

        //get the new ID and assert that it is null
        fromDao = addressDao.getAddressById(address.getAdd_id());
        assertNull(fromDao);

    }

}
