package com.salesi.coding.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        @Nullable @Bind(R.id.contact_id) protected TextView mId;
        @Nullable @Bind(R.id.contact_name) protected TextView mName;
        ImageView email = (ImageView) itemView.findViewById(R.id.email);
        ImageView phone = (ImageView) itemView.findViewById(R.id.phone);
        ContactEntity contactData;


        public ViewHolder(View itemView) {
            super(itemView);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{contactData.Email});
                    v.getContext().startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
            });
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phoneIntent = new Intent(android.content.Intent.ACTION_DIAL);
                    phoneIntent.setData(Uri.parse("tel:"+contactData.PhoneNumber));
                    v.getContext().startActivity(Intent.createChooser(phoneIntent, "Call..."));
                }
            });

            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {
            contactData = entity;
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(entity.FirstNane+" "+entity.LastName);
        }

        @Override
        public void onClick(View v) {
            Log.d("debug", contactData.FirstNane + " " + contactData.LastName);
            Intent intent = new Intent(v.getContext(), com.salesi.coding.DisplayContactDetails.class);
            intent.putExtra("contactId", contactData.ContactID);
            v.getContext().startActivity(intent);
        }
    }
}
