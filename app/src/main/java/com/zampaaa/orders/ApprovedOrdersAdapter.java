package com.zampaaa.orders;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
public class ApprovedOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ApprovedOrders approvedOrders;
    private ArrayList<Order> ordersList = new ArrayList<>();

    public ApprovedOrdersAdapter(ApprovedOrders activity, ArrayList<Order> items) {
        this.approvedOrders = activity;
        this.ordersList = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_request_list_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final OrderViewHolder OrderViewHolder = (OrderViewHolder) holder;
        if (ordersList != null) {
            final Order order = ordersList.get(position);
            OrderViewHolder.orderId.setText(order.getOrderId());
            OrderViewHolder.orderId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(approvedOrders.getActivity());
                    builder.setTitle("Preapared");
                    builder.setMessage("Is This order prepared?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            approvedOrders.preparedOrders.add(order);
                            approvedOrders.approvedOrders.remove(order);
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.create();
                    builder.show();
                }
            });
        }
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
