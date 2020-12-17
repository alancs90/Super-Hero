/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;
import com.sg.hero.Dto.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author user
 */
@Repository
public class superpowerDaoImpl implements Dao<Superpower> {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    @Transactional
    public Superpower Create(Superpower power) {
        final String ADD_POWER = "INSERT INTO superpower(power) VALUES (?)";
        jdbcTemplate.update(ADD_POWER, power.getPower());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setSuperPowerId(newId);
        return power;
    }
    @Override
    public List<Superpower> ReadAll() {
        final String SELECT_ALL_POWERS = "SELECT * FROM superpower";
        return jdbcTemplate.query(SELECT_ALL_POWERS, new superPowerMapper());
    }
    @Override
    public Superpower ReadById(int superPowerId) {
        try {
            final String SELECT_POWER_BY_ID = "SELECT * FROM superpower WHERE superpowerId = ?";
        return jdbcTemplate.queryForObject(SELECT_POWER_BY_ID, new superPowerMapper(), superPowerId);
        } catch (DataAccessException ex) {
            return null;
        }
    }
    @Override
    public void Update(Superpower power) {
        final String UPDATE_POWER = "UPDATE superpower SET power = ? WHERE superpowerId = ?";
        jdbcTemplate.update(UPDATE_POWER, power.getPower(), power.getSuperPowerId());
    }
    @Override
    public void Delete(int superPowerId) {
        Superpower power = ReadById(superPowerId);
        final String DELETE_POWER = "DELETE from superpower WHERE superpowerId = ?";
        jdbcTemplate.update(DELETE_POWER, power.getSuperPowerId());
    }
}
