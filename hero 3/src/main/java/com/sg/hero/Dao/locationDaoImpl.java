/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class locationDaoImpl implements Dao<Location> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location Create(Location DTO) {
        final String ADD_LOCATION = "INSERT INTO locations(locationDescription, street, city, state, zip, lattitude,longitude) "
                + "VALUES(?,?,?,?,?,?,?)";
        jdbc.update(ADD_LOCATION,
                DTO.getLocationDescription(),
                DTO.getStreet(),
                DTO.getCity(),
                DTO.getState(),
                DTO.getZip(),
                DTO.getLattitude(),
                DTO.getLongitude());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        DTO.setLocationId(newId);
        return DTO;
    }

    @Override
    public List<Location> ReadAll() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM locations";
        return jdbc.query(SELECT_ALL_LOCATIONS, new locationMapper());
    }

    @Override
    public Location ReadById(int locationId) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM locations WHERE locationId = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new locationMapper(), locationId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void Update(Location DTO) {
        final String UPDATE_LOCATION = "UPDATE locations SET locationDescription = ?, street = ?, city = ?, state = ?, zip = ?, lattitude = ?,longitude = ? "
                + "WHERE locationId = ?";
        jdbc.update(UPDATE_LOCATION,

                DTO.getLocationDescription(),
                DTO.getStreet(),
                DTO.getCity(), 
                DTO.getState(), 
                DTO.getZip(), 
                DTO.getLattitude(), 
                DTO.getLongitude(),
                DTO.getLocationId());

    }

    @Override
    @Transactional
    public void Delete(int locationId) {
        final String DELETE_LOCATION_BY_ID = "DELETE FROM locations WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION_BY_ID, locationId);
    }
}
