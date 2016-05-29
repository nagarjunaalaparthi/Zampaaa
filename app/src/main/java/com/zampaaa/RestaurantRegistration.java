package com.zampaaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RestaurantRegistration extends AppCompatActivity {

    private EditText mPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_registration);

        initViews();
    }

    private void initViews() {

    }
}
