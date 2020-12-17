/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author alanc
 */
public class Sighting {
    // for single object -> HERO -> getHeroForSighting will query and bring back an object. 
    // then we another method to associate heroes with sighting
    // 1st it will get all sightings, -> create a list of sighting objects, then look up hero for sighting. 

    private int SightingId;
    private LocalDate Date;
    private Location location;

    private String description;
    private Hero hero;

    public int getSightingId() {
        return SightingId;
    }

    public void setSightingId(int SightingId) {
        this.SightingId = SightingId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.SightingId;
        hash = 83 * hash + Objects.hashCode(this.Date);
        hash = 83 * hash + Objects.hashCode(this.location);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.hero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.SightingId != other.SightingId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sighting{" + "SightingId=" + SightingId + ", Date=" + Date + ", location=" + location + ", description=" + description + ", hero=" + hero + '}';
    }

}
