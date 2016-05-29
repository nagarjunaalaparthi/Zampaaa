package com.zampaaa.orders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zampaaa.Model.Order;
import com.zampaaa.R;

import java.util.ArrayList;

/**
 * Created by Softapt on 29/05/2016.
 */
public class RequestedOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RequestedOrderFragment requestedOrderFragment;
    private ArrayList<Order> ordersList = new ArrayList<>();


    public RequestedOrdersAdapter(RequestedOrderFragment activity, ArrayList<Order> items) {
        this.requestedOrderFragment = activity;
        this.ordersList = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_request_list_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        if (ordersList != null) {
            Order order = ordersList.get(position);
            orderViewHolder.orderId.setText(order.getOrderId());
        }
        orderViewHolder.orderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requestedOrderFragment.getActivity(),OrderDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order",ordersList.get(position));
                requestedOrderFragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ordersList != null) {
            return ordersList.size();
        } else {
            return 20;
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderId;

        public OrderViewHolder(View view) {
            super(view);
            orderId = (TextView) view.findViewById(R.id.orderId);
        }

    }

}
