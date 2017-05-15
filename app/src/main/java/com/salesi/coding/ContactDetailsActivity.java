package com.salesi.coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.service.IContactService;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * Created by surbanczyk on 15.05.2017.
 */

public class ContactDetailsActivity extends AppCompatActivity {
    @Inject
    protected Lazy<IContactService> mContactService;

    private int contactId;

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
        for (int i = 0; i < contacts.size(); i++) {
            Log.d("SD", contacts.get(i).ContactID + "/" +contactId);
           // if(contacts.get(contactId).ContactID.toString() == contactId)
           // {
              //  contactDetails = contacts.get(contactId.).ContactID;
               // break;
            //}
        }//


    }
}
