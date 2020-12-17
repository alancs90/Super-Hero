/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Hero;
import com.sg.hero.Dto.Location;
import com.sg.hero.Dto.Sighting;
import com.sg.hero.Dto.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class sightingDaoImpl implements Dao<Sighting> {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    heroDaoImpl heroDao;

    @Override
    public Sighting Create(Sighting sighting) {
        final String INSTER_SIGHTING = "INSERT INTO sightings (heroId, locationId, sightingDate, sightingDescription) VALUES (?,?,?,?)";
        jdbc.update(INSTER_SIGHTING,
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId(),
                sighting.getDate(),
                sighting.getDescription());
        sighting.setSightingId(jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        return sighting;
    }

    private void insertSightingHero(Sighting sighting) {
        final String INSERT_SIGHTING_HERO = "INSERT INTO sightings(heroId) VALUES (?)";

        jdbc.update(INSERT_SIGHTING_HERO, sighting.getHero().getHeroId());
    }

    @Override
    public List<Sighting> ReadAll() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sightings";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new sightingMapper());
        associateLocationAndHeroes(sightings);

        return sightings;

    }
     private void associateLocationAndHeroes(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setHero(getHeroForSighting(sighting.getSightingId()));
            sighting.setLocation(getLocationForSighting(sighting.getSightingId()));
        }
     }

    @Override
    public Sighting ReadById(int sightingId) {

        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sightingsId = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new sightingMapper(), sightingId);

            sighting.setLocation(getLocationForSighting(sightingId));
            sighting.setHero(getHeroForSighting(sightingId));

            return sighting;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void Update(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sightings SET sightingDate = ?, sightingDescription = ?"
                + "WHERE sightingsId = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getDate(),
                sighting.getDescription(),
                sighting.getSightingId());
    }

    @Override
    @Transactional
    public void Delete(int id) {
        final String DELETE_FROM_SIGHTING_BY_ID = "DELETE FROM sightings  "
                + "WHERE sightingsId = ? ";
        jdbc.update(DELETE_FROM_SIGHTING_BY_ID, id);
        /**
         * final String DELETE_SUPERPOWER_BY_SIGHTING_ID ="DELETE sp.* FROM
         * superpower sp " + "JOIN hero h where sp.superpowerId = h.superpowerId
         * " + "JOIN sightings s where h.heroId = s.heroId " + "WHERE
         * s.sightingsId =?;"; jdbc.update(DELETE_SUPERPOWER_BY_SIGHTING_ID,
         * id);
         

        final String DELETE_HERO_BY_SIGHTING_ID = "Delete h.* FROM hero h \n"
                + "JOIN sightings s ON s.heroId = h.heroId \n"
                + "WHERE s.sightingsId = ? ;";
        jdbc.update(DELETE_HERO_BY_SIGHTING_ID, id);

        final String DELETE_LOCATION_BY_SIGHTING_ID = "DELETE l.* FROM locations l "
                + "JOIN sightings s ON l.locationId = s.locationId "
                + "WHERE s.sightingsId =?; ";

        jdbc.update(DELETE_LOCATION_BY_SIGHTING_ID, id);
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE sightingId = ? ";
        jdbc.update(DELETE_SIGHTING, DTO.getSightingId());
 */
    }

    private Hero getHeroForSighting(int sightingID) {
        final String SELECT_HERO_FOR_SIGHTING = "   SELECT h.* FROM hero h \n"
                + "    JOIN sightings s on h.heroId = s.heroId\n"
                + "    WHERE s.sightingsId = ?;";

        Hero hero = jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new heroMapper(), sightingID);
        Superpower superpower = heroDao.getSuperPowerByHero(hero.getHeroId());
        hero.setSuperpower(superpower);
        return hero;
    }

    public Location getLocationForSighting(int sightingID) {
        final String SELECT_LOCATION_FOR_SIGHTING = "  SELECT l.* "
                + "FROM locations l \n"
                + "JOIN sightings s on l.locationId = s.locationId\n"
                + "   WHERE s.sightingsId = ?;";
        return this.jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new locationMapper(), sightingID);
    }

  
}

    // We can reference the LocationId instead
    /**
     * List<Sighting> getSightingsForLocation(Location location) { final String
     * SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM sightings heroId = ?";
     * List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION, new
     * sightingMapper(), location.getLocationId());
     * associateLocationAndHero(sightings); return sightings; }
     */
// this is the only method needed we need to associate HERO with Sighting
    // This is the query to merge sightings and hero and find the hero connected with the same sighting id
    // it is used as a method to associateSightingsAndHero below

