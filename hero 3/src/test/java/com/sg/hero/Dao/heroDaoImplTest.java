/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Hero;
import com.sg.hero.Dto.Location;
import com.sg.hero.Dto.Organization;
import com.sg.hero.Dto.Sighting;
import com.sg.hero.Dto.Superpower;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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

public class heroDaoImplTest {

    @Autowired
    heroDaoImpl heroDao;

    @Autowired
    locationDaoImpl locationDao;

    @Autowired
    organizationDaoImpl organizationDao;

    @Autowired
    sightingDaoImpl sightingDao;

    @Autowired
    superpowerDaoImpl superpowerDao;

    public heroDaoImplTest() {

    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        

        List<Hero> heroes = heroDao.ReadAll();
        for (Hero hero : heroes) {
            heroDao.Delete(hero.getHeroId());
        }
        
        List<Superpower> superPowers = superpowerDao.ReadAll();
        for (Superpower superpower : superPowers) {
            superpowerDao.Delete(superpower.getSuperPowerId());
        }

        List<Sighting> sightings = sightingDao.ReadAll();
        for (Sighting sighting : sightings) {
            sightingDao.Delete(sighting.getSightingId());
        }

        List<Location> locations = locationDao.ReadAll();
        for (Location location : locations) {
            locationDao.Delete(location.getLocationId());
        }
        List<Organization> organizations = organizationDao.ReadAll();
        for (Organization organization : organizations) {
            organizationDao.Delete(organization.getOrganizationId());
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of Create method, of class heroDaoImpl.
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

        Hero fromDao = heroDao.ReadById(hero.getHeroId());

        assertEquals(hero, fromDao);
    }

    /**
     * Test of ReadAll method, of class heroDaoImpl.
     */
    @Test
    public void testReadAll() {
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);

        Superpower power2 = new Superpower();
        power2.setPower("Test Power 2");
        power2 = superpowerDao.Create(power2);

        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test MemberType");
        hero = heroDao.Create(hero);

        Hero hero2 = new Hero();
        hero2.setName("Testing Name");
        hero2.setDescription("Test Description 2");
        hero2.setSuperpower(power2);
        hero2.setMembertype("Test MemberType 2");
        hero2 = heroDao.Create(hero2);

        List<Hero> heroes = heroDao.ReadAll();
        
        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
    }

    /**
     * Test of ReadById method, of class heroDaoImpl.
     */
    @Test
    public void testReadById() {
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);

        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test MemberType");
        hero = heroDao.Create(hero);

        Hero fromDao = heroDao.ReadById(hero.getHeroId());

        assertEquals(hero, fromDao);
       
    }

    /**
     * Test of Update method, of class heroDaoImpl.
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

        Hero fromDao = heroDao.ReadById(hero.getHeroId());
        assertEquals(hero, fromDao);

        hero.setName("New Test First");
        heroDao.Update(hero);

        assertNotEquals(hero, fromDao);

        fromDao = heroDao.ReadById(hero.getHeroId());

        assertEquals(hero, fromDao);

    }

    /**
     * Test of Delete method, of class heroDaoImpl.
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

        Hero fromDao = heroDao.ReadById(hero.getHeroId());
        assertEquals(hero, fromDao);

        heroDao.Delete(hero.getHeroId());

        fromDao = heroDao.ReadById(hero.getHeroId());
        assertNull(fromDao);
    }

   
    @Test
    public void testAssociateOrganization() {
        // tested above
    }

    /**
     * Test of readSightingByHero method, of class heroDaoImpl.
     */
    @Test
    public void testReadSightingByHero() {
        //tested above
    }

    /**
     * Test of associateSighting method, of class heroDaoImpl.
     */
    @Test
    public void testAssociateSighting() {
        // tested above
    }

}
