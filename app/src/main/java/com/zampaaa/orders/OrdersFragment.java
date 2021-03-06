package com.zampaaa.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zampaaa.BaseActivity;
import com.zampaaa.BaseFragment;
import com.zampaaa.Model.Item;
import com.zampaaa.Model.Order;
import com.zampaaa.R;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class OrdersFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    View view = null;
    private RequestedOrderFragment requestedOrderFragment;
    private PreparedOrders PreparedOrdersFragment;
    private ApprovedOrders approvedOrdersFragment;
    public ArrayList<Order> preparedOrders;
    public ArrayList<Order> approvedOrders;

    public ArrayList<Order> getApprovedOrders() {
        return approvedOrders;
    }

    public void setApprovedOrders(ArrayList<Order> approvedOrders) {
        this.approvedOrders = approvedOrders;
    }


    public ArrayList<Order> getPreparedOrders() {
        return preparedOrders;
    }

    public void setPreparedOrders(ArrayList<Order> preparedOrders) {
        this.preparedOrders = preparedOrders;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_orders, container, false);
            approvedOrders = new ArrayList<>();

            preparedOrders = new ArrayList<>();
            initViews(view);
        } else {

        }
        return view;
    }


    private void initViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Requested Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Approved Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Prepared Orders"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getChildFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(pageLister);
    }


    ViewPager.OnPageChangeListener pageLister = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            tabLayout.getTabAt(position).select();
            if(position == 1){
                approvedOrdersFragment.setDataToAdapter(approvedOrders);

            }else if(position == 2){
                PreparedOrdersFragment.setDataToAdapter(preparedOrders);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public class Pager extends FragmentPagerAdapter {

        //integer to count number of tabs
        int tabCount;

        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount = tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    requestedOrderFragment = new RequestedOrderFragment();
                    return requestedOrderFragment;
                case 1:
                    approvedOrdersFragment = new ApprovedOrders();
                    return approvedOrdersFragment;
                case 2:
                    PreparedOrdersFragment = new PreparedOrders();
                    return PreparedOrdersFragment;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }
    }
}
