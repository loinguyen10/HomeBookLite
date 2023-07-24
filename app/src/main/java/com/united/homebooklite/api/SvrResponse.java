package com.united.homebooklite.api;

import com.united.homebooklite.models.Account;
import com.united.homebooklite.models.Country;
import com.united.homebooklite.models.District;
import com.united.homebooklite.models.Property;
import com.united.homebooklite.models.Province;
import com.united.homebooklite.models.Room;

public class SvrResponse {

    private Country[] countries;
    private Province[] provinces;
    private District[] districts;

    private Account account;
    private Account[] accounts;

    private Room room;
    private Room[] rooms;
    private Property property;
    private Property[] properties;
    private String message;

    public Country[] getCountries() {
        return countries;
    }

    public Province[] getProvinces() {
        return provinces;
    }

    public District[] getDistricts() {
        return districts;
    }

    public Account getAccount() {
        return account;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public Property getProperty() {
        return property;
    }

    public Room getRoom() {
        return room;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Property[] getProperties() {
        return properties;
    }

    public String getMessage() {
        return message;
    }
}
