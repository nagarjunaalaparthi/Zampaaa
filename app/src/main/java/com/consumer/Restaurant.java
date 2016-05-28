package com.consumer;

import java.io.Serializable;

/**
 * Created by sriram on 28/5/16.
 */
public class Restaurant implements Serializable{

    public Restaurant(String name, String address, int rating, int imageDrawbale) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.imageDrawbale = imageDrawbale;
    }

    private String name;
    private String address;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImageDrawbale() {
        return imageDrawbale;
    }

    public void setImageDrawbale(int imageDrawbale) {
        this.imageDrawbale = imageDrawbale;
    }

    private int rating;
    private int imageDrawbale;
}
