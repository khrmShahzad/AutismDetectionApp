package com.autismdetectionapp.model;

public class Parent {

    String key, name, email, password, contact, cnic;

    public Parent() {
    }

    public Parent(String name, String email, String password, String contact, String cnic) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.cnic = cnic;
    }

    public Parent(String key, String name, String email, String password, String contact, String cnic) {
        this.key = key;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.cnic = cnic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
}
