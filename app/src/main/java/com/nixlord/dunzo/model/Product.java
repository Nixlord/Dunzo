package com.nixlord.dunzo.model;

import java.util.ArrayList;

public class Product {
    String ID;
    String name;
    String type;
    String price;
    ArrayList<String> stores;

    public Product() {

    }

    public Product(String ID, String name, String type, String price, ArrayList<String> stores) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.price = price;
        this.stores = stores;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<String> getStores() {
        return stores;
    }

    public void setStores(ArrayList<String> stores) {
        this.stores = stores;
    }
}
