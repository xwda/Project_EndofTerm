package com.example.a19093.project_endofterm;

public class City {
    private String city;
    private String admin_district;
    private String province;
    City(){
        this.city = "泾川";
        this.admin_district = "平凉";
        this.province = "甘肃";
    }
    public String getCity() {
        return city;
    }
    public String getProvince() {
        return province;
    }
    public String getAdmin_district() {
        return admin_district;
    }

    public void setCity(String city){
        this.city = city;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public void setAdmin_district(String admin_district){
        this.admin_district = admin_district;
    }

    public void init(String city, String admin_district, String province){
        this.city = city;
        this.admin_district = admin_district;
        this.province = province;
    }

    public String toString1(){
        return city + " " + admin_district + " " + province;
    }

    public String toString2(){
        return city + "," + admin_district;
    }
}
