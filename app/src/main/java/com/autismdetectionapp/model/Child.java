package com.autismdetectionapp.model;

public class Child {

    String key, name, gender, disability, parentID;
    int age;

    public Child() {
    }

    public Child(String name, String gender, String disability, String parentID, int age) {
        this.name = name;
        this.gender = gender;
        this.disability = disability;
        this.parentID = parentID;
        this.age = age;
    }

    public Child(String key, String name, String gender, String disability, String parentID, int age) {
        this.key = key;
        this.name = name;
        this.gender = gender;
        this.disability = disability;
        this.parentID = parentID;
        this.age = age;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
