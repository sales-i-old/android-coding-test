package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesi.coding.ContactDetailsPage;
import com.salesi.coding.R;
import com.salesi.coding.di.ApplicationContext;
import com.salesi.coding.entity.ContactEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Contacts view adapter
 * <p>
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactEntity> mContacts;
    @Inject
    @ApplicationContext
    Context mContext;

    @Inject
    public ContactsAdapter() {
    }

    public void setData(List<ContactEntity> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_contact_row_item, parent, false);
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Nullable
        @Bind(R.id.contact_id)
        protected TextView mId;
        @Nullable
        @Bind(R.id.contact_name)
        protected TextView mName;
        @Nullable
        @Bind(R.id.phone)
        protected ImageView mPhone;
        @Nullable
        @Bind(R.id.email)
        protected ImageView mEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {
            mId.setText(Integer.toString(entity.ContactID));
            mName.setText(entity.FirstNane + " " + entity.LastName);
        }

        @OnClick(R.id.phone)
        public void onPhoneClick() {
            String phoneNumber = mContacts.get(this.getAdapterPosition()).PhoneNumber;
            if (TextUtils.isEmpty(phoneNumber.trim()) || phoneNumber.equals("null")) {
                Toast.makeText(mContext, "Phone number not available", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            mContext.startActivity(intent);
        }

        @OnClick(R.id.email)
        public void onEmailClick() {
            String email = mContacts.get(this.getAdapterPosition()).Email;
            Log.i("Click", "Email");
            if (TextUtils.isEmpty(email.trim()) || email.equals("null")) {
                Toast.makeText(mContext, "Email not available", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", email, null));
            Intent eIntent = Intent.createChooser(intent, "Send email...");
            eIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(eIntent);
        }

        @Override
        public void onClick(View v) {
            Log.i("Click", "Item");
            Intent intent = new Intent(mContext, ContactDetailsPage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Integer contactid = mContacts.get(this.getAdapterPosition()).ContactID;
            String title = mContacts.get(this.getAdapterPosition()).Title;
            String firstname = mContacts.get(this.getAdapterPosition()).FirstNane;
            String lastname = mContacts.get(this.getAdapterPosition()).LastName;
            String jobtitle = mContacts.get(this.getAdapterPosition()).JobTitle;
            String Phonenumber = mContacts.get(this.getAdapterPosition()).PhoneNumber;
            String email = mContacts.get(this.getAdapterPosition()).Email;

            ArrayList<String> hobbies = null;
            Bundle b = new Bundle();

            if (mContacts.get(this.getAdapterPosition()).Hobbies != null) {
                hobbies = (ArrayList<String>) mContacts.get(this.getAdapterPosition()).Hobbies;
            }

            b.putInt("contctid", contactid);
            b.putString("title", title);
            b.putString("firstnane", firstname);
            b.putString("lastname", lastname);
            b.putString("jobtitle", jobtitle);
            b.putString("phonenumber", Phonenumber);
            b.putString("email", email);
            b.putStringArrayList("hobbies", hobbies);

            intent.putExtras(b);
            mContext.startActivity(intent);

        }
    }

}
