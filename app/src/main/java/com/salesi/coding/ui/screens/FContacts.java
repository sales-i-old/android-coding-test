package com.salesi.coding.ui.screens;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

/**
 * Fragment matching first tab
 *
 * Copyright © 2017 sales­i
 */

public class FContacts extends Fragment implements ContactsAdapter.OnClickListener {
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
                mRecycler.setAdapter(mAdapter.get());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter.get().setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view, ContactEntity contactEntity) {
        int id = view.getId();
        switch (id) {
            case R.id.phone:
                launchCallActivity(contactEntity);
                break;
            case R.id.email:
                launchEmailActivity(contactEntity);
                break;
            default:
                launchContactDetailsActivity(contactEntity);
                break;
        }
    }

    private void launchContactDetailsActivity(ContactEntity contactEntity) {
        Intent intent = ContactDetailsActivity.newIntent(getContext(), contactEntity, mAdapter.get().getData());
        startActivity(intent);
    }

    private void launchCallActivity(ContactEntity contactEntity) {
        String phoneNumber = contactEntity.phoneNumber;
        if(TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(getContext(), R.string.invalid_phone_number, Toast.LENGTH_SHORT).show();
        } else  {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +phoneNumber));
            startActivity(intent);
        }
    }

    private void launchEmailActivity(ContactEntity contactEntity) {
        String email = contactEntity.email;
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), R.string.invalid_email, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getContext(), R.string.no_available_email_client_found, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
