/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author alanc
 */
public class locationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt("locationId"));
        location.setLocationDescription(rs.getString("locationDescription"));
        location.setStreet(rs.getString("street"));
        location.setCity(rs.getString("city"));
        location.setState(rs.getString("state"));
        location.setZip(rs.getString("zip"));
        location.setLongitude(rs.getDouble("longitude"));
        location.setLattitude(rs.getDouble("lattitude"));
        return location;
    }

}
