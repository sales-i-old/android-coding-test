package com.salesi.coding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetails extends AppCompatActivity {

    @Bind(R.id.tvTitle) protected TextView mTitle;
    @Bind(R.id.tvFirstName) protected TextView mFirstName;
    @Bind(R.id.tvLastName) protected TextView mLastName;
    @Bind(R.id.tvJobTitle) protected TextView mJobTitle;
    @Bind(R.id.tvPhoneNUmber) protected TextView mPhoneNUmber;
    @Bind(R.id.tvEmail) protected TextView mEmail;
    @Bind(R.id.tvHobbies) protected TextView mHobbies;
    @Bind(R.id.tvAddressLine1) protected TextView mAddressLine1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ButterKnife.bind(this);

        //Get the Bundle data.
        Intent contactIntent = getIntent();
        Bundle contactBundle =  contactIntent.getExtras();

        String hobbies = contactBundle.getString("hobbies");
        String title = contactBundle.getString("title");
        String firstName = contactBundle.getString("firstName");
        String lastName = contactBundle.getString("lastName");
        String jobTitle = contactBundle.getString("jobTitle");
        String phoneNumber = contactBundle.getString("phoneNumber");
        String email = contactBundle.getString("email");
        String address = contactBundle.getString("address");
        setContactInfo(title, firstName, lastName, jobTitle, phoneNumber, email, hobbies, address);
    }

    private void setContactInfo(String title, String firstName, String lastName, String jobTitle,
                                String phoneNumber, String email, String hobbies, String address){
        //Set the data
        mTitle.setText(title);
        mFirstName.setText(firstName);
        mLastName.setText(lastName);
        mJobTitle.setText(jobTitle);
        mPhoneNUmber.setText(phoneNumber);
        mEmail.setText(email);
        mHobbies.setText(hobbies.toString());
        mAddressLine1.setText(address.toString());
    }
}
