package com.consumer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consumer.model.Restaurant;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantsFragment extends Fragment {

    public RecyclerView mRestaurantsRecyclerView;
    private View rootView;
    private GridLayoutManager gridLayoutManager;
    private RestaurantsAdapter restaurantsAdapter;

    private ArrayList<Restaurant> restaurantsList = new ArrayList<>();
    private String name[] = {"DRUNKEN MONKEY", "BPM - BEATS PER MINUTE", "MUSTANG TERRACE LOUNGE", "PINE & DINE", "THE WONTON",
            "9GRILL", "KODAK HOUSE", "DRUNKEN MONKEY", "BPM - BEATS PER MINUTE", "MUSTANG TERRACE LOUNGE", "PINE & DINE", "THE WONTON"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_restaurants, container, false);
            init();
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }

    private void init() {
        prepareData();
        initViews();
        initListeners();
        initObjects();
    }

    private void prepareData() {
        for (int i = 0; i < 12; i++) {
            String imageName = String.format("%02d", i+1);
            Restaurant restaurant = new Restaurant(name[i], "Gachibowli, Hyderabad", 4, getResources().getIdentifier("pic" + imageName, "drawable", getActivity().getPackageName()));
            restaurantsList.add(restaurant);
        }
    }


    /**
     * Initializing views
     */
    private void initViews() {
        mRestaurantsRecyclerView = (RecyclerView) rootView.findViewById(R.id.movies_recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRestaurantsRecyclerView.setLayoutManager(gridLayoutManager);
        mRestaurantsRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Initializing listeners
     */
    private void initListeners() {
    }

    /**
     * Initializing objects
     */
    private void initObjects() {
        restaurantsAdapter = new RestaurantsAdapter(getActivity());
        restaurantsAdapter.updateData(restaurantsList);
        mRestaurantsRecyclerView.setAdapter(restaurantsAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRestaurantsRecyclerView.setAdapter(null);
    }
}
