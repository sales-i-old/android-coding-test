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

public class ContactsFragment extends Fragment {
    @Inject protected Lazy<IContactService> mContactService;
    @Inject protected Lazy<ContactsAdapter> mAdapter;

    @Bind(R.id.list_contacts) protected RecyclerView mRecycler;

    public static ContactsFragment instance() {
        return new ContactsFragment();
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

        getActivity().runOnUiThread(() -> {
            List<ContactEntity> contacts = mContactService.get().fetchContacts();
            Log.i("myapp","calling fetchContacts");

            for(ContactEntity contactEntity: contacts) {
                StringBuilder builder = new StringBuilder();
                if(contactEntity.getHobbies()!=null) {
                    for (String hobby : contactEntity.getHobbies()) {
                        builder.append(hobby);
                        builder.append(", ");
                    }

                    MainApp.contactHobbiesMap.put(contactEntity.getFirstNane()+" "+contactEntity.getLastName(), builder.toString());
                }
            }

            mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            mRecycler.setItemAnimator(new DefaultItemAnimator());

            mAdapter.get().setData(contacts);
            mRecycler.setAdapter(mAdapter.get());
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
