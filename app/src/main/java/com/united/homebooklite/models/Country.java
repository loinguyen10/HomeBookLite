package com.united.homebooklite.models;

public class Country {
    String id,nameVN,nameEN;

    public Country() {
    }

    public Country(String id, String nameVN, String nameEN) {
        this.id = id;
        this.nameVN = nameVN;
        this.nameEN = nameEN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameVN() {
        return nameVN;
    }

    public void setNameVN(String nameVN) {
        this.nameVN = nameVN;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }
}
