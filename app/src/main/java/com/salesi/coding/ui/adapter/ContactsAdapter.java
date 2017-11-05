package com.salesi.coding.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import java.util.List;
import java.util.Locale;

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
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ContactEntity item);
    }


    @Inject
    public ContactsAdapter() {
    }

    public void setData(List<ContactEntity> contacts, OnItemClickListener listener) {
        this.mContacts = contacts;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.layout_contact_row_item, parent,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mContacts.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable @Bind(R.id.contact_id) protected TextView mId;
        @Nullable @Bind(R.id.contact_name) protected TextView mName;

        @OnClick(R.id.phone)
        void callContact() {
            //TODO Implement Call feature
            Toast.makeText(itemView.getContext(), "Calling contact...", Toast.LENGTH_SHORT).show();
        }

        @OnClick(R.id.email)
        void emailContact() {
            //TODO Implement Email feature
            Toast.makeText(itemView.getContext(), "Emailing contact...", Toast.LENGTH_SHORT).show();
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ContactEntity entity, final OnItemClickListener listener) {
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(String.format(Locale.UK, "%1$s %2$s", entity.FirstNane, entity.LastName));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(entity);
                }
            });
        }
    }
}
