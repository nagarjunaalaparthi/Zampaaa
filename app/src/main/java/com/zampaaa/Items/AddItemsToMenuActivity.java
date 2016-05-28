package com.zampaaa.Items;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zampaaa.BaseActivity;
import com.zampaaa.Model.Item;
import com.zampaaa.R;

/**
 * Created by Softapt on 28/05/2016.
 */
public class AddItemsToMenuActivity extends BaseActivity implements View.OnClickListener {
    CharSequence[] items = new CharSequence[]{"English Breakfast", "SouthIndian Breakfast", "NorthIndian Breakfast", "Starters & cababs", "Soups", "Salads", "Sandwitches & Burgers", "Pizzas", "Chinese"};
    private RadioGroup group;
    private TextView select;
    private EditText itemName;
    private EditText itemPrice;
    private Button addItem;
    private String selectedCategory = "";
    private Item selectedItem;
    private RadioButton nonveg,veg;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        group = (RadioGroup) findViewById(R.id.group);
        veg = (RadioButton) findViewById(R.id.veg);
        nonveg = (RadioButton) findViewById(R.id.nonveg);
        select = (TextView) findViewById(R.id.select_cuisine);
        itemName = (EditText) findViewById(R.id.item_name);
        itemPrice = (EditText) findViewById(R.id.item_price);
        addItem = (Button) findViewById(R.id.add_item);
        getBundleData();
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddItemsToMenuActivity.this);
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        select.setText("Select Category : " + items[i]);
                        selectedCategory = items[i].toString();
                        dialogInterface.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        addItem.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null && bundle.containsKey("item")){
            selectedItem = (Item) bundle.getSerializable("item");
            setDatatoUI(selectedItem);
        }
    }

    private void setDatatoUI(Item selectedItem) {
        itemPrice.setText(selectedItem.getPrice());
        itemName.setText(selectedItem.getName());
        select.setText("Select Category : " + selectedItem.getCategory());
        selectedCategory = selectedItem.getCategory();
        if(selectedItem.getVegType().equalsIgnoreCase("veg")){
            veg.setChecked(true);
        }else{
            nonveg.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_item) {
            if (validItem()) {
                Item item = new Item();
                item.setName(itemName.getText().toString());
                item.setCategory(selectedCategory);
                item.setPrice(itemPrice.getText().toString());
                if(group.getCheckedRadioButtonId() == R.id.veg){
                    item.setVegType("veg");
                }else{
                    item.setVegType("non-veg");
                }
                    if(selectedItem!=null) {
                        item.setId(selectedItem.getId());
                        dbHelper.UpdateItem(item);
                }else {
                    dbHelper.insertItem(item);
                    }
                finish();
            }
        }
    }

    private boolean validItem() {
        if(TextUtils.isEmpty(itemName.getText().toString()) || TextUtils.isEmpty(itemPrice.getText().toString()) || selectedCategory.length() == 0){
            Toast.makeText(AddItemsToMenuActivity.this,"Please add all details",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}