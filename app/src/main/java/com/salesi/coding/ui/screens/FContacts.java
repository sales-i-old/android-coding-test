package com.salesi.coding.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salesi.coding.ContactDetailsActivity;
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
import rx.functions.Action1;

/**
 * Fragment matching first tab
 *
 * Copyright © 2017 sales­i
 */

public class FContacts extends Fragment {
    @Inject
    protected Lazy<IContactService> service;

    @Inject
    protected Lazy<ContactsAdapter> adapter;

    @Bind(R.id.list_contacts)
    protected RecyclerView recyclerView;

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
                List<ContactEntity> contacts = service.get().fetchContacts();

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                final ContactsAdapter contactsAdapter = adapter.get();
                contactsAdapter.setData(contacts);
                contactsAdapter.getPositionClicks().subscribe(new Action1<ContactEntity>() {
                    @Override
                    public void call(final ContactEntity contactEntity) {
                        final FragmentActivity activity = FContacts.instance().getActivity();
                        Intent intent = new Intent(activity,
                                ContactDetailsActivity.class);
                        final Bundle instanceBundle =
                                ContactDetailsActivity.createInstanceBundle(contactEntity);
                        intent.putExtra(ContactDetailsActivity.CONTACT_DETAILS_BUNDLE_ID,
                                instanceBundle);
                        startActivity(intent);
                    }
                });

                contactsAdapter.getOnCallIconClickClicks().subscribe(new Action1<ContactEntity>() {
                    @Override
                    public void call(final ContactEntity contactEntity) {

                    }
                });
                recyclerView.setAdapter(contactsAdapter);
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
