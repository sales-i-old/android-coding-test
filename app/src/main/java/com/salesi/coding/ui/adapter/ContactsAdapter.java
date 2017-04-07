package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.screens.FContactsDetails;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Contacts view adapter
 *
 * Copyright © 2017 sales­i
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>   {
    private List<ContactEntity> mContacts;
    private  Context context;

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
        @Nullable @Bind(R.id.phone) protected ImageView phoneImageView;
        @Nullable @Bind(R.id.email) protected ImageView emailImageView;



        public ViewHolder(final View itemView) {
            super(itemView);
            context=itemView.getContext();

            ButterKnife.bind(this, itemView);

            phoneImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        ContactEntity clickedDataItem = mContacts.get(pos);
                        String uri = "tel:" + clickedDataItem.PhoneNumber.trim() ;
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        context.startActivity(intent);
                    }
                }
            });
            emailImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        ContactEntity clickedDataItem = mContacts.get(pos);

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{clickedDataItem.Email});
                        i.putExtra(Intent.EXTRA_SUBJECT, "");
                        i.putExtra(Intent.EXTRA_TEXT, "");

                        context.startActivity(Intent.createChooser(i, "Send mail..."));
                    }
                }
            });
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        ContactEntity clickedDataItem = mContacts.get(pos);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.PhoneNumber, Toast.LENGTH_SHORT).show();

                        FContactsDetails nextFrag= new FContactsDetails();
                        FragmentManager fragmentManager = getApplicationContext().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                                .replace(itemView.getId(), nextFrag,null)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });*/
        }

        public void bind(ContactEntity entity) {
            mId.setText(String.valueOf(entity.ContactID));
            mName.setText(""+entity.FirstNane+" "+entity.LastName);
        }
    }
}
