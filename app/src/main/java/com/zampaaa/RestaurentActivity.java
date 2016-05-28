package com.zampaaa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zampaaa.Analytics.ReportsActivity;
import com.zampaaa.Items.AddItemsToMenuActivity;
import com.zampaaa.Items.ItemsAdapter;
import com.zampaaa.Model.Item;
import com.zampaaa.orders.OrdersActivity;

import java.util.ArrayList;


public class RestaurentActivity extends BaseActivity implements View.OnClickListener {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton actionButton;
    private ArrayList<Item> items = new ArrayList<>();
    private TextView orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurent);
        initDummyData();
        initViews();
        initListeners();

    }

    private void initDummyData() {

    }

    private void initListeners() {
        actionButton.setOnClickListener(this);
        orders.setOnClickListener(this);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(RestaurentActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actionButton = (FloatingActionButton) findViewById(R.id.fab);
        orders = (TextView) findViewById(R.id.order_count);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDataToAdapter();
    }

    public void setDataToAdapter() {
        items = dbHelper.getItems();
        ItemsAdapter adapter = new ItemsAdapter(RestaurentActivity.this,items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Intent addIntent = new Intent(RestaurentActivity.this, AddItemsToMenuActivity.class);
                startActivity(addIntent);
                break;
            case R.id.order_count:
                Intent orderIntent = new Intent(RestaurentActivity.this, OrdersActivity.class);
                startActivity(orderIntent);
                break;
            case R.id.reports:
                Intent reportIntent = new Intent(RestaurentActivity.this, ReportsActivity.class);
                startActivity(reportIntent);
                break;
        }
    }
}

