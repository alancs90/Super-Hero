/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Hero;
import com.sg.hero.Dto.Organization;
import com.sg.hero.Dto.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class organizationDaoImpl implements Dao<Organization> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Organization Create(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organization(organizationName,organizationDescription,street,city,state,zip,memberType)"
                + "VALUES(?,?,?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescriptions(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getZip(),
                organization.getMemberType());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(newId);
        insertOrganizationMember(organization);
        return organization;
    }

    private void insertOrganizationMember(Organization organization) {
        final String INSERT_ORGANIZATION_MEMBER = "INSERT INTO "
                + "organizationMembers(organizationId, heroId) VALUES(?,?)";
        for (Hero hero : organization.getHeroes()) {
            jdbc.update(INSERT_ORGANIZATION_MEMBER, organization.getOrganizationId(), hero.getHeroId());
        }
    }

    @Override
    public List<Organization> ReadAll() {
        final String SELECT_ALL_ORGANIZATION = "SELECT * FROM organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATION, new organizationMapper());
        associateHero(organizations);
        return organizations;
    }

    private void associateHero(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setHeroes(getHeroesForOrganization(organization.getOrganizationId()));
        }
    }

    @Override
    public Organization ReadById(int organizationId) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organization WHERE organizationId = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new organizationMapper(), organizationId);
            organization.setHeroes(getHeroesForOrganization(organizationId));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Hero> getHeroesForOrganization(int organizationId) {
        final String SELECT_HEROES_FOR_SIGHTING = "SELECT  h.* FROM hero h "
                + "JOIN organizationMembers om ON om.heroId = h.heroId WHERE om.organizationId = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_SIGHTING, new heroMapper(), organizationId);
        for (Hero hero : heroes) {
            Superpower superpower = getSuperPowerByHero(hero.getHeroId());
            hero.setSuperpower(superpower);
        }
        return heroes;
    }

    private Superpower getSuperPowerByHero(int heroId) {
        final String SUPERPOWER_BY_HEROID = " SELECT  sp.* \n"
                + "FROM superpower sp \n"
                + "JOIN hero h ON sp.superpowerId = h.superPowerId\n"
                + "  WHERE h.heroId = ?";
        return jdbc.queryForObject(SUPERPOWER_BY_HEROID, new superPowerMapper(), heroId);
    }

    @Override
    @Transactional
    public void Update(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization SET organizationName = ?, organizationDescription = ?, street = ?, city = ?, state = ?, zip = ?, MemberType = ? "
                + "WHERE organizationId = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescriptions(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getZip(),
                organization.getMemberType(),
                organization.getOrganizationId());
        final String DELETE_ORGANIZATION_MEMBER = "DELETE FROM organizationMembers WHERE organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION_MEMBER, organization.getOrganizationId());
        insertOrganizationMember(organization);
    }

    @Override
    @Transactional
    public void Delete(int organizationId) {
        final String DELETE_ORGANIZATION_MEMBER = "DELETE FROM organizationMembers WHERE organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION_MEMBER, organizationId);
        final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationId);
    }
}
