/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.igordeoliveirasa.correios;

/**
 *
 * @author igor
 */
public class ZIPCodeAddress {
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String stateAcronym;

    public String getStateAcronym() {
        return stateAcronym;
    }

    public void setStateAcronym(String stateAcronym) {
        this.stateAcronym = stateAcronym;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString() {
        return zipCode + ": " + street + ", " + neighborhood + ", " + city + ", " + state + " - " + stateAcronym + ", " + country;
    }
}
