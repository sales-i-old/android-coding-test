package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.screens.ContactDetailActivity;

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

    private static final String TAG = "ContactsAdapter";

    ContactDetailActivity contactDetailActivity;

    private Context context;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(mContacts.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Contact entity clicked");

                // show that id's detail in new Activity
                // (also could be Fragment or DialogFragment using FragmentManager)
                Intent intent = new Intent(context, ContactDetailActivity.class);
                intent.putExtra("contact_contactID", mContacts.get(position).ContactID);
                intent.putExtra("contact_first_name", mContacts.get(position).FirstName);
                intent.putExtra("contact_last_name", mContacts.get(position).LastName);
                context.startActivity(intent);
                //TODO layout for this
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable @Bind(R.id.contact_id) protected TextView mId;
        @Nullable @Bind(R.id.contact_name) protected TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bind(ContactEntity entity) {
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(entity.FirstName+" "+entity.LastName);
        }
    }
}
