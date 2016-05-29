package com.zampaaa.Analytics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zampaaa.BaseActivity;
import com.zampaaa.BaseFragment;
import com.zampaaa.R;

/**
 * Created by Softapt on 28/05/2016.
 */
public class ReportsFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    private TextView selectFrequency;
    CharSequence[] items = new CharSequence[]{"daily", "weekly", "monthly", "yearly"};
    private String selectFrequencyText = "";
    View view = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_reports, container, false);
            initViews(view);

        } else {

        }
        return view;
    }

    private void initViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("User Reports"));
        tabLayout.addTab(tabLayout.newTab().setText("Product Reports"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        selectFrequency = (TextView) view.findViewById(R.id.select_frequency);
        selectFrequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectFrequency.setText("Selected Frequency : " + items[i]);
                        selectFrequencyText = items[i].toString();
                        dialogInterface.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getChildFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);

        viewPager.addOnPageChangeListener(pageLister);
    }


    ViewPager.OnPageChangeListener pageLister = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tabLayout.getTabAt(position).select();
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

    public class Pager extends FragmentStatePagerAdapter {

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
                    UserAnalytics tab1 = new UserAnalytics();
                    return tab1;
                case 1:
                    ProductAnalytics tab2 = new ProductAnalytics();
                    return tab2;
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
