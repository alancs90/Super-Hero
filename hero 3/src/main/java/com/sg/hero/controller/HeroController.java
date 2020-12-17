/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.Dao.heroDaoImpl;
import com.sg.hero.Dao.superpowerDaoImpl;
import com.sg.hero.Dto.Hero;
import com.sg.hero.Dto.Superpower;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author alanc
 */
@Controller
public class HeroController {

    @Autowired
    heroDaoImpl heroDao;

    @Autowired
    superpowerDaoImpl superpowerDao;

    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heroes = heroDao.ReadAll();
        model.addAttribute("heroes", heroes);
        return "heroes";
    }

    @PostMapping("addHero")
    public String addHero(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String power = request.getParameter("superPower");
        String memberType = request.getParameter("memberType");
        Superpower superpower = new Superpower();
        Hero hero = new Hero();

        hero.setName(name);
        hero.setDescription(description);
        superpower.setPower(power);
        hero.setSuperpower(superpowerDao.Create(superpower));
        hero.setMembertype(memberType);
        heroDao.Create(hero);
        return "redirect:/heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        heroDao.Delete(id);
        return "redirect:/heroes";
    }

    @GetMapping("editHero")
    public String editHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroDao.ReadById(id);
        String power = request.getParameter("superPower");
        Superpower superpower = new Superpower();
        superpower.setPower(power);
        hero.setSuperpower(superpowerDao.Create(superpower));
        model.addAttribute("hero", hero);
        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request) {
        /* 
         String name = request.getParameter("name");
        String description = request.getParameter("description");
        String power = request.getParameter("superPower");
        String memberType = request.getParameter("memberType");
        Superpower superpower = new Superpower();
        Hero hero = new Hero();

        hero.setName(name);
        hero.setDescription(description);
        superpower.setPower(power);
        hero.setSuperpower(superpowerDao.Create(superpower));
        hero.setMembertype(memberType);
        heroDao.Create(hero);
        return "redirect:/heroes";
        */
        
        
        
        
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = heroDao.ReadById(id);
        hero.setName(request.getParameter("heroName"));
        hero.setDescription(request.getParameter("heroDescription"));
        String power = request.getParameter("superPower");
        Superpower superpower = new Superpower();
        superpower.setPower(power);
        hero.setSuperpower(superpowerDao.Create(superpower));

        hero.setMembertype(request.getParameter("memberType"));
        heroDao.Update(hero);
        return "redirect:/heroes";
    }
}
