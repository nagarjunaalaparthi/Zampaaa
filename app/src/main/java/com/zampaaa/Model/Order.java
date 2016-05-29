package com.zampaaa.Model;

import java.util.ArrayList;

/**
 * Created by Softapt on 29/05/2016.
 */
public class Order {
    ArrayList<Item> items = new ArrayList<>();
    String status = "";
    String orderId = "";
    String totalAmt = "";
    String comments = "";
    String orderPlacedby = "";

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderPlacedby() {
        return orderPlacedby;
    }

    public void setOrderPlacedby(String orderPlacedby) {
        this.orderPlacedby = orderPlacedby;
    }
}
