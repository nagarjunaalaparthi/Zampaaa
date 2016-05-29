package com.zampaaa.Analytics;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zampaaa.Model.User;
import com.zampaaa.R;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private UserAnalytics activity;
    private ArrayList<User> users = new ArrayList<>();


    public UserAdapter(UserAnalytics activity, ArrayList<User> items) {
        this.activity = activity;
        this.users = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_reports_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        if (users != null) {
            User user = users.get(position);
            userViewHolder.name.setText(user.getName().trim());
            userViewHolder.number.setText(user.getNumber().trim());
            userViewHolder.orders.setText(user.getOrders().trim());
        }
    }

    @Override
    public int getItemCount() {
        if (users != null) {
            return users.size();
        } else {
            return 20;
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView number;
        public TextView orders;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            number = (TextView) view.findViewById(R.id.number);
            orders = (TextView) view.findViewById(R.id.orders);
        }

    }
}
