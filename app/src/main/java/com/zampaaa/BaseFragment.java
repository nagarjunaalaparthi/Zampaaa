package com.zampaaa;

import android.support.v4.app.Fragment;

import com.zampaaa.Model.Order;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class BaseFragment extends Fragment {

    public ArrayList<Order> getApprovedOrders() {
        return approvedOrders;
    }

    public void setApprovedOrders(ArrayList<Order> approvedOrders) {
        this.approvedOrders = approvedOrders;
    }

    public ArrayList<Order> approvedOrders = new ArrayList<>();

    public ArrayList<Order> preparedOrders = new ArrayList<>();

    public ArrayList<Order> getPreparedOrders() {
        return preparedOrders;
    }

    public void setPreparedOrders(ArrayList<Order> preparedOrders) {
        this.preparedOrders = preparedOrders;
    }
}
