package com.obsidiam.util.model;

/**
 * User model.
 */
public class User {
    private UserType type;
    private int index;
    private String name;
    private String surName;
    private String addresss;

    public User(){}

    public User(UserType type, int index, String name, String surName, String address){
           this.type = type;
           this.surName = surName;
           this.addresss = address;
           this.index = index;
           this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }



    @Override
    public String toString(){
        return type.toString()+": "+name+" "+surName+" "+addresss;
    }
}
