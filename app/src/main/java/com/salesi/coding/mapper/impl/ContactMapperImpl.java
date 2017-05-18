package com.salesi.coding.mapper.impl;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.mapper.IContactMapper;
import com.salesi.coding.mapper.adapter.ContactsAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Mapper layer implementations
 * <p>
 * Copyright © 2017 sales­i
 */
public class ContactMapperImpl implements IContactMapper {
    private final ContactsAPI mContactsAPI;

    public ContactMapperImpl(ContactsAPI contactsAPI) {
        mContactsAPI = contactsAPI;
    }

    private List<ContactEntity> contacts;

    @Override
    public List<ContactEntity> fetchContacts() {
        if (contacts == null) {
            Call<List<ContactEntity>> call = mContactsAPI.fetchContacts();
            try {
                Response<List<ContactEntity>> response = call.execute();
                contacts = response.body();
                return contacts;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Collections.EMPTY_LIST;
        } else {
            return contacts;
        }
    }

    @Override
    public ContactEntity fetchContact(int position) {
        return contacts.get(position);
    }

    @Override
    public List<ContactEntity> getContactsWithSimilarHobbies(int position) {
        ContactEntity contact = fetchContact(position);
        List<ContactEntity> friends = new ArrayList<>();

        if (null != contact && null != contact.Hobbies && contact.Hobbies.size() > 0) {
            int i = 0;
            for (ContactEntity currentContact : contacts) {
                if (i != position) {
                    if (hasSimilarHobby(contact, currentContact)) {
                        friends.add(currentContact);
                    }
                }
            }
        }

        return friends;
    }

    private boolean hasSimilarHobby(ContactEntity contact1, ContactEntity contact2) {
        if(contact1.Hobbies != null && contact2.Hobbies != null) {
            for (String curVal : contact1.Hobbies){
                for (String curVal2 : contact2.Hobbies) {
                    if (curVal.toLowerCase().contains(curVal2.toLowerCase())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
