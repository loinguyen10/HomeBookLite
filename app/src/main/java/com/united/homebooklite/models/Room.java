package com.united.homebooklite.models;

public class Room {
    int id, property_id,size,people, available, bed, room;
    String type,quality, amenities;
    int price;

    public Room() {
    }

    public Room(int id, String quality, String type, int property_id, int size, int people,int bed, int room, String amenities, int price, int available) {
        this.id = id;
        this.property_id = property_id;
        this.size = size;
        this.quality = quality;
        this.bed = bed;
        this.room = room;
        this.people = people;
        this.type = type;
        this.amenities = amenities;
        this.price = price;
        this.available = available;
    }

    public Room(String quality, String type, int property_id, int size, int people,int bed, int room, String amenities, int price,int available) {
        this.property_id = property_id;
        this.size = size;
        this.quality = quality;
        this.people = people;
        this.bed = bed;
        this.room = room;
        this.type = type;
        this.amenities = amenities;
        this.price = price;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
