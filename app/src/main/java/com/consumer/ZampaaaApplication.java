package com.consumer;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

public class ZampaaaApplication extends Application {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "KzDVE9cN0yPQR5x8g64og5AHc";
    private static final String TWITTER_SECRET = "utvCLa2hqW2LarH5ctFthdrKnv8qgnC0l22sEbC72Saxh4o5Xc";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
    }
}