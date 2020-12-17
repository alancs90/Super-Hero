/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;
import com.sg.hero.Dao.heroDaoImpl;
import com.sg.hero.Dao.superpowerDaoImpl;
import com.sg.hero.Dto.Superpower;
import java.util.List;
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
public class SuperPowerController {
    @Autowired
    heroDaoImpl heroDao;
    @Autowired
    superpowerDaoImpl superpowerDao;
    @GetMapping("superpowers")
    public String displaySuperpower(Model model) {
        List<Superpower> superpowers = superpowerDao.ReadAll();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    @PostMapping("addSuperpower")
    public String addSuperpower(String power) {
        Superpower superPower = new Superpower();
        superPower.setPower(power);
        superpowerDao.Create(superPower);
        return  "redirect:/superpowers";
    }
    @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model) {
        Superpower superpower = superpowerDao.ReadById(id);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }
    @PostMapping("editSuperpower")
    public String performEditPower(Superpower superpower) {
        superpowerDao.Update(superpower);
        return "redirect:/superpowers";
    }
}