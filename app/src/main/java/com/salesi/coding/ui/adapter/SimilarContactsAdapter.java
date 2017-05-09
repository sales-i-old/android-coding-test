package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Contacts view adapter
 * <p>
 * Copyright © 2017 sales­i
 */
public class SimilarContactsAdapter extends RecyclerView.Adapter<SimilarContactsAdapter.ViewHolder> {
    private List<ContactEntity> mContacts;
    private Context context;

    public SimilarContactsAdapter(List<ContactEntity> entities) {
        this.mContacts = entities;
    }

    public void setData(List<ContactEntity> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_similar_contact_row_item, parent, false);
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
        @Nullable
        @Bind(R.id.contact_name)
        protected TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ContactEntity entity) {
            mName.setText(entity.firstNane + " " + entity.lastName);
        }
    }
}
