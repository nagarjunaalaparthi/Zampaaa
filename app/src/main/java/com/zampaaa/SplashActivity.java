package com.zampaaa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zampaaa.Utils.SharedPreferenceUtils;

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
                if(SharedPreferenceUtils.readBoolean(SplashActivity.this,"login",false)){
                    Intent homeIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                }else{
                    Intent homeIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(homeIntent);
                }
                finish();
            }
        }, 2000);
    }
}
