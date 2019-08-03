package com.nixlord.dunzo.model;

import java.util.HashMap;

public class Seller {
    String id;
    String name;
    String address;
    String phoneNo;
    HashMap<String, String> productIDCountMap;

    public Seller() {

    }

    public Seller(String id, String name, String address, String phoneNo, HashMap<String, String> productIDCountMap) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.productIDCountMap = productIDCountMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public HashMap<String, String> getProductIDCountMap() {
        return productIDCountMap;
    }

    public void setProductIDCountMap(HashMap<String, String> productIDCountMap) {
        this.productIDCountMap = productIDCountMap;
    }
}
