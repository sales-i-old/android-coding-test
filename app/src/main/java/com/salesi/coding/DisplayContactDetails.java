package com.salesi.coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.salesi.coding.entity.ContactEntity;
import java.util.List;

/**
 * Created by imrooniel on 23/04/2017.
 */

public class DisplayContactDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact_details);
        List<ContactEntity> contactList; //get list, then get contact data from the list via passed contactId

        Intent intent = getIntent();
        Integer contactId = intent.getIntExtra("contactId", 0);
        ContactEntity contactData;

        TextView mId = (TextView) findViewById(R.id.detilsId);
        TextView mName = (TextView) findViewById(R.id.detailsName);
        TextView mEmail = (TextView) findViewById(R.id.detailsEmail);
        TextView mPhone = (TextView) findViewById(R.id.detailsPhone);
        TextView mAddress = (TextView) findViewById(R.id.detailsAddress);
        TextView mHobbies = (TextView) findViewById(R.id.detailsHobbies);



        mId.setText(String.valueOf(contactId));
//        mName.setText(contactData.Title + " " + contactData.FirstNane + " " + contactData.LastName + ", " + contactData.JobTitle);
//        mEmail.setText(contactData.Email);
//        mPhone.setText(contactData.PhoneNumber);
//        mAddress.setText(contactData.Address.Address1 + " "
//                        + contactData.Address.Address2 + " "
//                        + contactData.Address.Address3 + " "
//                        + contactData.Address.Address4 + ", "
//                        + contactData.Address.Postcode + " "
//                        + contactData.Address.Town + ", "
//                        + contactData.Address.County + ", "
//                        + contactData.Address.Country);
    }

}
