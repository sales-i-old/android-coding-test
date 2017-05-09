package com.salesi.coding.ui.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesi.coding.Constants;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Contacts view adapter
 * <p>
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<ContactEntity> mContacts;

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

    class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @Bind(R.id.contact_id)
        protected TextView mId;

        @Nullable
        @Bind(R.id.contact_name)
        protected TextView mName;

        @Nullable
        @Bind(R.id.phone)
        protected ImageView mPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ContactEntity entity) {
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(entity.FirstName + " " + entity.LastName);

            mPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + entity.PhoneNumber));
                    int permissionCheck = ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE);
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        v.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(v.getContext(), R.string.contact_phone_call_missing_permission, Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions((Activity) v.getContext(), new String[]{Manifest.permission.CALL_PHONE}, Constants.PHONE_CALL_PERMISSION_REQUEST_CODE);
                    }
                }
            });
        }
    }
}
