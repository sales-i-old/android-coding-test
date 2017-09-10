package com.salesi.coding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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
    @Bind(R.id.textView11) TextView others;
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> {
            onBackPressed(); // Implemented by activity
        });

        Intent intent = getIntent();

        String ContactID = intent.getStringExtra("ContactID");
        String Title = intent.getStringExtra("Title");
        String FirstName = intent.getStringExtra("FirstName");
        String LastName = intent.getStringExtra("LastName");
        String JobTitle = intent.getStringExtra("JobTitle");
        String PhoneNumber = intent.getStringExtra("PhoneNumber");
        String Email = intent.getStringExtra("Email");
        String Address = intent.getStringExtra("Address");
        String Hobbies = intent.getStringExtra("Hobbies");

        this.ContactID.setText(ContactID);
        this.Title.setText(Title);
        this.FirstName.setText(FirstName);
        this.LastName.setText(LastName);
        this.JobTitle.setText(JobTitle);
        this.PhoneNumber.setText(PhoneNumber);
        this.Email.setText(Email);
        this.Address.setText(Address);
        this.Hobbies.setText(Hobbies);

        StringBuilder builder = new StringBuilder();
        if(Hobbies!=null && !Hobbies.isEmpty()) {
            List<String> hobbiesList = Arrays.asList(Hobbies.split(","));
            for (String my_hobby : hobbiesList) {
                my_hobby = my_hobby.trim();
                Iterator it = MainApp.contactHobbiesMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (pair.getValue().toString().contains(my_hobby)) {
                        if (!builder.toString().contains(pair.getKey().toString()) && !pair.getKey().toString().equals(FirstName+" "+LastName)) {
                            builder.append(pair.getKey());
                            builder.append(", ");
                        }
                    }
                }
            }
            if (builder.length() > 0) builder.deleteCharAt(builder.length() - 2);
            this.others.setText(builder.toString());
        }



    }
}
