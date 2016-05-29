package com.zampaaa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zampaaa.Analytics.ReportsFragment;
import com.zampaaa.orders.OrdersFragment;

import java.util.Stack;

/**
 * Created by Softapt on 29/05/2016.
 */
public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private Stack<Fragment> fragmentStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        fragmentStack = new Stack<Fragment>();
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("reports"));
        tabLayout.addTab(tabLayout.newTab().setText("chat"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager

        //Creating our pager adapter

        //Adding adapter to pager

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
        RestaurentFragment restaurentActivity = new RestaurentFragment();
        addFragmentToView(restaurentActivity);
        tabLayout.getTabAt(0).select();
    }

    /**
     * adds the fragment to the content layout of main screen
     *
     * @param fragmentToBeAdded - fragment (home/settings/categories)
     */
    public void addFragmentToView(Fragment fragmentToBeAdded) {
        Fragment fragment = ((Fragment) getSupportFragmentManager().findFragmentById(R.id.container));
        if (fragment == null || (fragment != null && !fragment.getClass().getSimpleName().equalsIgnoreCase(fragmentToBeAdded.getClass().getSimpleName()))) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragmentToBeAdded);
            if (!fragmentStack.contains(fragmentToBeAdded)) {
                fragmentStack.push(fragmentToBeAdded);
            }
            fragmentTransaction.commit();
        } else {

        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                RestaurentFragment restaurentActivity = new RestaurentFragment();
                addFragmentToView(restaurentActivity);
                // menu
                break;
            case 1:
                OrdersFragment ordersFragment = new OrdersFragment();
                addFragmentToView(ordersFragment);
                // orders
                break;
            case 2:
                ReportsFragment reportsFragment = new ReportsFragment();
                addFragmentToView(reportsFragment);
                // reports
                break;
            case 3:
                ChatFragment chatFragment = new ChatFragment();
                addFragmentToView(chatFragment);
                // chat
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
