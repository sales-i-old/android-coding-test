package com.salesi.coding.ui.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.salesi.coding.DetailsActivity;
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
    private Context context;

    @Inject
    public ContactsAdapter() {
    }

    public void setData(List<ContactEntity> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
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
        @Bind(R.id.itemBackground)
        protected RelativeLayout mBackground;
        @Nullable
        @Bind(R.id.phone)
        protected ImageView iconPhone;
        @Nullable
        @Bind(R.id.email)
        protected ImageView iconEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ContactEntity entity) {
            mId.setText("" + entity.contactID);
            mName.setText(entity.firstNane + " " + entity.lastName);
            mBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(DetailsActivity.getInstance(context, entity, mContacts));
                }
            });

            iconPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(entity.phoneNumber)){
                        Toast.makeText(context, "Phone number is not valid", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                    phoneIntent.setData(Uri.parse("tel:" + entity.phoneNumber));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "Go to app setting and allow Phone permission", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    context.startActivity(phoneIntent);
                }
            });

            iconEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = entity.email;

                    if (TextUtils.isEmpty(email)){
                        Toast.makeText(context, "email is not valid", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});

                    context.startActivity(emailIntent);
                }
            });
        }
    }
}
