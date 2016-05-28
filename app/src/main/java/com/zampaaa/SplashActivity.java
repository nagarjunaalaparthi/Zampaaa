package com.zampaaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Softapt on 28/05/2016.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },1000);
    }
}
