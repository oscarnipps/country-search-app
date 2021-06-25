package com.example.countrysearch.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Country {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String countryName;

    private String callingCode;

    private String countryCapital;

    private Integer population;

    private String timezone;

    private String language;

    private String currency;

    private String flag;

    public Country(int id, String countryName, String callingCode, String countryCapital, Integer population, String timezone, String language, String currency, String flag) {
        this.id = id;
        this.countryName = countryName;
        this.callingCode = callingCode;
        this.countryCapital = countryCapital;
        this.population = population;
        this.timezone = timezone;
        this.language = language;
        this.currency = currency;
        this.flag = flag;
    }

    @Ignore
    public Country( String countryName, String callingCode, String countryCapital, Integer population, String timezone, String language, String currency, String flag) {
        this.countryName = countryName;
        this.callingCode = callingCode;
        this.countryCapital = countryCapital;
        this.population = population;
        this.timezone = timezone;
        this.language = language;
        this.currency = currency;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode = callingCode;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
