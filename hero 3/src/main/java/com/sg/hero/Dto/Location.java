/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hero.Dto;

/**
 *
 * @author alanc
 */
public class Location {

    private int LocationId;
    private String LocationDescription;
    private double Lattitude;
    private double Longitude;
    private String Street;
    private String City;
    private String State;
    private String Zip;

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int LocationId) {
        this.LocationId = LocationId;
    }

    public String getLocationDescription() {
        return LocationDescription;
    }

    public void setLocationDescription(String LocationDescription) {
        this.LocationDescription = LocationDescription;
    }


    public double getLattitude() {
        return Lattitude;
    }

    public void setLattitude(double Lattitude) {
        this.Lattitude = Lattitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.LocationId;
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
        final Location other = (Location) obj;
        if (this.LocationId != other.LocationId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "LocationId=" + LocationId + ", LocationDescription=" + LocationDescription + ", Lattitude=" + Lattitude + ", Longitude=" + Longitude + ", Street=" + Street + ", City=" + City + ", State=" + State + ", Zip=" + Zip + '}';
    }

}
