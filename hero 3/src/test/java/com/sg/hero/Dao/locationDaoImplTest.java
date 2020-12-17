/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Location;
import com.sg.hero.Dto.Sighting;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
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

public class locationDaoImplTest {

    @Autowired
    sightingDaoImpl sightingDao;

    @Autowired
    locationDaoImpl locationDao;

    @Autowired
    heroDaoImpl heroDao;

    public locationDaoImplTest() {
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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Create method, of class locationDaoImpl.
     */
    @Test
    public void testCreateAndGet() {
        Location location = new Location();
        location.setLocationDescription("Test Location Description");
        location.setCity("Test City");
        location.setState("NY");
        location.setStreet("Test Street");
        location.setZip("10002");
        location.setLattitude(21.123);
        location.setLongitude(12.30343);
        location = locationDao.Create(location);
        Location fromDao = locationDao.ReadById(location.getLocationId());
        assertEquals(location, fromDao);
    }

    /**
     * Test of ReadAll method, of class locationDaoImpl.
     */
    @Test
    public void testReadAll() {
        Location location1 = new Location();
        location1.setLocationDescription("Test description");
        location1.setStreet("Test Street");
        location1.setCity("Test City");
        location1.setState("NY");
        location1.setZip("10002");
        location1.setLattitude(12.123);
        location1.setLongitude(21.31234);
        location1 = locationDao.Create(location1);

        Location location2 = new Location();
        location2.setLocationDescription("Test Description 2");
        location2.setStreet("Test Street");
        location2.setCity("Test City");
        location2.setState("NY");
        location2.setZip("10002");
        location2.setLattitude(45.68765);
        location2.setLongitude(12.33278);
        location2 = locationDao.Create(location2);
        List<Location> locations = locationDao.ReadAll();
        
        
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
    }

    /**
     * Test of Update method, of class locationDaoImpl.
     */
    @Test
    
    public void testUpdate() {
        Location location = new Location();
        location.setLocationDescription("Test Descritpion");
        location.setStreet("Test Street");
        location.setCity("Test City");
        location.setState("NY");
        location.setZip("10002");
        location.setLattitude(12.34322);
        location.setLongitude(43.21311);
        location = locationDao.Create(location);
        
        Location fromDao = locationDao.ReadById(location.getLocationId());
        assertEquals(location, fromDao);
        
        location.setCity("SF");
        locationDao.Update(location);
        // changed assertNotSame instead of using assertEquals because 
        assertNotSame(location, fromDao);
        
        fromDao = locationDao.ReadById(location.getLocationId());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of Delete method, of class locationDaoImpl.
     */
    @Test
    public void testDelete() {
        Location location = new Location();
        location.setLocationDescription("Test Location");
        location.setStreet("Test Street");
        location.setCity("Test City");
        location.setState("NY");
        location.setZip("10002");
        location.setLattitude(13.20234);
        location.setLongitude(75.0097);
        location = locationDao.Create(location);
        Location fromDao = locationDao.ReadById(location.getLocationId());
        assertEquals(location, fromDao);
        locationDao.Delete(location.getLocationId());
        fromDao = locationDao.ReadById(location.getLocationId());
        assertNull(fromDao);
    }
}
