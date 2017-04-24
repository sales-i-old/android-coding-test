package com.salesi.coding.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Contacts view adapter
 *
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactEntity> mContacts;

    @Inject
    public ContactsAdapter() {}

    public void setData(List<ContactEntity> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.layout_contact_row_item, parent,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable @Bind(R.id.contact_id) protected TextView mId;
        @Nullable @Bind(R.id.contact_name) protected TextView mName;
        @Nullable @Bind(R.id.phone) protected ImageView mPhone;
        @Nullable @Bind(R.id.email) protected ImageView mEmail;
        private ContactEntity mEntity;

        @OnClick(R.id.phone)
        void callNumber(View view) {
            Intent intent = new Intent(android.content.Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mEntity.PhoneNumber));
            view.getContext().startActivity(Intent.createChooser(intent, "Call number"));
        }

        @OnClick(R.id.email)
        void sendEmail(View view) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, mEntity.Email);
            view.getContext().startActivity(Intent.createChooser(intent, "Send Email"));
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {
            mEntity = entity;
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(entity.FirstName+" "+entity.LastName);
        }
    }
}
