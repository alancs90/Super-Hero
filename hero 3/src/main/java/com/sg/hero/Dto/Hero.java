/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dto;

import java.util.Objects;

/**
 *
 * @author alanc
 */
public class Hero {

    private int HeroId;
    private String Name;
    private String Description;
    private Superpower superpower;
    // potentially put the superpower DTO as a field *** 
    // SUPER POWER IS GOING TO LOOK LIKE TEACHER ->  superpower(child) -> Hero (Parent) 

    private String membertype;

    public int getHeroId() {
        return HeroId;
    }

    public void setHeroId(int HeroId) {
        this.HeroId = HeroId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

  
    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.HeroId;
        hash = 67 * hash + Objects.hashCode(this.Name);
        hash = 67 * hash + Objects.hashCode(this.Description);
        hash = 67 * hash + Objects.hashCode(this.superpower);
        hash = 67 * hash + Objects.hashCode(this.membertype);
        return hash;
    }

    @Override
    public String toString() {
        return "Hero{" + "HeroId=" + HeroId + ", Name=" + Name + ", Description=" + Description + ", superpower=" + superpower + ", membertype=" + membertype + '}';
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
        final Hero other = (Hero) obj;
        if (this.HeroId != other.HeroId) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.membertype, other.membertype)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        return true;
    }

   

  
}
