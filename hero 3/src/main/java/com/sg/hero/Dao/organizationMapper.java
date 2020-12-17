/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Organization;
import com.sg.hero.Dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author alanc
 */
public class organizationMapper implements RowMapper<Organization> {

//    private int OrganizationId;
//    private String Name;
//    private String Descriptions;
//    private String Street;
//    private String City;
//    private String State;
//    private String Zip;
//    private List<Hero> heroes;
//    
    @Override
    public Organization mapRow(ResultSet rs, int i) throws SQLException {
        Organization organization = new Organization();

        organization.setOrganizationId(rs.getInt("organizationId"));
        organization.setName(rs.getString("organizationName"));
        organization.setDescriptions(rs.getString("organizationDescription"));
        organization.setStreet(rs.getString("street"));
        organization.setCity(rs.getString("city"));
        organization.setState(rs.getString("state"));
        organization.setZip(rs.getString("zip"));
        organization.setCity(rs.getString("city"));


        return organization;
    }

}
