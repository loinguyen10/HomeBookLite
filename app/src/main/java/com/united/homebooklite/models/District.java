package com.united.homebooklite.models;

public class District {
    String id,name,province_id;

    public District() {
    }

    public District(String id, String name, String province_id) {
        this.id = id;
        this.province_id = province_id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
