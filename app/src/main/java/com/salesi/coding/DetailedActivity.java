package com.salesi.coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.salesi.coding.common.Constants;
import com.salesi.coding.common.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailedActivity extends AppCompatActivity {
    @Bind(R.id.textView) TextView ContactID;
    @Bind(R.id.textView2) TextView Title;
    @Bind(R.id.textView3) TextView FirstName;
    @Bind(R.id.textView4) TextView LastName;
    @Bind(R.id.textView5) TextView JobTitle;
    @Bind(R.id.textView6) TextView PhoneNumber;
    @Bind(R.id.textView7) TextView Email;
    @Bind(R.id.textView8) TextView Address;
    @Bind(R.id.textView9) TextView Hobbies;
    @Bind(R.id.textView11) TextView otherContactsWithSameHobbies;
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> {
            onBackPressed(); // Implemented by activity
        });

        Intent intent = getIntent();

        String ContactID = intent.getStringExtra(Constants.CONTACT_ID);
        String Title = intent.getStringExtra(Constants.TITLE);
        String FirstName = intent.getStringExtra(Constants.FIRST_NAME);
        String LastName = intent.getStringExtra(Constants.LAST_NAME);
        String JobTitle = intent.getStringExtra(Constants.JOB_TITLE);
        String PhoneNumber = intent.getStringExtra(Constants.PHONE_NUMBER);
        String Email = intent.getStringExtra(Constants.EMAIL);
        String Address = intent.getStringExtra(Constants.ADDRESS);
        String Hobbies = intent.getStringExtra(Constants.HOBBIES);

        this.ContactID.setText(ContactID);
        this.Title.setText(Title);
        this.FirstName.setText(FirstName);
        this.LastName.setText(LastName);
        this.JobTitle.setText(JobTitle);
        this.PhoneNumber.setText(PhoneNumber);
        this.Email.setText(Email);
        this.Address.setText(Address);
        this.Hobbies.setText(Hobbies);
        this.otherContactsWithSameHobbies.setText(Utils.getContacts(Hobbies,FirstName+" "+LastName));
    }
}
