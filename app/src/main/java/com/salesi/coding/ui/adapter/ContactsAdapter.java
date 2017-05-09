package com.salesi.coding.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Contacts view adapter
 *
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactEntity> contacts;

    private final PublishSubject<ContactEntity> onCallIconClick = PublishSubject.create();

    private final PublishSubject<ContactEntity> onClickSubject = PublishSubject.create();

    private final PublishSubject<ContactEntity> onEmailIconClick = PublishSubject.create();

    @Inject
    public ContactsAdapter() {}

    public void setData(List<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.layout_contact_row_item, parent,  false);
        return new ViewHolder(view);
    }

    public Observable<ContactEntity> getPositionClicks(){
        return onClickSubject.asObservable();
    }

    public Observable<ContactEntity> getOnCallIconClickClicks(){
        return onCallIconClick.asObservable();
    }

    public Observable<ContactEntity> getOnEmailIconClicks(){
        return onEmailIconClick.asObservable();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ContactEntity entity = contacts.get(position);
        holder.bind(entity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(entity);
            }
        });

        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                onCallIconClick.onNext(entity);
            }
        });

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                onEmailIconClick.onNext(entity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @Bind(R.id.contact_id)
        protected TextView mId;

        @Nullable
        @Bind(R.id.contact_name)
        protected TextView mName;

        @Nullable
        @Bind(R.id.phone)
        protected TextView phone;

        @Nullable
        @Bind(R.id.email)
        protected TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {
            final Integer contactID = entity.getContactID();

            assert mId != null;
            mId.setText(String.format(Locale.getDefault(),"%d", contactID));

            assert mName != null;
            mName.setText(entity.getFirstName() +" "+ entity.getLastName());
        }
    }
}
