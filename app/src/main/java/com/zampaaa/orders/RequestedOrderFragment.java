package com.zampaaa.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zampaaa.BaseFragment;
import com.zampaaa.Items.ItemsAdapter;
import com.zampaaa.R;

/**
 * Created by Softapt on 28/05/2016.
 */
public class RequestedOrderFragment extends BaseFragment {

    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_requested_orders, container, false);
            initViews(view);
        }else{

        }
        return view;
    }

    private void initViews(View view) {
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            ItemsAdapter adapter = new ItemsAdapter(null);
            recyclerView.setAdapter(adapter);
    }
}
