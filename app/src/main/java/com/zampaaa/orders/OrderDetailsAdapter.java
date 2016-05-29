package com.zampaaa.orders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zampaaa.Items.AddItemsToMenuActivity;
import com.zampaaa.MainActivity;
import com.zampaaa.Model.Item;
import com.zampaaa.R;
import com.zampaaa.RestaurentFragment;

import java.util.ArrayList;

/**
 * Created by Softapt on 29/05/2016.
 */
public class OrderDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OrderDetailsActivity activity;
    private ArrayList<Item> items = new ArrayList<>();

    public OrderDetailsAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    public OrderDetailsAdapter(OrderDetailsActivity activity, ArrayList<Item> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if (items != null) {
            Item item = items.get(position);
            if (item.getVegType().equalsIgnoreCase("veg")) {
                itemViewHolder.veg.setImageResource(R.drawable.veg);
            } else {
                itemViewHolder.veg.setImageResource(R.drawable.non_veg);
            }
            itemViewHolder.price.setText("\u20B9" + item.getPrice().trim());
            itemViewHolder.name.setText(item.getName().trim());
        }
        itemViewHolder.delete.setVisibility(View.INVISIBLE);
        itemViewHolder.edit.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        View view = null;
        public TextView name;
        public TextView price;
        public ImageView veg;
        public ImageView edit;
        public ImageView delete;

        public ItemViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.itemName);
            price = (TextView) view.findViewById(R.id.price);
            veg = (ImageView) view.findViewById(R.id.vegImage);
            edit = (ImageView) view.findViewById(R.id.editImage);
            delete = (ImageView) view.findViewById(R.id.deleteImage);
        }

    }
}
