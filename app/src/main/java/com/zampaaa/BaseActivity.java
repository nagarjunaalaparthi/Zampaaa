package com.zampaaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zampaaa.Utils.DBHelper;

/**
 * Created by Softapt on 28/05/2016.
 */
public class BaseActivity extends AppCompatActivity {
    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(BaseActivity.this,"Zampaaa",null,1);
    }
}
