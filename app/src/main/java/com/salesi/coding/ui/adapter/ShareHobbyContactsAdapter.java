package com.salesi.coding.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by haitao on 20/05/2017.
 */

public class ShareHobbyContactsAdapter extends RecyclerView.Adapter<ShareHobbyContactsAdapter.ViewHolder> {

    private List<ContactEntity> mContacts;
    private static IClickItem mIClickItem;

    @Inject
    public ShareHobbyContactsAdapter(){}

    public void setData(List<ContactEntity> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_share_contacts_row_item, parent,  false);
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

    public void setIClickItem(IClickItem IClickItem) {
        mIClickItem = IClickItem;
    }

    public interface IClickItem {
        void onClickItem(ContactEntity contactEntity);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.share_contact_name_view)
        TextView contactNameView;
        @Bind(R.id.share_hobbies_view)
        TextView hobbiesView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIClickItem.onClickItem(mContacts.get(getAdapterPosition()));
                }
            });
        }

        public void bind(ContactEntity contactEntity) {
            contactNameView.setText(contactEntity.getName());
            hobbiesView.setText(contactEntity.getHobbyString());
        }
    }
}
