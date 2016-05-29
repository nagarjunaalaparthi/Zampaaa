package com.zampaaa;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.zampaaa.Model.Order;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class BaseFragment extends Fragment {

    private ProgressDialog mDialog;

    public void showProgress() {

        if (mDialog == null) {
            mDialog = new ProgressDialog(getActivity());
            mDialog.setMessage("Loading...");
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setCancelable(false);
            mDialog.show();
        } else if (!mDialog.isShowing()) {
            mDialog.show();
        }

    }
    public void dismissProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


}
