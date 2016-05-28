package com.zampaaa.Items;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zampaaa.R;

/**
 * Created by Softapt on 28/05/2016.
 */
public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        View view = null;
        public TextView name;
        public TextView price;
        public ImageView veg;
        public ImageView edit;

        public ItemViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.itemName);
            price = (TextView) view.findViewById(R.id.price);
            veg = (ImageView) view.findViewById(R.id.vegImage);
            edit = (ImageView) view.findViewById(R.id.editImage);
        }

    }
}
