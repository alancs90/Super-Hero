/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Hero;
import com.sg.hero.Dto.Organization;
import com.sg.hero.Dto.Sighting;
import com.sg.hero.Dto.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class heroDaoImpl implements Dao<Hero> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Hero Create(Hero hero) {
        final String ADD_HERO = "INSERT INTO hero(heroName, heroDescription, superpowerId, memberType)"
                + "VALUES(?,?,?,?)";
        jdbcTemplate.update(ADD_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower().getSuperPowerId(),
                hero.getMembertype());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(newId);
        return hero;
    }

    @Override
    public List<Hero> ReadAll() {
        final String SELECT_ALL_HEROES = "SELECT * FROM hero";
        List<Hero> heroes = jdbcTemplate.query(SELECT_ALL_HEROES, new heroMapper());
        associateSuperPowersAndHeroes(heroes);
        return heroes;
    }

    @Override
    public Hero ReadById(int id) {
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM hero WHERE heroId = ?";
            Hero hero = jdbcTemplate.queryForObject(SELECT_HERO_BY_ID, new heroMapper(), id);
            hero.setSuperpower(getSuperPowerByHero(id));

            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void Update(Hero DTO) {
        final String UPDATE_HERO = "UPDATE hero SET heroName = ?, heroDescription = ?, superPowerId = ?, memberType = ? "
                + "WHERE heroId = ?";
        jdbcTemplate.update(UPDATE_HERO,
                DTO.getName(),
                DTO.getDescription(),
                DTO.getSuperpower().getSuperPowerId(),
                DTO.getMembertype(),
                DTO.getHeroId());

    }

    @Override
    @Transactional
    public void Delete(int id) {
        Hero DTO = ReadById(id);
        
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE heroId = ?";
        jdbcTemplate.update(DELETE_SIGHTING, id);
        
        final String UPDATE_HERO = "DELETE FROM hero "
                + "WHERE heroiD = ?";
        jdbcTemplate.update(UPDATE_HERO,
                DTO.getHeroId());
    }

    public List<Organization> readOrganizationByHero(int heroId) {
        final String ORGANIZATION_BY_HEROID = "SELECT * FROM organizationMembers WHERE heroId = ? ";
        return this.jdbcTemplate.query(ORGANIZATION_BY_HEROID, new organizationMapper(), heroId);
    }

    /**
     * public void associateOrganization(Hero hero) { List<Organization>
     * organizationList = readOrganizationByHero(hero.getHeroId());
     * hero.setOrganizations(organizationList); } "SELECT s.* FROM sightings s
     * JOIN " + "hero h ON heroId = s.sightingId WHERE s.heroId = ?";
     *
     *
     */
    public Superpower getSuperPowerByHero(int heroId) {
        final String SUPERPOWER_BY_HEROID = " SELECT  sp.* \n"
                + "FROM superpower sp \n"
                + "JOIN hero h on sp.superpowerId = h.superPowerId\n"
                + "  WHERE h.heroId =?;";
        return this.jdbcTemplate.queryForObject(SUPERPOWER_BY_HEROID, new superPowerMapper(), heroId);

    }

    public List<Sighting> readSightingByHero(int heroId) {
        final String SIGHTING_BY_HEROID = "SELECT * FROM sightings WHERE heroId = ? ";
        return this.jdbcTemplate.query(SIGHTING_BY_HEROID, new sightingMapper(), heroId);
    }

    public void associateSuperPowersAndHeroes(List<Hero> heroes) {
        for (Hero hero : heroes) {
            hero.setSuperpower(getSuperPowerByHero(hero.getHeroId()));

        }
    }

    /**
     * public void associateSighting(Hero hero) { List<Sighting> sightingList =
     * readSightingByHero(hero.getHeroId()); hero.setSightings(sightingList); }
     */
}
