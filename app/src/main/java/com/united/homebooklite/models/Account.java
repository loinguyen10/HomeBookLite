package com.united.homebooklite.models;

public class Account {
    int id;
    String email,password,role,name,phone;

    public Account() {
    }

    public Account(int id, String email, String password, String role, String name, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.phone = phone;
    }

    public Account( String email, String password, String role, String name, String phone) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
