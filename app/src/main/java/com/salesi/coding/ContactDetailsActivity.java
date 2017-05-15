package com.salesi.coding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactDetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Bind(R.id.view_details_id) protected TextView mContactID;

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
        Integer ContactID = b.getInt("ContactID");

        mContactID.setText(String.valueOf(ContactID));
    }
}
