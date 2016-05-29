package com.zampaaa.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zampaaa.BaseFragment;
import com.zampaaa.R;

/**
 * Created by Softapt on 29/05/2016.
 */
public class ApprovedOrders extends BaseFragment {
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ApprovedOrdersAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
            initViews(view);
        }else{

        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ApprovedOrdersAdapter(ApprovedOrders.this,null);
        recyclerView.setAdapter(adapter);
    }
}
