package com.consumer;

import android.content.Context;
import android.os.Bundle;
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
public class RestaurantDetailsFragment extends Fragment implements AppConstants {


    private View rootView;
    private LinearLayout mDynamicMenuLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_restaurant_details, container, false);
            init();
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }


    void init() {
        initViews();
    }

    private void initViews() {
        mDynamicMenuLayout = (LinearLayout) rootView.findViewById(R.id.dynamic_menu_layout);

        for (int i = 0; i < 25; i++) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mReviewRowItem = inflater.inflate(R.layout.list_menu_item, null);
            if (i % 10 == 0) {
                TextView mMenuHeader = (TextView) mReviewRowItem.findViewById(R.id.menu_header);
                mMenuHeader.setVisibility(View.VISIBLE);
            }

            if (i % 3 == 0) {
                ImageView menutype = (ImageView) mReviewRowItem.findViewById(R.id.menu_type);
                menutype.setImageResource(R.drawable.red_dot);
            }
            mDynamicMenuLayout.addView(mReviewRowItem);
        }
    }


    void setDataToViews() {

    }

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_cart, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
*/

}
