package com.sg.superhero_pt1.dao;

import com.sg.superhero_pt1.model.Member;
import com.sg.superhero_pt1.model.Powers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PowersDaoImpl implements PowersDao{
    @Autowired
    JdbcTemplate jdbc;

    public static final class PowersMapper implements RowMapper<Powers> {
        @Override
        public Powers mapRow(ResultSet rs, int index) throws SQLException {
            Powers powers = new Powers();
            powers.setPowers_id(rs.getInt("powers_id"));
            powers.setPowers_name(rs.getString("powers_name"));
            return powers;
        }
    }

    @Override
    @Transactional
    public Powers addPowers(Powers powers){
        final String sql = "INSERT INTO powers(powers_name) "
                + "VALUES(?)";
        jdbc.update(sql,
                powers.getPowers_name());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        powers.setPowers_id(newId);
        return powers;
    }

    @Override
    public List<Powers> getAllPowers(){
        final String sql = "SELECT * FROM powers";
        return jdbc.query(sql, new PowersMapper());
    }

    @Override
    public Powers getPowersById(int powers_id){
        try{
            final String sql = "SELECT * FROM powers WHERE powers_id = ?";
            return jdbc.queryForObject(sql, new PowersMapper(), powers_id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void editPowers(Powers power){
        final String sql = "UPDATE powers SET powers_name = ? " +
                "WHERE powers_id = ?";
        jdbc.update(sql,
                power.getPowers_name(),
                power.getPowers_id());
    }

    @Override
    @Transactional
    public void deletePowersById(int powers_id){
        final String DELETE_MEMBERS = "DELETE FROM member WHERE powers_id = ?";
        jdbc.update(DELETE_MEMBERS, powers_id);

        final String DELETE_POWERS = "DELETE FROM powers WHERE powers_id = ?";
        jdbc.update(DELETE_POWERS, powers_id);
    }

}
