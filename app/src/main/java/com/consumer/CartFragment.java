package com.consumer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartFragment extends Fragment {

    private View rootView;
    private LinearLayout mDynamicItemsLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_cart_checkout, container, false);
            init();
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }

    private void init() {
        initViews();
        initListeners();
        initObjects();
    }


    /**
     * Initializing views
     */
    private void initViews() {

        mDynamicItemsLayout = (LinearLayout) rootView.findViewById(R.id.item_details_layout);

        for (int i = 0; i < 5; i++) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mReviewRowItem = inflater.inflate(R.layout.list_cart_item, null);
            TextView mMenuHeader = (TextView) mReviewRowItem.findViewById(R.id.add_quantity);
            mMenuHeader.setText("Quantity: 2");
            if (i % 3 == 0) {
                ImageView menutype = (ImageView) mReviewRowItem.findViewById(R.id.menu_type);
                menutype.setImageResource(R.drawable.red_dot);
            }
            mDynamicItemsLayout.addView(mReviewRowItem);
        }
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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
