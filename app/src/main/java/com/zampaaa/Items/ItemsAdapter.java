package com.zampaaa.Items;

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

import com.zampaaa.MainActivity;
import com.zampaaa.Model.Item;
import com.zampaaa.R;
import com.zampaaa.RestaurentFragment;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RestaurentFragment activity;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private  ArrayList<Item> items = new ArrayList<>();

    public ItemsAdapter(RestaurentFragment activity ) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if(items!=null){
            Item item = items.get(position);
            if(item.getVegType().equalsIgnoreCase("veg")){
                itemViewHolder.veg.setImageResource(R.drawable.veg);
            }else{
                itemViewHolder.veg.setImageResource(R.drawable.non_veg);
            }
            itemViewHolder.price.setText("\u20B9"+item.getPrice().trim());
            itemViewHolder.name.setText(item.getName().trim());
        }
        itemViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = ((MainActivity)activity.getActivity()).dbHelper.getItem(items.get(position).getId());
                if(item!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item",item);
                    Intent editIntent = new Intent(activity.getActivity(),AddItemsToMenuActivity.class);
                    editIntent.putExtras(bundle);
                    activity.startActivity(editIntent);
                }
            }
        });
        itemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity!=null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity.getActivity());
                    builder.setTitle("Delete");
                    builder.setMessage("You want to remove this item from the list?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(activity!=null){
                                ((MainActivity)activity.getActivity()).dbHelper.deleteItem(items.get(position).getId());
                                activity.setDataToAdapter();
                            }
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("No",null);
                    builder.create();
                    builder.show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if(items!=null) {
            return items.size();
        }else{
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
