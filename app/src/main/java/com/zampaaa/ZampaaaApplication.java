package com.zampaaa;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Softapt on 28/05/2016.
 */
public class ZampaaaApplication extends Application {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "jDu7t4VZq0LvqKgpsW6WDUkRV";
    private static final String TWITTER_SECRET = "xl27j7zOCtR8uleQvvI0vxSe6e3Zlct8AyCtK5D2JL5KQPq2qB";
    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
    }
}
