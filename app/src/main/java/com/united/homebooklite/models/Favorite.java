package com.united.homebooklite.models;

public class Favorite {
    int id,account_id,room_id;

    public Favorite() {
    }

    public Favorite(int id, int account_id, int room_id) {
        this.id = id;
        this.account_id = account_id;
        this.room_id = room_id;
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
}
