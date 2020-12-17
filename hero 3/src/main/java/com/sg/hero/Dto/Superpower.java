/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dto;

import java.util.Objects;

/**
 *
 * @author michaelrodriguez
 */
public class Superpower {
    private int superPowerId;
    private String Power; 

    public int getSuperPowerId() {
        return superPowerId;
    }

    public void setSuperPowerId(int superPowerId) {
        this.superPowerId = superPowerId;
    }

    public String getPower() {
        return Power;
    }

    public void setPower(String Power) {
        this.Power = Power;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.superPowerId;
        hash = 31 * hash + Objects.hashCode(this.Power);
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
        final Superpower other = (Superpower) obj;
        if (this.superPowerId != other.superPowerId) {
            return false;
        }
        if (!Objects.equals(this.Power, other.Power)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Superpower{" + "superPowerId=" + superPowerId + ", Power=" + Power + '}';
    }
    
    
}
