package com.salesi.coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetailsPage extends AppCompatActivity {

    @Bind(R.id.Title)
    protected TextView mTitle;
    @Bind(R.id.Firstname)
    protected TextView mFirstName;
    @Bind(R.id.LastName)
    protected TextView mLastName;
    @Bind(R.id.PhoneNumber)
    protected TextView mPhoneNumber;
    @Bind(R.id.Email)
    protected TextView mEmail;
    @Bind(R.id.Hobbies)
    protected TextView mHobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details_page);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String title = b.getString("title");
        String firstnane = b.getString("firstnane");
        String lastname = b.getString("lastname");
        String PhoneNumber = b.getString("phonenumber");
        String email = b.getString("email");
        ArrayList<String> hobbies = b.getStringArrayList("hobbies");

        mTitle.setText(title);
        mFirstName.setText(firstnane);
        mLastName.setText(lastname);
        mPhoneNumber.setText(PhoneNumber);
        mEmail.setText(email);
        mHobbies.append(hobbies.toString().replace("[", "").replace("]", ""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.closeapp:
                finish();
                break;
            default:
        }
        return true;
    }


}

