/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.controller;

import com.sg.hero.Dao.heroDaoImpl;
import com.sg.hero.Dao.locationDaoImpl;
import com.sg.hero.Dao.organizationDaoImpl;
import com.sg.hero.Dao.sightingDaoImpl;
import com.sg.hero.Dao.superpowerDaoImpl;
import com.sg.hero.Dto.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author michaelrodriguez
 */
@Controller
public class LocationController {

    @Autowired
    sightingDaoImpl sightingDao;

    @Autowired
    organizationDaoImpl organizationDao;

    @Autowired
    heroDaoImpl heroDao;
    @Autowired
    locationDaoImpl locationDao;

    @Autowired
    superpowerDaoImpl superpowerDao;

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.ReadAll();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(String locationDescription, String street, String city,
            String state, String zip, double lattitude, double longitude) {
        Location location = new Location();
        location.setLocationDescription(locationDescription);
        location.setStreet(street);
        location.setCity(city);
        location.setState(state);
        location.setZip(zip);
        location.setLattitude(lattitude);
        location.setLattitude(lattitude);

        locationDao.Create(location);

        return "redirect:/locations";

    }

    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationDao.Delete(id);
        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model) {
        Location location = locationDao.ReadById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(Location location) {
        locationDao.Update(location);
        return "redirect:/locations";
    }

}
