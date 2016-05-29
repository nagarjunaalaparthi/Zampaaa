package com.zampaaa.orders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zampaaa.BaseFragment;
import com.zampaaa.Model.Item;
import com.zampaaa.Model.Order;
import com.zampaaa.R;
import com.zampaaa.Utils.SharedPreferenceUtils;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class RequestedOrderFragment extends BaseFragment {

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RequestedOrdersAdapter adapter;
    ArrayList<Order> requestedOrders = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
            requestedOrders = getDummyData();
            initViews(view);
        } else {

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(SharedPreferenceUtils.readBoolean(getActivity(),"orderKey",false)){
            SharedPreferenceUtils.writeBoolean(getActivity(),"orderKey",false);
            delete(SharedPreferenceUtils.readString(getActivity(),"orderId",""));
        }
    }
    public ArrayList<Order> getDummyData() {
        ArrayList<Order> orders = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            Order order = new Order();
            order.setOrderId(i + "");
            order.setOrderPlacedby("user" + i);
            order.setMobileNumber(i + "-000000000");
            order.setStatus("requested");
            ArrayList<Item> items = new ArrayList<>();
            int amt = 0;
            for (int j = 1; j < 6; j++) {
                Item item = new Item();
                item.setName("item" + j);
                item.setPrice(10 * j + "");
                if (j % 2 == 0) {
                    item.setVegType("veg");
                } else {
                    item.setVegType("non-veg");
                }
                amt = amt + (10 * j);
                items.add(item);
            }
            order.setItems(items);
            order.setTotalAmt(amt + "");
            orders.add(order);
        }
        return orders;
    }
    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RequestedOrdersAdapter(RequestedOrderFragment.this, requestedOrders);
        recyclerView.setAdapter(adapter);
    }
    public void delete(String id){
        for(int i=0;i<requestedOrders.size();i++){
            Order order= requestedOrders.get(i);
            if(id.equalsIgnoreCase(order.getOrderId())){
                approvedOrders.add(order);
                requestedOrders.remove(order);
                break;
            }
        }
        adapter = new RequestedOrdersAdapter(RequestedOrderFragment.this, requestedOrders);
        recyclerView.setAdapter(adapter);
    }
}
