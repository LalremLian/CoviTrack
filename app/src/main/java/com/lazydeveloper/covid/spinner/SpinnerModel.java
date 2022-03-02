package com.lazydeveloper.covid.spinner;

public class SpinnerModel {

    String name;
    String app_logo;

    public SpinnerModel() {
    }

    public SpinnerModel(String name, String app_logo) {
        this.name = name;
        this.app_logo = app_logo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getApp_logo() {
        return app_logo;
    }

    public void setApp_logo(String app_logo) {
        this.app_logo = app_logo;
    }

    @Override
    public String toString() {
        return "SpinnerModel{" +
                "name='" + name + '\'' +
                ", app_logo='" + app_logo + '\'' +
                '}';
    }
}
