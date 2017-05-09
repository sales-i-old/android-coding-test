package com.salesi.coding.ui.screens;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.service.IContactService;
import com.salesi.coding.ui.adapter.ContactsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

public class ContactDetailsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.tvContactId)
    protected TextView tvContactId;

    @Bind(R.id.tvTitle)
    protected TextView tvTitle;

    @Bind(R.id.tvFirstName)
    protected TextView tvFirstName;

    @Bind(R.id.tvLastName)
    protected TextView tvLastName;

    @Bind(R.id.tvJobTitle)
    protected TextView tvJobTitle;

    @Bind(R.id.tvPhoneNumber)
    protected TextView tvPhoneNumber;

    @Bind(R.id.tvEmail)
    protected TextView tvEmail;

    @Bind(R.id.tvAddress1)
    protected TextView tvAddress1;

    @Bind(R.id.tvAddress2)
    protected TextView tvAddress2;

    @Bind(R.id.tvAddress3)
    protected TextView tvAddress3;

    @Bind(R.id.tvAddress4)
    protected TextView tvAddress4;

    @Bind(R.id.tvTown)
    protected TextView tvTown;

    @Bind(R.id.tvCounty)
    protected TextView tvCounty;

    @Bind(R.id.tvPostcode)
    protected TextView tvPostcode;

    @Bind(R.id.tvCountry)
    protected TextView tvCountry;

    @Bind(R.id.tvHobbies)
    protected TextView tvHobbies;

    @Bind(R.id.list_contacts)
    protected RecyclerView mRecycler;

    @Inject
    protected Lazy<IContactService> mContactService;

    @Inject
    protected Lazy<ContactsAdapter> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainApp) getApplication()).getComponent().inject(this);

        setContentView(R.layout.activity_contact_details);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setTitle(getString(R.string.contact_details_title));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ContactEntity selectedContact = (ContactEntity) getIntent().getSerializableExtra(getString(R.string.contact_key));
        if (selectedContact == null) {
            finish();
            return;
        }

        tvContactId.setText(selectedContact.ContactID.toString());
        tvTitle.setText(selectedContact.Title);
        tvFirstName.setText(selectedContact.FirstName);
        tvLastName.setText(selectedContact.LastName);
        tvJobTitle.setText(selectedContact.JobTitle);
        tvPhoneNumber.setText(selectedContact.PhoneNumber);
        tvEmail.setText(selectedContact.Email);
        tvAddress1.setText(selectedContact.Address.Address1);
        tvAddress2.setText(selectedContact.Address.Address2);
        tvAddress3.setText(selectedContact.Address.Address3);
        tvAddress4.setText(selectedContact.Address.Address4);
        tvTown.setText(selectedContact.Address.Town);
        tvCounty.setText(selectedContact.Address.County);
        tvPostcode.setText(selectedContact.Address.Postcode);
        tvCountry.setText(selectedContact.Address.Country);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < selectedContact.Hobbies.size(); ++i) {
            if (!TextUtils.isEmpty(selectedContact.Hobbies.get(i))) {
                stringBuilder.append(selectedContact.Hobbies.get(i));
                if (i != selectedContact.Hobbies.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
        }
        tvHobbies.setText(stringBuilder.toString());

        new AsyncTask<ContactEntity, Void, List<ContactEntity>>() {

            @Override
            protected List<ContactEntity> doInBackground(ContactEntity... params) {
                return mContactService.get().fetchContactsWithSharedHobby(params[0]);
            }

            @Override
            protected void onPostExecute(List<ContactEntity> contactEntities) {
                mRecycler.setLayoutManager(new LinearLayoutManager(ContactDetailsActivity.this));
                mRecycler.addItemDecoration(new DividerItemDecoration(ContactDetailsActivity.this, LinearLayoutManager.VERTICAL));
                mRecycler.setItemAnimator(new DefaultItemAnimator());

                mAdapter.get().setData(contactEntities);
                mRecycler.setAdapter(mAdapter.get());
            }
        }.execute(selectedContact);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
