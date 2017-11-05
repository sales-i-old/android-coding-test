package com.salesi.coding.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.salesi.coding.DetailsActivity;
import com.salesi.coding.MainActivity;
import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.service.IContactService;
import com.salesi.coding.ui.Fragments.DetailFragment;
import com.salesi.coding.ui.adapter.ContactsAdapter;
import com.salesi.coding.ui.adapter.ContactsAdapter.OnItemClickListener;
import com.salesi.coding.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * Fragment matching first tab
 * <p>
 * Copyright © 2017 sales­i
 */

public class FContacts extends Fragment {
    @Inject
    protected Lazy<IContactService> mContactService;
    @Inject
    protected Lazy<ContactsAdapter> mAdapter;

    @Bind(R.id.list_contacts)
    protected RecyclerView mRecycler;

    private ContactEntity selectedContact;

    DetailFragment detailsFrag;

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

                detailsFrag = ((DetailFragment) getActivity().getSupportFragmentManager().findFragmentByTag(MainActivity.DETAILFRAGMENT_TAG));

                if (detailsFrag != null) {
                    selectedContact = detailsFrag.getContact();
                }

                List<ContactEntity> contacts = (selectedContact != null)
                        ? filterContactData(selectedContact, mContactService.get().fetchContacts())
                        : mContactService.get().fetchContacts();

                mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecycler.setItemAnimator(new DefaultItemAnimator());

                mAdapter.get().setData(contacts, onItemClickListener);
                mRecycler.setAdapter(mAdapter.get());
            }
        });

        return view;
    }

    private List<ContactEntity> filterContactData(ContactEntity selectedContact, List<ContactEntity> contactEntities) {
        List<ContactEntity> filteredDataList = new ArrayList<>();

        if (selectedContact != null && (selectedContact.Hobbies != null && selectedContact.Hobbies.size() > 0)) {
            for (ContactEntity contactEntity : contactEntities) {

                // DOn't add selected contact or contact we've already got
                if (contactEntity.ContactID.equals(selectedContact.ContactID) || filteredDataList.contains(contactEntity)) {
                    continue;
                }

                if (contactEntity.Hobbies != null) {
                    for (String contactHobby : contactEntity.Hobbies) {
                        if (Utils.containsIgnoreCase(selectedContact.Hobbies, contactHobby)) {
                            filteredDataList.add(contactEntity);
                            break;
                        }

                    }
                }
            }
        }

        return filteredDataList;
    }



    public OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(ContactEntity item) {
            selectedContact = item;

            // If I had more time, I would instead save all the data into local storage, pass across the id,
            // and then retrieve the data from the database using a DAO
            // Because this way could cause a transaction too large exception
            displayDetails(item);
        }
    };

    private void displayDetails(ContactEntity contactEntity) {
        if (getActivity().findViewById(R.id.contact_detail_container) != null) {

            DetailFragment fragment = new DetailFragment();
            fragment.setData(contactEntity);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contact_detail_container, fragment, MainActivity.DETAILFRAGMENT_TAG)
                    .commit();

        } else {
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("contact", contactEntity);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
