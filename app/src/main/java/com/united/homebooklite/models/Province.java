package com.united.homebooklite.models;

public class Province {
    String name,id,country_id;

    public Province() {
    }

    public Province(String id, String name, String country_id) {
        this.id = id;
        this.country_id = country_id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
