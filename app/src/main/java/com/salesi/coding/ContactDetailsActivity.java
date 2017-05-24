package com.salesi.coding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.adapter.ContactDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetailsActivity
        extends AppCompatActivity {

    public static final String KEY_CONTACT = "com.salesi.coding.entity.ContactEntity";
    public static final String KEY_CONTACT_LIST = "com.salesi.coding.entity.ContactEntity_LIST";
    public static final int REQUEST_CONTACT_DETAILS = 0x000001;
    public static final int RESULT_CONTACT_DETAILS = 0x000002;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.recyclerView)
    protected RecyclerView mRecyclerView;

    private ContactDetailsAdapter mContactDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setLogo(R.mipmap.ic_launcher);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mContactDetailsAdapter = new ContactDetailsAdapter();
        mRecyclerView.setAdapter(mContactDetailsAdapter);

        Intent intent = getIntent();
        ContactEntity contact = (ContactEntity) intent.getSerializableExtra(KEY_CONTACT);
        List<ContactEntity> contacts = (List<ContactEntity>) intent.getSerializableExtra(KEY_CONTACT_LIST);

        mContactDetailsAdapter.add("Full Name: " + contact.getFullName(true));
        mContactDetailsAdapter.add("Job Title: " + contact.getJobTitle());
        mContactDetailsAdapter.add("PhoneNumber: " + contact.getPhoneNumber());
        mContactDetailsAdapter.add("Email: " + contact.getEmail());
        mContactDetailsAdapter.add("Address: " + contact.getFullAddress());
        mContactDetailsAdapter.add("Similar Hobbies:");

        int size = contacts.size();
        for (int i = 0; i < size; i++) {
            ContactEntity currentContact = contacts.get(i);
            if (currentContact.contactId == contact.contactId)
                continue;

            if (currentContact.hasSimilarHobbies(contact))
                mContactDetailsAdapter.add(currentContact.getFullName(false));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.close) {
            setResult(RESULT_CONTACT_DETAILS);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context, ContactEntity contact, List<ContactEntity> contacts) {
        Intent intent = new Intent(context, ContactDetailsActivity.class);
        intent.putExtra(KEY_CONTACT, contact);
        intent.putExtra(KEY_CONTACT_LIST, new ArrayList<>(contacts));
        return intent;
    }
}