package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesi.coding.ContactDetails;
import com.salesi.coding.R;
import com.salesi.coding.di.ApplicationContext;
import com.salesi.coding.entity.AddressEntity;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.util.Util;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Contacts view adapter
 *
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactEntity> mContacts;

    @Inject @ApplicationContext
    Context mContext;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Nullable @Bind(R.id.contact_id) protected TextView mId;
        @Nullable @Bind(R.id.contact_name) protected TextView mName;
        @Nullable @Bind(R.id.phone) protected ImageView mPhone;
        @Nullable @Bind(R.id.email) protected ImageView mEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {
            mId.setText(Integer.toString(entity.ContactID));
            mName.setText(entity.FirstName+" "+entity.LastName);
        }

        @Override
        public void onClick(View v) {

            Integer contactId = mContacts.get(this.getAdapterPosition()).ContactID;
            String title = mContacts.get(this.getAdapterPosition()).Title;
            String firstName = mContacts.get(this.getAdapterPosition()).FirstName;
            String lastName = mContacts.get(this.getAdapterPosition()).LastName;
            String jobTitle = mContacts.get(this.getAdapterPosition()).JobTitle;
            String phoneNumber = mContacts.get(this.getAdapterPosition()).PhoneNumber;
            String emailaddress = mContacts.get(this.getAdapterPosition()).Email;
            List<String> hobbies = (List<String>) mContacts.get(this.getAdapterPosition()).Hobbies;

            //Get the address details
            AddressEntity addressEntity = mContacts.get(this.getAdapterPosition()).Address;

            Intent detailViewIntent = new Intent(mContext, ContactDetails.class);
            detailViewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle contactsBundle = new Bundle();

            //Get the instance of Utilities class
            Util util = new Util();
            String addressResult = util.addressBuilder(addressEntity);
            String hobbiesResult = util.HobbiesBuilder(hobbies);

            contactsBundle.putInt("ContactId", contactId);
            contactsBundle.putString("title", title);
            contactsBundle.putString("firstName", firstName);
            contactsBundle.putString("lastName", lastName);
            contactsBundle.putString("jobTitle", jobTitle);
            contactsBundle.putString("phoneNumber", phoneNumber);
            contactsBundle.putString("email", emailaddress);
            contactsBundle.putString("hobbies", hobbiesResult);
            contactsBundle.putString("address", addressResult);
            detailViewIntent.putExtras(contactsBundle);
            mContext.startActivity(detailViewIntent);
        }
    }


}
