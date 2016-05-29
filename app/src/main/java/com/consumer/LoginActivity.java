package com.consumer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.consumer.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button mVerify;
    private EditText mPhoneNumber;
    private DatabaseReference mDatabase;
    private String mUserId;
    private EditText mEmail;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        mEmail = (EditText) findViewById(R.id.email);
        mName = (EditText) findViewById(R.id.name);
        mVerify = (Button) findViewById(R.id.verify);
        mVerify.setOnClickListener(verifyClickListener);
    }

    View.OnClickListener verifyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mPhoneNumber.getText().toString().length() > 0) {
                mUserId = mPhoneNumber.getText().toString();
                if(mUserId.length() != 10) {
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

    View.OnClickListener signUpClickListener = new View.OnClickListener(){
        String mEmailStr,mNameStr;
        @Override
        public void onClick(View v) {
            if(mEmail.getText().toString().length() >0 && isEmailValid(mEmail.getText().toString())){
                mEmailStr = mEmail.getText().toString();
                if(mName.getText().toString().length() > 0){
                    mNameStr = mName.getText().toString();
                    User mUser = new User(mEmailStr,mNameStr);

                    mDatabase.child(mUserId).setValue(mUser, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError !=null){
                                Log.e("SignUp Failed",databaseError.getMessage());
                            } else {
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Toast.makeText(LoginActivity.this,"Welcome "+dataSnapshot.getValue(User.class).getName(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                moveToHomeScreen();

                            }
                        }
                    });

                } else {
                    mName.setError("Name is required");
                }
            } else {
                mEmail.setError("Email is required & Enter a Valid Email");
            }

        }
    };

    AuthCallback authCallback = new AuthCallback() {
        @Override
        public void success(DigitsSession session, String phoneNumber) {
            // TODO: associate the session userID with your user model
            /*Intent restaurentIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(restaurentIntent);
            SharedPreferenceUtils.writeBoolean(LoginActivity.this,"login",true);
            finish();*/
            //Firebase userRef= new Firebase(USERS_LOCATION);
            mDatabase.child(mUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.getValue() != null) {
                        //user exists, do something
                        moveToHomeScreen();
                    } else {
                        mEmail.setVisibility(View.VISIBLE);
                        mName.setVisibility(View.VISIBLE);
                        mVerify.setText("Sign Up");
                        mVerify.setOnClickListener(signUpClickListener);
                        //user does not exist, do something else
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(LoginActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public void failure(DigitsException exception) {
            Log.d("Digits", "Sign in with Digits failure", exception);
        }
    };

    private void moveToHomeScreen() {
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        SharedPreferenceUtils.writeBoolean(LoginActivity.this,"login",true);
        finish();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
