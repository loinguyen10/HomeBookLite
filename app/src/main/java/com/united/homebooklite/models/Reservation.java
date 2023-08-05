package com.united.homebooklite.models;

public class Reservation {
    int id, account_id, room_id, room, people, cost, status;
    String checkin_date, checkout_date;

    public Reservation() {
    }

    public Reservation(int id, int account_id, int room_id, String checkin_date, String checkout_date, int room, int people, int cost, int status) {
        this.id = id;
        this.account_id = account_id;
        this.room_id = room_id;
        this.room = room;
        this.people = people;
        this.cost = cost;
        this.status = status;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(String checkin_date) {
        this.checkin_date = checkin_date;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }
}
