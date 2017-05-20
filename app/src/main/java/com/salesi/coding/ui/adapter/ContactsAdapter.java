package com.salesi.coding.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesi.coding.MainActivity;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.screens.ContactDetailFragment;

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
        @Nullable @Bind(R.id.phone) protected ImageView ivPhone;
        @Nullable @Bind(R.id.email) protected ImageView ivEmail;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemView.getContext() instanceof MainActivity){
                        Bundle bundle = new Bundle();
                        ContactDetailFragment df=new ContactDetailFragment();
                        ContactEntity contactEntity = mContacts.get(getAdapterPosition());
                        bundle.putSerializable(ContactDetailFragment.ARG_CONTACT, contactEntity);
                        df.setArguments(bundle);
                        MainActivity activity = (MainActivity)itemView.getContext();
                        activity.navigate(df,"_detail");
                    }
                }
            });
        }

        public void bind(ContactEntity entity) {
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(entity.FirstName +" "+entity.LastName);
            ivPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    ContactEntity entity = mContacts.get(index);
                    MainActivity.makePhoneCall(ivPhone.getContext(),entity.PhoneNumber);
                }
            });
            ivEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    ContactEntity entity = mContacts.get(index);
                    MainActivity.sendEmail(ivEmail.getContext(),entity.Email);
                }
            });
        }


    }

}
