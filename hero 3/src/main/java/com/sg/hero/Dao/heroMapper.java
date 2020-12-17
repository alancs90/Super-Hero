/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Hero;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author alanc
 */
public class heroMapper implements RowMapper<Hero> {

    @Override
    public Hero mapRow(ResultSet rs, int i) throws SQLException {
        Hero hero = new Hero();

        hero.setHeroId(rs.getInt("heroId"));
        hero.setDescription(rs.getString("heroDescription"));
        hero.setMembertype(rs.getString("membertype"));
        hero.setName(rs.getString("heroName"));
        
        return hero;
    }

}
