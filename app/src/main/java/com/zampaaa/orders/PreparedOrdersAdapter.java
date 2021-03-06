package com.zampaaa.orders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zampaaa.MainActivity;
import com.zampaaa.Model.Item;
import com.zampaaa.Model.Order;
import com.zampaaa.R;

import java.util.ArrayList;

/**
 * Created by Softapt on 29/05/2016.
 */
public class PreparedOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private PreparedOrders preparedOrders;
    private ArrayList<Order> ordersList = new ArrayList<>();

    public PreparedOrdersAdapter(PreparedOrders activity, ArrayList<Order> items) {
        this.preparedOrders = activity;
        this.ordersList = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_prepated_list_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final OrderViewHolder OrderViewHolder = (OrderViewHolder) holder;
        if (ordersList != null) {
            Order order = ordersList.get(position);
            OrderViewHolder.orderId.setText(order.getOrderId());
            OrderViewHolder.status.setVisibility(View.VISIBLE);
//            OrderViewHolder.status.setText("status :\n" + order.getStatus());
        }
        OrderViewHolder.layout.
                setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           AlertDialog.Builder builder = new AlertDialog.Builder(preparedOrders.getActivity());
                                           builder.setTitle("Complete");
                                           builder.setMessage("Is This order delivered?");
                                           builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialogInterface, int i) {
                                                   OrderViewHolder.status.setText("status :\n" + "delivered successfully");
                                                   dialogInterface.dismiss();
                                               }
                                           });
                                           builder.setNegativeButton("No", null);
                                           builder.create();
                                           builder.show();
                                       }

                                   }

                );
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
        public TextView status;
        public RelativeLayout layout;

        public OrderViewHolder(View view) {
            super(view);
            orderId = (TextView) view.findViewById(R.id.orderId);
            status = (TextView) view.findViewById(R.id.status);
            layout = (RelativeLayout) view.findViewById(R.id.detailslayout);
        }

    }

}
