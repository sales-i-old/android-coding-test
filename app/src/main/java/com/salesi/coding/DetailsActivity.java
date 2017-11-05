package com.salesi.coding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.Fragments.DetailFragment;
import com.salesi.coding.ui.screens.FContacts;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by buxtonj on 05/11/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            ContactEntity fContacts = (ContactEntity) getIntent().getSerializableExtra("contact");

            DetailFragment fragment = new DetailFragment();
            fragment.setData(fContacts);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_detail_container, fragment, MainActivity.DETAILFRAGMENT_TAG)
                    .commit();
        }
    }
}
