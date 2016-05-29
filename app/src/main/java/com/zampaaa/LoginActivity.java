package com.zampaaa;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsAuthConfig;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zampaaa.Model.Restaurant;
import com.zampaaa.Model.User;
import com.zampaaa.Utils.SharedPreferenceUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Softapt on 28/05/2016.
 */
public class LoginActivity extends BaseActivity {
    private Button mVerify;
    private EditText mPhoneNumber;
    private DatabaseReference mDatabase;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        mVerify = (Button) findViewById(R.id.verify);
        mVerify.setOnClickListener(verifyClickListener);
    }

    public void showProgress() {

        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("Loading...");
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(false);
            mDialog.show();
        } else if (!mDialog.isShowing()) {
            mDialog.show();
        }

    }

    public void dismissProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    private String mUserId;
    View.OnClickListener verifyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mPhoneNumber.getText().toString().length() > 0) {
                mUserId = mPhoneNumber.getText().toString();
                if (mUserId.length() != 10) {
                    mPhoneNumber.setError("enter a valid phone number");
                } else {
                    DigitsAuthConfig config = new DigitsAuthConfig.Builder().withAuthCallBack(authCallback).withPhoneNumber("+91" + mPhoneNumber.getText().toString()).build();
                    Digits.getSessionManager().clearActiveSession();
                    Digits.authenticate(config);
                }

            } else {
                mPhoneNumber.setError("Phone is required");
            }
        }
    };

    View.OnClickListener signUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };

    AuthCallback authCallback = new AuthCallback() {
        @Override
        public void success(DigitsSession session, String phoneNumber) {
            // TODO: associate the session userID with your user model
            showProgress();
            mDatabase.child("merchants").child(mUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        //user exists, do something
                        dismissProgress();
                        moveToHomeScreen();
                    } else {
                        Restaurant rest = new Restaurant("The Circuit", "#304,watermark, Madhapur", "Hyderabad", "Telangana", "500081", "9:00 AM", "10:00 PM", false);

                        User merchant = new User();
                        merchant.setName("Rangaiah");
                        merchant.setEmail("rangaiah@circuit.com");
                        merchant.setRestaurant(rest);
                        Map<String, Object> merchantValues = merchant.toMap();
                        dismissProgress();
                        mDatabase.child("merchants").child(mUserId).setValue(merchant, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                SharedPreferenceUtils.writeString(LoginActivity.this, "currentUserId", mUserId);
                                moveToHomeScreen();
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void failure(DigitsException exception) {
            Log.d("Digits", "Sign in with Digits failure", exception);
        }
    };

    private void moveToHomeScreen() {
        Intent restaurentIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(restaurentIntent);
        SharedPreferenceUtils.writeBoolean(LoginActivity.this, "login", true);
        finish();
    }
}
