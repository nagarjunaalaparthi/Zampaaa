package com.zampaaa.Items;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zampaaa.BaseActivity;
import com.zampaaa.R;

import org.w3c.dom.Text;

/**
 * Created by Softapt on 28/05/2016.
 */
public class AddItemsToMenuActivity extends BaseActivity {
    CharSequence[] items = new CharSequence[]{"English Breakfast", "SouthIndian Breakfast", "NorthIndian Breakfast", "Starters & cababs", "Soups", "Salads", "Sandwitches & Burgers", "Pizzas", "Chinese"};
    private RadioGroup group;
    private TextView select;
    private EditText itemName;
    private EditText itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        group = (RadioGroup) findViewById(R.id.group);
        select = (TextView) findViewById(R.id.select_cuisine);
        itemName = (EditText) findViewById(R.id.item_name);
        itemPrice = (EditText) findViewById(R.id.item_price);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddItemsToMenuActivity.this);
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        select.setText("Select Category : " + items[i]);
                        dialogInterface.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}