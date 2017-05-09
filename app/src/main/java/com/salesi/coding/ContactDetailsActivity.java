package com.salesi.coding;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.salesi.coding.databinding.ActivityContactDetailsBinding;
import com.salesi.coding.entity.ContactEntity;

public class ContactDetailsActivity extends AppCompatActivity {

    public static final String CONTACT_DETAILS_BUNDLE_ID = "CONTACT_DETAILS_BUNDLE_ID";

    private static final String CONTACT_DETAILS_ID = "CONTACT_DETAILS_ID";

    private ActivityContactDetailsBinding binding;

    public static Bundle createInstanceBundle(ContactEntity entity) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(CONTACT_DETAILS_ID, entity);
        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_details);
        final Bundle bundleExtra = getIntent().getBundleExtra(CONTACT_DETAILS_BUNDLE_ID);
        ContactEntity entity = bundleExtra.getParcelable(CONTACT_DETAILS_ID);
        binding.setModel(entity);

        final ArrayAdapter<String> adapter = createAdapter(entity);
        binding.hobbiesList.setAdapter(adapter);
    }

    @NonNull
    private ArrayAdapter<String> createAdapter(final ContactEntity entity) {
        return new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                entity.getHobbies());
    }
}
