package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesi.coding.DetailedActivity;
import com.salesi.coding.R;
import com.salesi.coding.common.Constants;
import com.salesi.coding.entity.Address;
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
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>  {
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bind(mContacts.get(position));
        holder.mName.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra(Constants.CONTACT_ID, mContacts.get(position).getContactID() + "");
            intent.putExtra(Constants.TITLE, mContacts.get(position).getTitle());
            intent.putExtra(Constants.FIRST_NAME, mContacts.get(position).getFirstNane());
            intent.putExtra(Constants.LAST_NAME, mContacts.get(position).getLastName());
            intent.putExtra(Constants.JOB_TITLE, mContacts.get(position).getTitle());
            intent.putExtra(Constants.PHONE_NUMBER, mContacts.get(position).getPhoneNumber());
            intent.putExtra(Constants.EMAIL, mContacts.get(position).getEmail());

            Address address = mContacts.get(position).getAddress();
            if (address != null) {
                intent.putExtra(Constants.ADDRESS, buildAddress(address));
            }
            StringBuilder builder = new StringBuilder();
            List<String> hobbies = mContacts.get(position).getHobbies();
            if (hobbies != null) {
                for (String hobby : hobbies) {
                    if(!hobby.isEmpty()) {
                        builder.append(hobby);
                        builder.append(", ");
                    }
                }
                if (builder.length() > 0) builder.deleteCharAt(builder.length() - 2);
                intent.putExtra(Constants.HOBBIES, builder.toString());
            }
            context.startActivity(intent);


        });


        holder.mEmail.setOnClickListener(v -> {
            email( position,v.getContext());
        });

        holder.mPhone.setOnClickListener(v -> {
            phoneCall( position,v.getContext());
        });

    }

    public void email(int position,Context context)
    {
        String send_to =  mContacts.get(position).getEmail();
        if(send_to.isEmpty())
        {
            Toast.makeText(context.getApplicationContext(),  context.getString(R.string.email_not_found), Toast.LENGTH_SHORT).show();
        }
        else {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(Constants.MAILTO, send_to, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, Constants.SUBJECT);
            emailIntent.putExtra(Intent.EXTRA_TEXT, Constants.BODY);
            context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.send_email)));
        }
    }

    public void phoneCall(int position,Context context)
    {
        String phone =  mContacts.get(position).getPhoneNumber();
        if(phone.isEmpty())
        {
            Toast.makeText(context.getApplicationContext(), context.getString(R.string.phone_not_found), Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(Constants.TEL, phone, null));
            context.startActivity(intent);
        }
    }
    public String buildAddress(Address address){
        StringBuilder builder = new StringBuilder();
        Boolean append_comma = false;
        if(!TextUtils.isEmpty(address.getAddress1()))
        {
            builder.append(address.getAddress1());
            append_comma = true;
        }

        if(!TextUtils.isEmpty(address.getAddress2()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getAddress2());
            append_comma = true;
        }

        if(!TextUtils.isEmpty(address.getAddress3()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getAddress3());
            append_comma = true;
        }

        if(!TextUtils.isEmpty(address.getAddress4()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getAddress4());
            append_comma = true;
        }
        if(!TextUtils.isEmpty(address.getTown()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getTown());
            append_comma = true;
        }

        if(!TextUtils.isEmpty(address.getCounty()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getCounty());
            append_comma = true;
        }

        if(!TextUtils.isEmpty(address.getPostcode()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getPostcode());
            append_comma = true;
        }

        if(!TextUtils.isEmpty(address.getCountry()))
        {
            if(append_comma) builder.append(", ");
            builder.append(address.getCountry());
        }
        return builder.toString();
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @Nullable @Bind(R.id.contact_id) protected TextView mId;
        @Nullable @Bind(R.id.contact_name) protected TextView mName;
        @Nullable @Bind(R.id.phone) protected ImageView mPhone;
        @Nullable @Bind(R.id.email) protected ImageView mEmail;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bind(ContactEntity entity) {

            mId.setText(String.valueOf(entity.getContactID()));  // Bug fix: the app crashes on loading the list of contacts from the API.
            mName.setText(entity.getFirstNane()+" "+entity.getLastName());

        }
    }
}
