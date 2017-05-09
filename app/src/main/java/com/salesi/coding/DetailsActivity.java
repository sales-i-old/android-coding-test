package com.salesi.coding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.salesi.coding.entity.AddressEntity;
import com.salesi.coding.entity.ContactEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    public static final String CONTACT_DATA_KEY = "contact_data";

    @Bind(R.id.viewTitle)
    protected TextView viewTitle;
    @Bind(R.id.viewJobTitle)
    protected TextView viewJobTitle;
    @Bind(R.id.viewPhoneNumber)
    protected TextView viewPhoneNumber;
    @Bind(R.id.viewEmail)
    protected TextView viewEmail;
    @Bind(R.id.viewAddress)
    protected TextView viewAddress;
    @Bind(R.id.viewHobbies)
    protected TextView viewHobbies;


    public static Intent getInstance(Context context, ContactEntity contactEntity) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(CONTACT_DATA_KEY, contactEntity);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        ContactEntity data = (ContactEntity) getIntent().getSerializableExtra(CONTACT_DATA_KEY);

        if (data == null) {
            finish();
            return;
        }

        viewTitle.setText(data.title + " " + data.firstNane + " " + data.lastName);
        viewJobTitle.setText("Job title: " + data.jobTitle);
        viewPhoneNumber.setText("Phone: " + data.phoneNumber);
        viewEmail.setText("E-mail: " + data.email);

        AddressEntity addressEntity = data.address;
        if (addressEntity != null) {
            viewAddress.setText("Address: " + addressEntity.address1 + " " + addressEntity.address2 + " " + addressEntity.address3 + " " + addressEntity.address4
                    + " " + addressEntity.town + " " + addressEntity.country + " " + addressEntity.postcode + " " + addressEntity.country);
        }

        String hobbies = "";
        if (data.hobbies != null && data.hobbies.size() > 0) {
            for (String hobby : data.hobbies) {
                hobbies = hobbies + hobby + " ";
            }
            viewHobbies.setText("Hobbies: " + hobbies);
        }
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//
//        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), getApplicationContext());
//        mViewPager.setAdapter(adapter);
//        mTabLayout.setupWithViewPager(mViewPager);
    }
}