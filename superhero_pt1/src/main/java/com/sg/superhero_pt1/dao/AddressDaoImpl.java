package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class AddressDaoImpl  implements  AddressDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Address add(Address address) {
        final String sql = "INSERT INTO address(street, city, state, zipcode) " + "VALUES(?,?,?,?)";

        jdbc.update(sql, address.getStreet(), address.getCity(), address.getState(), address.getZipcode());

        //this allows us to auto increment out address_id by choosing the last one generated and setting the id to that
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        address.setAdd_id(newId);
        return address;
    }

    @Override
    public List<Address> getAllAddresses() {
        final String sql = "SELECT * FROM address";
        return jdbc.query(sql, new AddressMapper());
    }

    @Override
    public Address getAddressById(int add_id) {
        try {
            final String sql = "SELECT * FROM address WHERE add_id = ?";
            return jdbc.queryForObject(sql, new AddressMapper(), add_id);

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateAddress(Address address) {
        final String sql = "UPDATE address SET "
                + "street = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zipcode = ? "
                + "WHERE add_id = ?;";
        jdbc.update(sql, address.getStreet(), address.getCity(), address.getState(), address.getZipcode(), address.getAdd_id());
    }

    @Override
    @Transactional
    public void deleteAddressById(int add_id) {
        //UPDATE organization SET add_id = NULL WHERE add_id = ?;
        final String updateOrg = "UPDATE organization SET add_id = NULL WHERE add_id = ?;";
        jdbc.update(updateOrg, add_id);

        //UPDATE sightings SET add_id = NULL WHERE add_id = ?;
        final String updateSight = "UPDATE sightings SET add_id = NULL WHERE add_id = ?;";
        jdbc.update(updateSight, add_id);

        //DELETE FROM address WHERE add_id = ?;
        final String deleteAdd = "DELETE FROM address WHERE add_id = ?;";
        jdbc.update(deleteAdd, add_id);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDaoImpl)) return false;
        AddressDaoImpl that = (AddressDaoImpl) o;
        return Objects.equals(jdbc, that.jdbc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jdbc);
    }

    public static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int index) throws SQLException {
            Address address = new Address();
            address.setAdd_id(rs.getInt("add_id"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZipcode(rs.getInt("zipcode"));
            return address;
        }
    }
}
