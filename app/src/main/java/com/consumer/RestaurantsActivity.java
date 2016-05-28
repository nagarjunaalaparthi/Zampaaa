package com.consumer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by naresh poola on 16/4/16.
 */
public class RestaurantsActivity extends BaseActivity{


    Toolbar mToolbar;
    TextView mToolbarTitle;
    @Nullable
    boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
    }

    /**
     * Method used to set the toolbar title
     *
     * @param title
     */
    public void setToolbarTitle(String title) {
        mToolbarTitle.setText(title);
    }


    public boolean isTwoPaneContainer(){
        return isTwoPane;
    }
}
