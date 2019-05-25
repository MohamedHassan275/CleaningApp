package com.mohmedhassan.cleaningapp.Edit_ProfileUser;

public class city_intity_Country {

    String city_name;
    String City_id;


    public city_intity_Country(String city_name) {
        this.city_name = city_name;
        //this.City_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_id() {
        return City_id;
    }

    public void setCity_id(String city_id) {
        this.City_id = city_id;
    }
}
