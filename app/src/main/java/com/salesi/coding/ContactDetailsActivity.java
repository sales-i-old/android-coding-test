package com.salesi.coding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.salesi.coding.entity.ContactAddressEntity;
import com.salesi.coding.entity.ContactEntity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.contact_id)
    protected TextView mContactIdView;
    @Bind(R.id.contact_title)
    protected TextView mContactTitleView;
    @Bind(R.id.contact_first_name)
    protected TextView mContactFirstNameView;
    @Bind(R.id.contact_last_name)
    protected TextView mContactLastNameView;
    @Bind(R.id.contact_job_title)
    protected TextView mContactJobTitleView;
    @Bind(R.id.contact_phone_number)
    protected TextView mContactPhoneNumberView;
    @Bind(R.id.contact_email)
    protected TextView mContactEmailView;
    @Bind(R.id.contact_address)
    protected TextView mContactAddressView;
    @Bind(R.id.contact_hobbies)
    protected TextView mContactHobbiesView;

    private static String NEWLINE_CHARACTER = "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        ContactEntity contactEntity = (ContactEntity) getIntent().getSerializableExtra(Constants.Serializables.CONTACT_ENTITY);

        bindContactDetails(contactEntity);
    }

    private void bindContactDetails(ContactEntity contactEntity) {
        mContactIdView.setText(contactEntity.ContactID != null ? contactEntity.ContactID.toString() : "");
        mContactTitleView.setText(contactEntity.Title);
        mContactFirstNameView.setText(contactEntity.FirstName);
        mContactLastNameView.setText(contactEntity.LastName);
        mContactJobTitleView.setText(contactEntity.JobTitle);
        mContactPhoneNumberView.setText(contactEntity.PhoneNumber);
        mContactEmailView.setText(contactEntity.Email);
        mContactAddressView.setText(parseContactAddress(contactEntity.Address));
        mContactHobbiesView.setText(parseContactHobbies(contactEntity.Hobbies));
    }

    private String parseContactAddress(ContactAddressEntity contactAddressEntity) {
        if (contactAddressEntity != null) {
            return "" +
                    formatValueIfPresent(contactAddressEntity.Address1) +
                    formatValueIfPresent(contactAddressEntity.Address2) +
                    formatValueIfPresent(contactAddressEntity.Address3) +
                    formatValueIfPresent(contactAddressEntity.Address4) +
                    formatValueIfPresent(contactAddressEntity.Postcode) +
                    formatValueIfPresent(contactAddressEntity.County) +
                    formatValueIfPresent(contactAddressEntity.Town) +
                    formatValueIfPresent(contactAddressEntity.Country);
        }
        return "";
    }

    private String formatValueIfPresent(String value) {
        return TextUtils.isEmpty(value) ? "" : value + NEWLINE_CHARACTER;
    }

    private String parseContactHobbies(ArrayList<String> contactHobbies) {
        if (contactHobbies != null) {
            return Arrays.toString(contactHobbies.toArray());
        }
        return "";
    }
}
