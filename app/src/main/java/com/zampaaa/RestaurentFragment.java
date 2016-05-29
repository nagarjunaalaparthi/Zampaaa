package com.zampaaa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zampaaa.Items.AddItemsToMenuActivity;
import com.zampaaa.Items.ItemsAdapter;
import com.zampaaa.Model.Item;
import com.zampaaa.Utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.Iterator;


public class RestaurentFragment extends BaseFragment implements View.OnClickListener {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton actionButton;
    private ArrayList<Item> items = new ArrayList<>();

    private DatabaseReference mDatabase;
    View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_restaurent, container, false);
            initViews(view);
            initListeners();
            showProgress();
        } else {

        }
        return view;
    }

    private void initDummyData() {

    }

    private void initListeners() {
        actionButton.setOnClickListener(this);
    }

    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onResume() {
        super.onResume();
        setDataToAdapter();
    }

    public void setDataToAdapter() {
        final String path = "/merchants/" + SharedPreferenceUtils.readString(getActivity(), "currentUserId", "")
                + "/restaurant/itemDetails/";
        final ItemsAdapter adapter = new ItemsAdapter(RestaurentFragment.this);
        recyclerView.setAdapter(adapter);
        //items = ((MainActivity) getActivity()).dbHelper.getItems();
        mDatabase.child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    //    for(int i =0 ;i<dataSnapshot.getChildrenCount();i++){
                    items.clear();
                dismissProgress();
                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot temp = iterator.next();
                        String child = temp.getKey();
                        items.add(dataSnapshot.child(child).getValue(Item.class));
                    }
                    adapter.setItems(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent addIntent = new Intent(getActivity(), AddItemsToMenuActivity.class);
                startActivity(addIntent);
                break;
//            case R.id.order_count:
//                Intent orderIntent = new Intent(RestaurentFragment.this, OrdersFragment.class);
//                startActivity(orderIntent);
//                break;
//            case R.id.reports:
//                Intent reportIntent = new Intent(RestaurentFragment.this, ReportsFragment.class);
//                startActivity(reportIntent);
//                break;
        }
    }
}

