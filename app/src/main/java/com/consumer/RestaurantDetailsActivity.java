package com.consumer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.consumer.model.Restaurant;


/**
 * Created by naresh poola on 16/4/16.
 */
public class RestaurantDetailsActivity extends BaseActivity implements AppConstants, View.OnClickListener {

    RestaurantDetailsFragment restaurantDetailsFragment;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    ImageView mMovieBannerImage;
    Toolbar toolbar;
    FloatingActionButton favouriteFab;
    private int vibrant = -1;
    private Restaurant restaurant;
    private String youTubeVideoId = "mK5xvHhWbJI";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        init();
        setSupportActionBar(toolbar);
        setBackNavigationIcon();
        if (savedInstanceState == null) {
            restaurantDetailsFragment = new RestaurantDetailsFragment();
            Bundle bundle = new Bundle();
            restaurantDetailsFragment.setArguments(bundle);
            bundle.putSerializable(IBundleParams.RESULT_OBJ, getIntent().getSerializableExtra(IBundleParams.RESULT_OBJ));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_details_container, restaurantDetailsFragment)
                    .commit();
        }
    }

    void init() {
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mMovieBannerImage = (ImageView) findViewById(R.id.movie_image_banner);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        favouriteFab = (FloatingActionButton) findViewById(R.id.fab);
        setDataToViews();
        mCollapsingToolbarLayout.setOnClickListener(this);
        favouriteFab.setOnClickListener(this);
    }

    void setDataToViews() {
        restaurant = (Restaurant) getIntent().getSerializableExtra(IBundleParams.RESULT_OBJ);
        mCollapsingToolbarLayout.setTitle(restaurant.getName());
        mMovieBannerImage.setImageResource(restaurant.getImageDrawbale());
        Bitmap myBitmap = ((BitmapDrawable) mMovieBannerImage.getDrawable()).getBitmap();
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette.from(myBitmap).generate(paletteListener);
        }
    }

    Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
        public void onGenerated(Palette palette) {
            int defaultColor = 0x000000;
            vibrant = palette.getVibrantColor(defaultColor);
            int vibrantLight = palette.getLightVibrantColor(defaultColor);
            int vibrantDark = palette.getDarkVibrantColor(defaultColor);
            mCollapsingToolbarLayout.setBackgroundColor(vibrant);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mCollapsingToolbarLayout.setStatusBarScrimColor(vibrant);
                mCollapsingToolbarLayout.setContentScrimColor(vibrant);
                getWindow().setStatusBarColor(vibrant);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collapsing_toolbar:
                if (youTubeVideoId != null) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + youTubeVideoId));
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case R.id.fab:
                Intent intent = new Intent(RestaurantDetailsActivity.this, CartActivity.class);
                intent.putExtra(IBundleParams.RESULT_OBJ, vibrant);
                startActivity(intent);
                break;
        }
    }


    private void updateFavouriteFab(boolean isFavourite) {
        if (isFavourite) {
            favouriteFab.setImageResource(R.drawable.icon_favourite);
        } else {
            favouriteFab.setImageResource(R.drawable.icon_unfavourite);
        }
    }


}
