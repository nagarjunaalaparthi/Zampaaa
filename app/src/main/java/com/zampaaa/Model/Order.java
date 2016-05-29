package com.zampaaa.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiran on 29/5/16.
 */
public class Order {

    private String order_id = "";
    private String order_date = "";
    private String order_time = "";
    private String order_by_user_id = "";
    private List<Item> order_items = new ArrayList<>();

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_by_user_id() {
        return order_by_user_id;
    }

    public void setOrder_by_user_id(String order_by_user_id) {
        this.order_by_user_id = order_by_user_id;
    }

    public List<Item> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<Item> order_items) {
        this.order_items = order_items;
    }
}
