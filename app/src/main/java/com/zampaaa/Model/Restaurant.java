package com.zampaaa.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiran on 29/5/16.
 */
public class Restaurant {
    private String name;
    private String addr1;
    private String city;
    private String state;
    private String pincode;
    private String openTime;
    private String closeTime;
    private boolean vegOrNonVeg;

    public Restaurant(String name, String addr1, String city, String state, String pincode, String openTime, String closeTime, boolean vegOrNonVeg) {
        this.name = name;
        this.addr1 = addr1;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.vegOrNonVeg = vegOrNonVeg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public boolean isVegOrNonVeg() {
        return vegOrNonVeg;
    }

    public void setVegOrNonVeg(boolean vegOrNonVeg) {
        this.vegOrNonVeg = vegOrNonVeg;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("address", addr1);
        result.put("city", city);
        result.put("state", state);
        result.put("pincode", pincode);
        result.put("open_time", openTime);
        result.put("close_time", closeTime);
        result.put("veg_nonVeg", vegOrNonVeg);

        return result;
    }

}
