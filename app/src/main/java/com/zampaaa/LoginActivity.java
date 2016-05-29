package com.zampaaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthConfig;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.zampaaa.Utils.SharedPreferenceUtils;

/**
 * Created by Softapt on 28/05/2016.
 */
public class LoginActivity extends BaseActivity {
    private Button mVerify;
    private EditText mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        mVerify = (Button) findViewById(R.id.verify);
        mVerify.setOnClickListener(verifyClickListener);
    }

    View.OnClickListener verifyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mPhoneNumber.getText().toString().length() > 0) {
                DigitsAuthConfig config = new DigitsAuthConfig.Builder().withAuthCallBack(authCallback).withPhoneNumber("+91" + mPhoneNumber.getText().toString()).build();

                Digits.getSessionManager().clearActiveSession();
                Digits.authenticate(config);
            }
        }
    };

    AuthCallback authCallback = new AuthCallback() {
        @Override
        public void success(DigitsSession session, String phoneNumber) {
            // TODO: associate the session userID with your user model
            Intent restaurentIntent = new Intent(LoginActivity.this, RestaurentFragment.class);
            startActivity(restaurentIntent);
            SharedPreferenceUtils.writeBoolean(LoginActivity.this,"login",true);
            finish();
        }

        @Override
        public void failure(DigitsException exception) {
            Log.d("Digits", "Sign in with Digits failure", exception);
        }
    };
}
