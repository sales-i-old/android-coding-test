package com.salesi.coding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.salesi.coding.entity.ContactEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Bind(R.id.view_details_id) protected TextView mContactID;
    @Bind(R.id.view_details_address) protected TextView mAddress;
    @Bind(R.id.view_details_name) protected TextView mName;
    @Bind(R.id.view_details_jobtitle) protected TextView mJonTitle;
    @Bind(R.id.view_details_phone) protected TextView mPhone;
    @Bind(R.id.view_details_email) protected TextView mEmail;
    @Bind(R.id.view_details_hobbies) protected TextView mHobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        loadContactData();

    }


    private void loadContactData() {
        Bundle b = getIntent().getExtras();
        int position = b.getInt("position");

        MainApp mainApp = ((MainApp) getApplication());
        ContactEntity contact = mainApp.getComponent().contactService().fetchContact(position);

        mContactID.setText(String.valueOf(contact.ContactID));
        mAddress.setText(null != contact.Address ? contact.Address.toString() : "");
        mName.setText(contact.getCompleteName());

        if (null != contact.JobTitle && contact.JobTitle.length() > 0) {
            mJonTitle.setVisibility(View.VISIBLE);
            mJonTitle.setText(contact.JobTitle);
        } else {
            mJonTitle.setVisibility(View.GONE);
        }

        if (null != contact.PhoneNumber && contact.PhoneNumber.length() > 0) {
            mPhone.setVisibility(View.VISIBLE);
            mPhone.setText(contact.PhoneNumber);
        } else {
            mPhone.setVisibility(View.GONE);
        }

        if (null != contact.Email &&contact.Email.length() > 0) {
            mEmail.setVisibility(View.VISIBLE);
            mEmail.setText(contact.Email);
        } else {
            mEmail.setVisibility(View.GONE);
        }

        if (null != contact.Hobbies && contact.Hobbies.length > 0) {
            String hobbyList = "";
            for (String hobby: contact.Hobbies) {
                hobbyList += hobby + "\n";
            }
            mHobbies.setText(hobbyList.trim());
        }
    }
}
