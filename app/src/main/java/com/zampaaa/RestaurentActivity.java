package com.zampaaa;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zampaaa.Items.AddItemsToMenuActivity;
import com.zampaaa.Items.ItemsAdapter;
import com.zampaaa.orders.OrdersActivity;


public class RestaurentActivity extends AppCompatActivity implements View.OnClickListener{


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurent);
        initViews();
        initListeners();
    }

    private void initListeners() {
        actionButton.setOnClickListener(this);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(RestaurentActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemsAdapter adapter = new ItemsAdapter();
        recyclerView.setAdapter(adapter);
        actionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void onClick(View view) {
        Intent addIntent = new Intent(RestaurentActivity.this, OrdersActivity.class);
        startActivityForResult(addIntent, 100);
    }
}

