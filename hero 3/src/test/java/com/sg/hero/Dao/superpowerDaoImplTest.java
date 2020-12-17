/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dao;

import com.sg.hero.Dto.Hero;
import com.sg.hero.Dto.Superpower;
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
 * @author user
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class superpowerDaoImplTest {

    @Autowired
    superpowerDaoImpl superpowerDao;
    @Autowired
    heroDaoImpl heroDao;

    public superpowerDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Superpower> powers = superpowerDao.ReadAll();
        for (Superpower power : powers) {
            superpowerDao.Delete(power.getSuperPowerId());
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
     * Test of Create method, of class superpowerDaoImpl.
     */
    @Test
    public void testCreateAndGet() {
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);
        Superpower fromDao = superpowerDao.ReadById(power.getSuperPowerId());
        assertEquals(power, fromDao);
    }

    /**
     * Test of ReadAll method, of class superpowerDaoImpl.
     */
    @Test
    public void testReadAll() {
        Superpower power1 = new Superpower();
        power1.setPower("Test Power");
        power1 = superpowerDao.Create(power1);
        Superpower power2 = new Superpower();
        power2.setPower("Test Power 2");
        power2 = superpowerDao.Create(power2);
        List<Superpower> powers = superpowerDao.ReadAll();
        assertEquals(2, powers.size());
        assertTrue(powers.contains(power1));
        assertTrue(powers.contains(power2));
    }

    /**
     * Test of Update method, of class superpowerDaoImpl.
     */
    @Test
    public void testUpdate() {
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);
        Superpower fromDao = superpowerDao.ReadById(power.getSuperPowerId());
        assertEquals(power, fromDao);
        power.setPower("New Test Power");
        superpowerDao.Update(power);
        assertNotSame(power, fromDao);
        fromDao = superpowerDao.ReadById(power.getSuperPowerId());
        assertEquals(power, fromDao);
    }

    /**
     * Test of Delete method, of class superpowerDaoImpl.
     */
    @Test
    public void testDelete() {
        Superpower power = new Superpower();
        power.setPower("Test Power");
        power = superpowerDao.Create(power);
        Superpower fromDao = superpowerDao.ReadById(power.getSuperPowerId());
        assertEquals(power, fromDao);
        superpowerDao.Delete(power.getSuperPowerId());
        fromDao = superpowerDao.ReadById(power.getSuperPowerId());
        assertNull(fromDao);
    }
}
