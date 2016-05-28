package com.zampaaa.Model;

import java.io.Serializable;

/**
 * Created by Softapt on 28/05/2016.
 */
public class Item implements Serializable {
    String name = "";
    String price = "";
    String category = "";
    String vegType = "";
    String id = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVegType() {
        return vegType;
    }

    public void setVegType(String vegType) {
        this.vegType = vegType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
