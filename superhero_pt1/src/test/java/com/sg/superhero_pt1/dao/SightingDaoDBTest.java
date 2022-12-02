package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.TestApplicationConfiguration;
import com.sg.superhero_pt1.model.Address;
import com.sg.superhero_pt1.model.Sighting;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Before;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SightingDaoDBTest {

    @Autowired
    SightingDao sightingDao;

    @Autowired
    AddressDao addressDao;

    public SightingDaoDBTest() {}

    @Before
    public void setUp() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getId());
        }

        List<Address> addresses = addressDao.getAllAddresses();
        for(Address address : addresses) {
            addressDao.deleteAddressById(address.getAdd_id());
        }


    }

    @Test
    public void testAddAndGetSighting() {

        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        Sighting sighting = new Sighting();
        sighting.setName("Test First");
        sighting.setDescription("Test Description");
        sighting.setLatitude(1.0000);
        sighting.setLongitude(1.0000);
        sighting.setAdd_id(address.getAdd_id());

        sighting = sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getId());

        assertEquals(sighting, fromDao);
    }


    @Test
    public void testGetAllSightings() {

        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        Sighting sighting = new Sighting();
        sighting.setName("Test First");
        sighting.setDescription("Test Description");
        sighting.setLatitude(1.0000);
        sighting.setLongitude(1.0000);
        sighting.setAdd_id(address.getAdd_id());

        sighting = sightingDao.addSighting(sighting);

        Address address2 = new Address();
        address2.setStreet("456 Avenue Blvd");
        address2.setCity("Atlantis");
        address2.setState("CA");
        address2.setZipcode(98765);
        addressDao.add(address2);


        Sighting sighting2 = new Sighting();
        sighting2.setName("Test Second");
        sighting2.setDescription("Test Description");
        sighting2.setLatitude(2.0000);
        sighting2.setLongitude(2.0000);
        sighting2.setAdd_id(address2.getAdd_id());

        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));

    }

    @Test
    public void testUpdateSighting() {
        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        Sighting sighting = new Sighting();
        sighting.setName("Test First");
        sighting.setDescription("Test Description");
        sighting.setLatitude(1.0000);
        sighting.setLongitude(1.0000);
        sighting.setAdd_id(address.getAdd_id());

        sighting = sightingDao.addSighting(sighting);
        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting, fromDao);

        sighting.setDescription("New Test Description");
        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSightingById(sighting.getId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSightingById() {
        Address address = new Address();
        address.setStreet("123 Test Ave.");
        address.setCity("Metropolis");
        address.setState("MO");
        address.setZipcode(12345);
        addressDao.add(address);

        Sighting sighting = new Sighting();
        sighting.setName("Test First");
        sighting.setDescription("Test Description");
        sighting.setLatitude(1.0000);
        sighting.setLongitude(1.0000);
        sighting.setAdd_id(address.getAdd_id());

        sighting = sightingDao.addSighting(sighting);


        Sighting fromDao = sightingDao.getSightingById(sighting.getId());
        assertEquals(sighting, fromDao);

        sightingDao.deleteSightingById(sighting.getId());

        fromDao = sightingDao.getSightingById(sighting.getId());
        assertNull(fromDao);
    }
}
