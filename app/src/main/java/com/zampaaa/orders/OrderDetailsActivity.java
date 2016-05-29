package com.zampaaa.orders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zampaaa.BaseActivity;
import com.zampaaa.Model.Item;
import com.zampaaa.Model.Order;
import com.zampaaa.R;
import com.zampaaa.Utils.SharedPreferenceUtils;

import java.util.ArrayList;

/**
 * Created by Softapt on 29/05/2016.
 */
public class OrderDetailsActivity extends BaseActivity {
    private TextView giveOffer;
    private Object givenOfferToUser;
    CharSequence[] items = new CharSequence[]{"20% off", "30% off", "40% off", "50% off"};
    ArrayList<Item> selectedItems = new ArrayList<>();
    private TextView cancel;
    private TextView approve;
    private Order order;
    private TextView orderBy;
    private TextView totalAmount;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private OrderDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_deails);
        getBundleData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orderBy = (TextView) findViewById(R.id.orderBy);
        totalAmount = (TextView) findViewById(R.id.amt);
        giveOffer = (TextView) findViewById(R.id.give_offer);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(OrderDetailsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        cancel = (TextView) findViewById(R.id.cancel);
        approve = (TextView) findViewById(R.id.approve);
        giveOffer.setOnClickListener(clickListener);
        cancel.setOnClickListener(clickListener);
        approve.setOnClickListener(clickListener);
        initViews();
    }

    private void initViews() {
        if (order != null) {
            orderBy.setText(getString(R.string.orderedby, order.getOrderPlacedby() + ", " + order.getMobileNumber()));
            totalAmount.setText(getString(R.string.totalAmt, order.getTotalAmt()));
            selectedItems = order.getItems();
            adapter = new OrderDetailsAdapter(OrderDetailsActivity.this, selectedItems);
            recyclerView.setAdapter(adapter);
        }
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("order")) {
            order = (Order) bundle.getSerializable("order");
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cancel:
                    final AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);
                    builder.setTitle("Zampaaa");
                    View editView = LayoutInflater.from(OrderDetailsActivity.this).inflate(R.layout.cancel_message_layout, null);
                    builder.setView(editView);
                    final EditText text = (EditText) editView.findViewById(R.id.cancel_message);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (text.getText().toString().trim().length() > 0) {
                                // TODO : order cancellation from merchant side
                                dialogInterface.dismiss();
                            } else {
                                Toast.makeText(OrderDetailsActivity.this, "please enter the message", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.create();
                    builder.show();
                    break;
                case R.id.approve:
                    // TODO : approve and offer check
                    SharedPreferenceUtils.writeBoolean(OrderDetailsActivity.this, "orderKey", true);
                    SharedPreferenceUtils.writeString(OrderDetailsActivity.this, "orderId", order.getOrderId());
                    finish();
                    break;
                case R.id.give_offer:
                    final AlertDialog.Builder offerBuilder = new AlertDialog.Builder(OrderDetailsActivity.this);
                    offerBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            giveOffer.setText("Select Offer : " + items[i]);
                            givenOfferToUser = items[i].toString();
                            dialogInterface.dismiss();
                        }
                    });
                    offerBuilder.create();
                    offerBuilder.show();
                    break;
            }
        }
    };
}
