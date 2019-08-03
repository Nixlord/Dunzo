package com.nixlord.dunzo.model;

import java.util.HashMap;

public class Seller {
    String ID;
    String name;
    String address;
    String phoneNo;
    HashMap<String, Integer> productIDCountMap;

    public Seller(String ID, String name, String address, String phoneNo, HashMap<String, Integer> productIDCountMap) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.productIDCountMap = productIDCountMap;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public HashMap<String, Integer> getProductIDCountMap() {
        return productIDCountMap;
    }

    public void setProductIDCountMap(HashMap<String, Integer> productIDCountMap) {
        this.productIDCountMap = productIDCountMap;
    }
}
