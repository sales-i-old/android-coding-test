package com.salesi.coding.ui.screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.adapter.ContactsAdapter;
import com.salesi.coding.ui.adapter.ShareHobbyContactsAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

public class DetailActivity extends AppCompatActivity implements ShareHobbyContactsAdapter.IClickItem{

    @Bind(R.id.contact_name)
    TextView contactNameView;
    @Bind(R.id.job_title_view)
    TextView jobTitleView;
    @Bind(R.id.phone_view)
    TextView phoneView;
    @Bind(R.id.email_view)
    TextView emailView;
    @Bind(R.id.address_view)
    TextView addressView;
    @Bind(R.id.hobbies_view)
    TextView hobbiesView;
    @Bind(R.id.job_title_text_view)
    TextView jobTitleTextView;
    @Bind(R.id.phone_text_vew)
    TextView phoneTextView;
    @Bind(R.id.email_text_view)
    TextView emailTextView;
    @Bind(R.id.address_text_view)
    TextView addressTextView;
    @Bind(R.id.hobbies_text_view)
    TextView hobbiesTextView;
    @Bind(R.id.share_hobby_text_view)
    TextView shareHobbyTextView;
    @Bind(R.id.share_hobby_recycler_view)
    RecyclerView shareHobbyRecyclerView;
    private ArrayList<ContactEntity> contactEntityArrayList;
    private String contactId;
    private String contactName;
    @Inject
    protected Lazy<ShareHobbyContactsAdapter> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp) getApplication()).getComponent().inject(this);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        contactId = getIntent().getStringExtra("contactId");
        contactName = getIntent().getStringExtra("contactName");
        contactEntityArrayList = getIntent().getParcelableArrayListExtra("contacts");
        setupView();
    }

    private void setupView() {
        for(ContactEntity contactEntity: contactEntityArrayList) {
            if (contactId.equals(contactEntity.getContactID()) && contactName.equals(contactEntity.getName())) {
                contactNameView.setText(contactEntity.getName());
                if (contactEntity.getJobTitle() != null && !"".equals(contactEntity.getJobTitle())) {
                    jobTitleView.setText(contactEntity.getJobTitle());
                } else {
                    jobTitleTextView.setVisibility(View.GONE);
                    jobTitleView.setVisibility(View.GONE);
                }
                if (contactEntity.getPhoneNumber() != null && !"".equals(contactEntity.getPhoneNumber())) {
                    phoneView.setText(contactEntity.getPhoneNumber());
                } else {
                    phoneView.setVisibility(View.GONE);
                    phoneTextView.setVisibility(View.GONE);
                }
                if (contactEntity.getEmail() != null && !"".equals(contactEntity.getEmail())) {
                    emailView.setText(contactEntity.getEmail());
                } else {
                    emailView.setVisibility(View.GONE);
                    emailTextView.setVisibility(View.GONE);
                }
                if (contactEntity.getAddress() != null && !"".equals(contactEntity.getAddress())) {
                    addressView.setText(contactEntity.getAddress());
                } else {
                    addressView.setVisibility(View.GONE);
                    addressTextView.setVisibility(View.GONE);
                }
                String hobbyString = contactEntity.getHobbyString();
                if (!"".equals(hobbyString)) {
                    hobbiesView.setText(hobbyString);
                    List<ContactEntity> contactEntities = new ArrayList<>();
                    for (ContactEntity contactEntity1: contactEntityArrayList) {
                        List<String> hobbies = contactEntity1.getHobbies();
                        if (hobbies != null && hobbies.size()>0) {
                            for (String hobby: hobbies) {
                                if (hobby != null && !hobby.equals("") && hobbyString.toLowerCase().contains(hobby.toLowerCase()) && !contactEntity1.getName().equals(contactName) && !contactEntities.contains(contactEntity1)) {
                                    contactEntities.add(contactEntity1);
                                }
                            }
                        }
                    }
                    if (contactEntities.size() > 0) {
                        shareHobbyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                        shareHobbyRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                        shareHobbyRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        mAdapter.get().setData(contactEntities);
                        mAdapter.get().setIClickItem(this);
                        shareHobbyRecyclerView.setAdapter(mAdapter.get());
                    }
                } else {
                    hobbiesView.setVisibility(View.GONE);
                    hobbiesTextView.setVisibility(View.GONE);
                    shareHobbyTextView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onClickItem(ContactEntity contactEntity) {
        Intent intent = new Intent();
        intent.setClass(this, DetailActivity.class);
        intent.putParcelableArrayListExtra("contacts", (ArrayList<? extends Parcelable>) contactEntityArrayList);
        intent.putExtra("contactId", contactEntity.getContactID());
        intent.putExtra("contactName", contactEntity.getName());
        startActivity(intent);
    }
}
