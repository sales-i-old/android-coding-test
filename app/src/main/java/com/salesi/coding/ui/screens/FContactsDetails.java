package com.salesi.coding.ui.screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This Activity will be used to display details of selected contacts
 */
public class FContactsDetails extends Fragment {

    @Bind(R.id.id) protected TextView contactID;
    @Bind(R.id.first_name) protected TextView contactFirstName;
    @Bind(R.id.last_name) protected TextView contactLastName;


    public static FContactsDetails instance() {
        return new FContactsDetails();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainApp) getActivity().getApplication()).getComponent().inject(this);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_fcontacts_details, container, false);
        ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
