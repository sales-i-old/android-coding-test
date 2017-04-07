package com.salesi.coding.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.common.Constants;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.listener.ContactListItemClickListener;
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
 * Copyright Â© 2017 salesÂ­i
 */

public class FContacts extends Fragment {
    @Inject protected Lazy<IContactService> mContactService;
    @Inject protected Lazy<ContactsAdapter> mAdapter;
    Navigate navigate;
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
                final List<ContactEntity> contacts = mContactService.get().fetchContacts();

                mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecycler.setItemAnimator(new DefaultItemAnimator());

                mAdapter.get().setData(contacts);
                mRecycler.setAdapter(mAdapter.get());
                mRecycler.addOnItemTouchListener(
                        new ContactListItemClickListener(getContext(), new ContactListItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                // TODO Handle item click
                                Intent contactDetailsIntent = new Intent(getContext(), FContactDetailsActivity.class);
                                contactDetailsIntent.putExtra(Constants.KEY_CONTACT_DETAILS, contacts.get(position));
                                contactDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(contactDetailsIntent);

                            }
                        })
                );


            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public interface Navigate
    {
        void click();
    }
}
