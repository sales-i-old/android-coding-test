package com.salesi.coding.mapper.impl;

import android.support.annotation.Nullable;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.mapper.IContactMapper;
import com.salesi.coding.mapper.adapter.ContactsAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Mapper layer implementations
 *
 * Copyright © 2017 sales­i
 */
public class ContactMapperImpl implements IContactMapper {
    private final ContactsAPI mContactsAPI;

    private List<ContactEntity> contacts = null;

    public ContactMapperImpl(ContactsAPI contactsAPI) {
        mContactsAPI = contactsAPI;
        contacts = new ArrayList<>();
    }

    @Override
    public List<ContactEntity> fetchContacts() {
        Call<List<ContactEntity>> call = mContactsAPI.fetchContacts();
        try {
            Response<List<ContactEntity>> response = call.execute();
            contacts = response.body();
            return contacts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Nullable
    @Override
    public ContactEntity fetchContact(int position) {
        fetchContactsIfNecessary();
        ContactEntity contactFound = null;
        if (null != contacts && position < contacts.size()) {
            contactFound = contacts.get(position);
        }
        return contactFound;
    }

    @Override
    public List<ContactEntity> hobbyFriends(int contactPosition) {
        fetchContactsIfNecessary();
        ContactEntity contact = fetchContact(contactPosition);
        List<ContactEntity> friends = new ArrayList<>();
        if (null != contact && null != contact.Hobbies && contact.Hobbies.length > 0) {
            int i = 0;
            for (ContactEntity currentContact : contacts) {
                if (i != contactPosition) {
                    if (null != currentContact.Hobbies && currentContact.Hobbies.length > 0) {
                        if (hasCommonHobby(contact, currentContact)) {
                            friends.add(currentContact);
                        }
                    }
                }
                i++;
            }
        }
        return friends;
    }

    private void fetchContactsIfNecessary() {
        if (contacts.size() == 0) {
            fetchContacts();
        }
    }

    private boolean hasCommonHobby(ContactEntity c1, ContactEntity c2) {
        List<String> list1 = new ArrayList<>(Arrays.asList(c1.Hobbies));
        List<String> list2 = new ArrayList<>(Arrays.asList(c2.Hobbies));
        list1.retainAll(list2);
        return list1.size() > 0;
    }
}
