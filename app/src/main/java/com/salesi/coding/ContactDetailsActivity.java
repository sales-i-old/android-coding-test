package com.salesi.coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.salesi.coding.entity.ContactAddressEntity;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.service.IContactService;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * Created by surbanczyk on 15.05.2017.
 */

public class ContactDetailsActivity extends AppCompatActivity {
    @Inject
    protected Lazy<IContactService> mContactService;

    private int contactId;

    @Bind(R.id.contact_id)
    protected TextView contact_id;
    @Bind(R.id.title)
    protected TextView title;
    @Bind(R.id.first_name)
    protected TextView first_name;
    @Bind(R.id.last_name)
    protected TextView last_name;
    @Bind(R.id.job_title)
    protected TextView job_title;
    @Bind(R.id.phone_number)
    protected TextView phone_number;
    @Bind(R.id.email)
    protected TextView email;
    @Bind(R.id.address1)
    protected TextView address1;
    @Bind(R.id.address2)
    protected TextView address2;
    @Bind(R.id.address3)
    protected TextView address3;
    @Bind(R.id.address4)
    protected TextView address4;
    @Bind(R.id.town)
    protected TextView town;
    @Bind(R.id.county)
    protected TextView county;
    @Bind(R.id.postal_code)
    protected TextView postal_code;
    @Bind(R.id.country)
    protected TextView country;
    @Bind(R.id.hobbies)
    protected TextView hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainApp) getApplication()).getComponent().inject(this);

        setContentView(R.layout.activity_contact_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        contactId = Integer.parseInt(intent.getStringExtra("contactPosition"));
        //Log.d("contactId", contactId);

        showContactDetails();
    }

    private void showContactDetails() {
        ContactEntity contactDetails;

        List<ContactEntity> contacts = mContactService.get().fetchContacts();
        contactDetails = contacts.get(contactId);

        contact_id.setText(contactDetails.ContactID.toString());
        title.setText(contactDetails.Title);
        first_name.setText(contactDetails.FirstNane);
        last_name.setText(contactDetails.LastName);
        job_title.setText(contactDetails.JobTitle);
        phone_number.setText(contactDetails.PhoneNumber);
        email.setText(contactDetails.Email);
        ContactAddressEntity addressEntity = contactDetails.Address;
        address1.setText(addressEntity.Address1);
        address2.setText(addressEntity.Address2);
        address3.setText(addressEntity.Address3);
        address4.setText(addressEntity.Address4);
        town.setText(addressEntity.Town);
        county.setText(addressEntity.County);
        postal_code.setText(addressEntity.Postcode);
        country.setText(addressEntity.Country);
        hobbies.setText(contactDetails.Hobbies.toString());

    }
}
