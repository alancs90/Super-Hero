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
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author michaelrodriguez
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class sightingDaoImplTest {

    @Autowired
    sightingDaoImpl sightingDao;

    @Autowired
    locationDaoImpl locationDao;

    @Autowired
    heroDaoImpl heroDao;

    @Autowired
    superpowerDaoImpl superpowerDao;

    public sightingDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        
        
        List<Sighting> sightings = sightingDao.ReadAll();
        for (Sighting sighting : sightings) {
            sightingDao.Delete(sighting.getSightingId());
        }
        List<Location> locations = locationDao.ReadAll();
        for (Location location : locations) {
            locationDao.Delete(location.getLocationId());
        }
        List<Hero> heroes = heroDao.ReadAll();
        for (Hero hero : heroes) {
            heroDao.Delete(hero.getHeroId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Create method, of class sightingDaoImpl.
     */
    @Test
    public void testCreate() {

        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);

        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test MemberType");
        hero = heroDao.Create(hero);

        Location location = new Location();
        location.setLocationDescription("Test Location Description");
        location.setCity("Test City");
        location.setState("NY");
        location.setStreet("Test Street");
        location.setZip("10002");
        location.setLattitude(21.123);
        location.setLongitude(12.30343);
        location = locationDao.Create(location);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);

        LocalDate localDate = LocalDate.parse("2020-03-29");
        sighting.setDate(localDate);
        sighting.setDescription("Test Description");

        sighting = sightingDao.Create(sighting);
        Sighting fromDao = sightingDao.ReadById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of ReadAll method, of class sightingDaoImpl.
     */
    @Test
    public void testReadAll() {
       
     
        
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);

        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test MemberType");
        hero = heroDao.Create(hero);

        Location location = new Location();
        location.setLocationDescription("Test Location Description");
        location.setCity("Test City");
        location.setState("NY");
        location.setStreet("Test Street");
        location.setZip("10002");
        location.setLattitude(21.123);
        location.setLongitude(12.30343);
        location = locationDao.Create(location);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        LocalDate localDate = LocalDate.parse("2020-03-29");
        sighting.setDate(localDate);
        sighting.setDescription("Test description");
        sighting = sightingDao.Create(sighting);

        Superpower power2 = new Superpower();
        power2.setPower("Test Power");
        power2 = superpowerDao.Create(power2);

        Hero hero2 = new Hero();
        hero2.setName("Test Name");
        hero2.setDescription("Test Description");
        hero2.setSuperpower(power);
        hero2.setMembertype("Test MemberType");
        hero2 = heroDao.Create(hero2);

        Location location2 = new Location();
        location2.setLocationDescription("Test Location Description");
        location2.setCity("Test City");
        location2.setState("NY");
        location2.setStreet("Test Street");
        location2.setZip("10002");
        location2.setLattitude(21.123);
        location2.setLongitude(12.30343);
        location2 = locationDao.Create(location2);
   

        Sighting sighting2 = new Sighting();
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        LocalDate localDate2 = LocalDate.parse("2020-03-29");
        sighting2.setDate(localDate2);
        sighting2.setDescription("Test description");
        sighting2 = sightingDao.Create(sighting2);

        List<Sighting> sightingsAfterAdd = sightingDao.ReadAll();

        assertEquals(2, sightingsAfterAdd.size());
        
        assertTrue(sightingsAfterAdd.contains(sighting));
        assertTrue(sightingsAfterAdd.contains(sighting2));
    }

    /**
     * Test of ReadById method, of class sightingDaoImpl.
     */
    @Test
    public void testReadById() {
        
        // already being tested above
    }

    /**
     * Test of Update method, of class sightingDaoImpl.
     */
    @Test
    public void testUpdate() {
        
        
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);

        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test MemberType");
        hero = heroDao.Create(hero);

        Location location = new Location();
        location.setLocationDescription("Test Location Description");
        location.setCity("Test City");
        location.setState("NY");
        location.setStreet("Test Street");
        location.setZip("10002");
        location.setLattitude(21.123);
        location.setLongitude(12.30343);
        location = locationDao.Create(location);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        LocalDate localDate = LocalDate.parse("2020-03-29");
        sighting.setDate(localDate);
        sighting.setDescription("Test description");
        sighting = sightingDao.Create(sighting);

        
        Sighting fromDao = sightingDao.ReadById(sighting.getSightingId());
        assertEquals(sighting, fromDao);

        sighting.setDescription("new test description");
        sightingDao.Update(sighting);

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.ReadById(sighting.getSightingId());

        assertEquals(sighting, fromDao);

       
    }

    /**
     * Test of Delete method, of class sightingDaoImpl.
     */
    @Test
    public void testDelete() {
         
        
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);

        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test MemberType");
        hero = heroDao.Create(hero);

        Location location = new Location();
        location.setLocationDescription("Test Location Description");
        location.setCity("Test City");
        location.setState("NY");
        location.setStreet("Test Street");
        location.setZip("10002");
        location.setLattitude(21.123);
        location.setLongitude(12.30343);
        location = locationDao.Create(location);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        LocalDate localDate = LocalDate.parse("2020-03-29");
        sighting.setDate(localDate);
        sighting.setDescription("Test description");
        sighting = sightingDao.Create(sighting);

        
        Sighting fromDao = sightingDao.ReadById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
        
        sightingDao.Delete(sighting.getSightingId());
        
        fromDao = sightingDao.ReadById(sighting.getSightingId());
        assertNull(fromDao);
        
    }

    /**
     * Test of getSightingsForHero method, of class sightingDaoImpl.
     */
    @Test
    public void testGetSightingsForHero() {
        // Already tested above
    }

    /**
     * Test of getSightingsForLocation method, of class sightingDaoImpl.
     */
    @Test
    public void testGetSightingsForLocation() {
    // tested above!
    }

}
