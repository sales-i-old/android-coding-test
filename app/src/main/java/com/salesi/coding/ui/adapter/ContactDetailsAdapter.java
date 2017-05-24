package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetailsAdapter
        extends RecyclerView.Adapter<ContactDetailsAdapter.ViewHolder> {

    private List<String> mDetails = new ArrayList<>();

    public void add(String detail) {
        mDetails.add(detail);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_contact_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String detail = mDetails.get(position);
        holder.bind(detail);
    }

    @Override
    public int getItemCount() {
        return mDetails.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        @Bind(R.id.name)
        protected TextView mName;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(String detail) {
            mName.setText(detail);
        }
    }
}
