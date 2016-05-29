package com.zampaaa.Analytics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zampaaa.Model.Item;
import com.zampaaa.R;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ProductAnalytics activity;
    private ArrayList<Item> items = new ArrayList<>();


    public ProductAdapter(ProductAnalytics activity, ArrayList<Item> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_reports_list_item, parent, false);
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
            itemViewHolder.name.setText(item.getName().trim());
            itemViewHolder.quantity.setText(item.getSoldQuantity());
        }
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 20;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView quantity;
        public ImageView veg;

        public ItemViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.product_name);
            quantity = (TextView) view.findViewById(R.id.quantity_sold);
            veg = (ImageView) view.findViewById(R.id.typeimage);
        }

    }
}
