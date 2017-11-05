package com.salesi.coding.ui.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.ui.screens.FContacts;

import java.util.Locale;

import static com.salesi.coding.MainActivity.CONTACTFRAGMENT_TAG;

/**
 * Created by buxtonj on 05/11/2017.
 */

public class DetailFragment extends Fragment {

    TextView contactName, jobTitle, phoneNumber, email, address, hobbies;
    private ContactEntity contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        contactName = (TextView) rootView.findViewById(R.id.contact_name);
        jobTitle = (TextView) rootView.findViewById(R.id.jobTitle);
        phoneNumber = (TextView) rootView.findViewById(R.id.phoneNumber);
        email = (TextView) rootView.findViewById(R.id.email);
        address = (TextView) rootView.findViewById(R.id.adress);
        hobbies = (TextView) rootView.findViewById(R.id.hobbies);

        if (contact != null && rootView.findViewById(R.id.contacts_frag_container) != null) {
            if (savedInstanceState == null) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contacts_frag_container, FContacts.instance(), CONTACTFRAGMENT_TAG)
                        .commit();
            }
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (contact != null) {
            contactName.setText(String.format(Locale.UK, "%s %s %s", contact.Title, contact.FirstNane, contact.LastName));
            jobTitle.setText(contact.JobTitle);
            phoneNumber.setText(contact.PhoneNumber);
            email.setText(contact.Email);


            if (contact.Address != null)
                address.setText(contact.Address.toString());

            if (contact.Hobbies != null)
                hobbies.setText(contact.Hobbies.toString());
        }
    }

    public void setData(ContactEntity contacts) {
        this.contact = contacts;
    }

    public ContactEntity getContact() {
        return contact;
    }
}
