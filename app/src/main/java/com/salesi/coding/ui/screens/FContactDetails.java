package com.salesi.coding.ui.screens;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.service.IContactService;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * Fragment matching first tab
 *
 * Copyright © 2017 sales­i
 */

public class FContactDetails extends Fragment {
    @Inject protected Lazy<IContactService> mContactService;

    @Bind(R.id.contact_name) protected TextView mContactName;

    public static FContactDetails instance() {
        return new FContactDetails();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainApp) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.layout_contact_details, container, false);
        ButterKnife.bind(this, view);

        StrictMode.ThreadPolicy strictPolicy = new StrictMode.ThreadPolicy.Builder()
                                                             .permitAll().build();
        StrictMode.setThreadPolicy(strictPolicy);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ContactEntity contact = mContactService.get().fetchContact(885);

                mContactName.setText(contact.FirstNane + " " + contact.LastName);

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
