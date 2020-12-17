/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author michaelrodriguez
 */
public class superPowerMapper implements RowMapper<Superpower> {

    @Override
    public Superpower mapRow(ResultSet rs, int i) throws SQLException {
        Superpower power = new Superpower();

        power.setSuperPowerId(rs.getInt("superpowerId"));

        power.setPower(rs.getString("power"));

        return power;
    }

}
