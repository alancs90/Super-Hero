/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author alanc
 */
public class sightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int i) throws SQLException {
        Sighting sighting = new Sighting();
        sighting.setSightingId(rs.getInt("sightingsId"));
        sighting.setDescription(rs.getString("sightingDescription"));
        sighting.setDate(rs.getDate("sightingDate").toLocalDate());

        return sighting;
    }

}
