package com.lazydeveloper.covid.model;

public class CasesModel {
    String updated;
    String country;
    String cases;
    String todayCases;
    String deaths;
    String todayDeaths;
    String recovered;
    String todayRecovered;
    String active;
    String oneCasePerPeople;
    String critical;
    String oneTestPerPeople;
    Float criticalPerOneMillion;
    CountryInfo countryInfo;

    public CasesModel(String updated, String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String oneCasePerPeople, String critical, String oneTestPerPeople, Float criticalPerOneMillion, CountryInfo countryInfo) {
        this.updated = updated;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.oneCasePerPeople = oneCasePerPeople;
        this.critical = critical;
        this.oneTestPerPeople = oneTestPerPeople;
        this.criticalPerOneMillion = criticalPerOneMillion;
        this.countryInfo = countryInfo;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getOneCasePerPeople() {
        return oneCasePerPeople;
    }

    public void setOneCasePerPeople(String oneCasePerPeople) {
        this.oneCasePerPeople = oneCasePerPeople;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getOneTestPerPeople() {
        return oneTestPerPeople;
    }

    public void setOneTestPerPeople(String oneTestPerPeople) {
        this.oneTestPerPeople = oneTestPerPeople;
    }

    public Float getCriticalPerOneMillion() {
        return criticalPerOneMillion;
    }

    public void setCriticalPerOneMillion(Float criticalPerOneMillion) {
        this.criticalPerOneMillion = criticalPerOneMillion;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    @Override
    public String toString() {
        return "CasesModel{" +
                "updated='" + updated + '\'' +
                ", country='" + country + '\'' +
                ", cases='" + cases + '\'' +
                ", todayCases='" + todayCases + '\'' +
                ", deaths='" + deaths + '\'' +
                ", todayDeaths='" + todayDeaths + '\'' +
                ", recovered='" + recovered + '\'' +
                ", todayRecovered='" + todayRecovered + '\'' +
                ", active='" + active + '\'' +
                ", oneCasePerPeople='" + oneCasePerPeople + '\'' +
                ", critical='" + critical + '\'' +
                ", oneTestPerPeople='" + oneTestPerPeople + '\'' +
                ", criticalPerOneMillion='" + criticalPerOneMillion + '\'' +
                ", countryInfo=" + countryInfo +
                '}';
    }
}
