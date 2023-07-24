package com.united.homebooklite.models;

public class Property {
    int id,owner_id,rating;
    String name,description,address,district,province,country,amenities,type;

    public Property() {
    }

    public Property(int id, String name, String description, String type, String address, String district, String province, String country, String amenities,int rating, int owner_id) {
        this.id = id;
        this.owner_id = owner_id;
        this.rating = rating;
        this.type = type;
        this.name = name;
        this.description = description;
        this.address = address;
        this.district = district;
        this.province = province;
        this.country = country;
        this.amenities = amenities;
    }

    public Property(String name, String description, String type, String address, String district, String province, String country, String amenities, int rating, int owner_id) {
        this.owner_id = owner_id;
        this.rating = rating;
        this.type = type;
        this.name = name;
        this.description = description;
        this.address = address;
        this.district = district;
        this.province = province;
        this.country = country;
        this.amenities = amenities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
