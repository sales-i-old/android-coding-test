package com.salesi.coding.ui.screens;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.service.IContactService;
import com.salesi.coding.ui.adapter.ContactsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * Fragment matching first tab
 *
 * Copyright © 2017 sales­i
 */

public class FContacts extends Fragment {
    @Inject protected Lazy<IContactService> mContactService;
    @Inject protected Lazy<ContactsAdapter> mAdapter;

    @Bind(R.id.list_contacts) protected RecyclerView mRecycler;

    public static FContacts instance() {
        return new FContacts();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainApp) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.layout_question_1, container, false);
        ButterKnife.bind(this, view);

        StrictMode.ThreadPolicy strictPolicy = new StrictMode.ThreadPolicy.Builder()
                                                             .permitAll().build();
        StrictMode.setThreadPolicy(strictPolicy);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<ContactEntity> contacts = mContactService.get().fetchContacts();

                mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecycler.setItemAnimator(new DefaultItemAnimator());

                mAdapter.get().setData(contacts);
                mAdapter.get().setContactClickListener(new ContactsAdapter.ContactClickListener() {
                    @Override
                    public void contactClicked(Integer ContactID) {
                        Log.d("CONTACT", "clicked " + ContactID.toString());
                    }
                });
                mRecycler.setAdapter(mAdapter.get());
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
