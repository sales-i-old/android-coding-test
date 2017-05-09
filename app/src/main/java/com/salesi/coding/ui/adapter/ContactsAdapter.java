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
import com.salesi.coding.listener.OnContactItemClickedListener;

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
    private OnContactItemClickedListener onContactItemClickedListener;

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
        final ContactEntity contactEntity = mContacts.get(position);
        holder.bind(contactEntity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onContactItemClickedListener != null) {
                    onContactItemClickedListener.onItemClicked(contactEntity);
                }
            }
        });
    }

    public void setOnItemClickListener(OnContactItemClickedListener onItemClickListener) {
        onContactItemClickedListener = onItemClickListener;
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

        private ContactEntity contactEntity;

        @OnClick(R.id.phone)
        void callPhoneNumber(View view) {
            Intent intent = new Intent(android.content.Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contactEntity.PhoneNumber));
            view.getContext().startActivity(Intent.createChooser(intent, "Call phone number"));
        }

        @OnClick(R.id.email)
        void sendEmail(View view) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text");
            intent.putExtra(Intent.EXTRA_EMAIL, contactEntity.Email);
            view.getContext().startActivity(Intent.createChooser(intent, "Send email"));
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {
            contactEntity = entity;
            mId.setText(entity.ContactID != null ? entity.ContactID.toString() : "");
            mName.setText(entity.FirstName+" "+entity.LastName);
        }
    }
}
