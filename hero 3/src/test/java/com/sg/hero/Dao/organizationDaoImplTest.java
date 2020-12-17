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
import java.util.ArrayList;
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
public class organizationDaoImplTest {
    @Autowired
    superpowerDaoImpl powerDao;
    
    @Autowired
    heroDaoImpl heroDao;
    
    @Autowired
    sightingDaoImpl sightingDao;
    
    @Autowired
    organizationDaoImpl organizationDao;
    


    @BeforeClass
    public static void setUpClass() {
    }
    @AfterClass
    public static void tearDownClass() {
    }
    @BeforeEach
    public void setUp() {
        List<Organization> organizations = organizationDao.ReadAll();
        for(Organization organization : organizations) {
            organizationDao.Delete(organization.getOrganizationId());
        }
        List<Hero> heroes = heroDao.ReadAll();
        for (Hero hero : heroes) {
            heroDao.Delete(hero.getHeroId());
        }
        List<Superpower> powers = powerDao.ReadAll();
        for(Superpower superpower : powers) {
            powerDao.Delete(superpower.getSuperPowerId());
        }
        List<Sighting> sightings = sightingDao.ReadAll();
        for(Sighting sighting : sightings) {
            sightingDao.Delete(sighting.getSightingId());
        }
    }
    @After
    public void tearDown() {
    }
    /**
     * Test of Create method, of class organizationDaoImpl.
     */
    @Test
    public void testCreate() {
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = powerDao.Create(power);
        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(power);
        hero.setMembertype("Test type");
        hero = heroDao.Create(hero);
        List<Hero> heroes = heroDao.ReadAll();
//        heroes.add(hero);
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescriptions("Test Description");
        organization.setStreet("Test Street");
        organization.setCity("Test City");
        organization.setState("NY");
        organization.setZip("10345");
        organization.setMemberType("Test Type");
        organization.setHeroes(heroes);
        organization = organizationDao.Create(organization);
        Organization fromDao = organizationDao.ReadById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
    }
    /**
     * Test of ReadAll method, of class organizationDaoImpl.
     */
    @Test
    public void testReadAll() {
        Organization organization = new Organization();
        organization.setName("Test Name");
        organization.setDescriptions("Test Description");
        organization.setStreet("Test Street");
        organization.setCity("Test City");
        organization.setState("NY");
        organization.setZip("10293");
        organization.setMemberType("Test Type");
        organization.setHeroes(new ArrayList<Hero>());
        organization = organizationDao.Create(organization);
        Organization organization2 = new Organization();
        organization2.setName("Test Name2");
        organization2.setDescriptions("Test Description 2");
        organization2.setStreet("Test Street 2");
        organization2.setCity("Test City 2");
        organization2.setState("TX");
        organization2.setZip("19284");
        organization2.setMemberType("Test MemberType 2");
        organization2.setHeroes(new ArrayList<Hero>());
        organization2 = organizationDao.Create(organization2);
        List<Organization> organizations = organizationDao.ReadAll();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
    }
    /**
     * Test of Update method, of class organizationDaoImpl.
     */
    @Test
    public void testUpdate() {
        Organization  organization = new Organization();
        organization.setName("Test Name");
        organization.setDescriptions("Test Description");
        organization.setStreet("Test Street");
        organization.setCity("Test City");
        organization.setState("NY");
        organization.setZip("33853");
        organization.setHeroes(new ArrayList<Hero>());
        organization.setMemberType("Test Membertype");
        organization = organizationDao.Create(organization);
        Organization fromDao = organizationDao.ReadById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
        organization.setName("New Test Name");
        organizationDao.Update(organization);
        assertNotSame(organization, fromDao);
        fromDao = organizationDao.ReadById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
    }
    /**
     * Test of Delete method, of class organizationDaoImpl.
     */
    @Test
    public void testDelete() {
       Organization organization = new Organization();
       organization.setName("Test Name");
       organization.setDescriptions("Test Description");
       organization.setStreet("Test Street");
       organization.setCity("Test City");
       organization.setState("NY");
       organization.setZip("29458");
       organization.setHeroes(new ArrayList<Hero>());
       organization.setMemberType("Test Type");
       organization = organizationDao.Create(organization);
       Organization fromDao = organizationDao.ReadById(organization.getOrganizationId());
       assertEquals(organization, fromDao);
       organizationDao.Delete(organization.getOrganizationId());
       fromDao = organizationDao.ReadById(organization.getOrganizationId());
       assertNull(fromDao);
    }
}

