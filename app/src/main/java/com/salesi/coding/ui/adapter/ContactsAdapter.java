package com.salesi.coding.ui.adapter;

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

/**
 * Contacts view adapter
 *
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactEntity> mContacts;
    private ContactClickListener listener;

    @Inject
    public ContactsAdapter() {}

    public void setData(List<ContactEntity> contacts) {
        mContacts = contacts;
    }

    public void setContactClickListener(ContactClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.layout_contact_row_item, parent,  false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setContactClickListener(listener);
        return viewHolder;
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
        @Bind(R.id.phone) protected ImageView mPhone;

        private ContactClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mPhone.setOnClickListener(this);
        }

        public void bind(ContactEntity entity) {
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(entity.FirstNane +" "+entity.LastName);
        }

        public void setContactClickListener(ContactClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.phone) {
                listener.makeCallClicked(getAdapterPosition());
            } else {
                listener.contactClicked(getAdapterPosition());
            }
        }
    }

    public interface ContactClickListener {
        void contactClicked(int position);
        void makeCallClicked(int position);
    }
}
