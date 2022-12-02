package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.Objects;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int org_id){
        try {
            final String sql = "SELECT * FROM organization WHERE org_id = ?";
            return jdbc.queryForObject(sql, new OrganizationDaoImpl.OrganizationMapper(), org_id);

        } catch (DataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Organization> getAllOrganizations() {
        final String sql = "SELECT * FROM organization";
        return jdbc.query(sql, new OrganizationDaoImpl.OrganizationMapper());
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String sql = "INSERT INTO organization(org_name, org_description, phone, add_id)" + "VALUES(? ,?, ?, ?)";
        jdbc.update(sql, organization.getOrg_name(), organization.getOrg_description(), organization.getPhone(), organization.getAdd_id());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrg_id(newId);
        return organization;
    }
    @Override
    public void updateOrganization(Organization organization) {
        final String sql = "UPDATE Organization SET "
                + "org_name = ?, "
                + "org_description = ?, "
                + "phone = ?, "
                + "add_id = ? "
                + "WHERE org_id = ?;";
        jdbc.update(sql,
                organization.getOrg_name(),
                organization.getOrg_description(),
                organization.getPhone(),
                organization.getAdd_id(),
                organization.getOrg_id());
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int org_id) {
        //UPDATE organization SET add_id = NULL WHERE add_id = ?;
        final String updateOrg = "UPDATE organization SET org_id = NULL WHERE org_id = ?;";
        jdbc.update(updateOrg, org_id);

        //DELETE FROM address WHERE add_id = ?;
        final String deleteOrg = "DELETE FROM organization WHERE org_id = ?;";
        jdbc.update(deleteOrg, org_id);

    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrg_id(rs.getInt("org_Id"));
            organization.setOrg_name(rs.getString("org_name"));
            organization.setOrg_description(rs.getString("org_description"));
            organization.setPhone(rs.getString("Phone"));
            organization.setAdd_id(rs.getInt("add_id"));
            return organization;
        }
    }

}