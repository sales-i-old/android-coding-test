package com.salesi.coding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.salesi.coding.entity.AddressEntity;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.adapter.SimilarContactsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    public static final String CONTACT_DATA_KEY = "contact_data";
    public static final String CONTACT_DATA_LIST_KEY = "contact_data_list";

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
    @Bind(R.id.similarContacts)
    protected RecyclerView similarContacts;

    public static Intent getInstance(Context context, ContactEntity contactEntity, List<ContactEntity> data) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(CONTACT_DATA_KEY, contactEntity);

        ArrayList<ContactEntity> contactEntities = new ArrayList<>();
        contactEntities.addAll(data);
        intent.putExtra(CONTACT_DATA_LIST_KEY, contactEntities);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        final ContactEntity data = (ContactEntity) getIntent().getSerializableExtra(CONTACT_DATA_KEY);

        if (data == null) {
            finish();
            return;
        } else {
            fillViewsData(data);
        }

        StrictMode.ThreadPolicy strictPolicy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(strictPolicy);


        ArrayList<ContactEntity> contacts = (ArrayList<ContactEntity>)getIntent().getSerializableExtra(CONTACT_DATA_LIST_KEY);

        similarContacts.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        similarContacts.setNestedScrollingEnabled(false);

        List<ContactEntity> contactEntityList = new ArrayList<>();
        List<String> hobbies = data.hobbies;
        if (hobbies != null && hobbies.size() > 0) {
            for (ContactEntity entity : contacts) {
                if (entity.hobbies == null || entity.hobbies.size() == 0) {
                    continue;
                }
                List<String> entityHobbies = entity.hobbies;

                if (!data.contactID.equals(entity.contactID)) {
                    for (int i = 0; i < hobbies.size(); i++) {
                        boolean isAdded = false;
                        for (int j = 0; j < entityHobbies.size(); j++) {
                            if (hobbies.get(i).equals(entityHobbies.get(j))) {
                                isAdded = true;
                                break;
                            }
                        }
                        if (isAdded) {
                            contactEntityList.add(entity);
                            break;
                        }
                    }
                }
            }
            SimilarContactsAdapter adapter = new SimilarContactsAdapter(contactEntityList);
            similarContacts.setAdapter(adapter);
        }

    }

    private boolean fillViewsData(ContactEntity data) {
        if (data == null) {
            finish();
            return true;
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
        return false;
    }
}