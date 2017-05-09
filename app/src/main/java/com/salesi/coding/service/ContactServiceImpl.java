package com.salesi.coding.service;

import android.text.TextUtils;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.mapper.IContactMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer implementations
 * <p>
 * Copyright © 2017 sales­i
 */

public class ContactServiceImpl implements IContactService {

    private final IContactMapper mMapper;

    public ContactServiceImpl(IContactMapper mapper) {
        mMapper = mapper;
    }

    @Override
    public List<ContactEntity> fetchContacts() {
        return mMapper.fetchContacts();
    }

    /* Case sensitive matching */
    @Override
    public List<ContactEntity> fetchContactsWithSharedHobby(ContactEntity contactToMatch) {
        List<ContactEntity> allContacts = mMapper.fetchContacts();
        List<ContactEntity> sharedContacts = new ArrayList<>();

        for (ContactEntity contact : allContacts) {
            /* Assumed that ContactID should be unique */
            if (contactToMatch.Hobbies != null && contact.Hobbies != null && !contact.ContactID.equals(contactToMatch.ContactID)) {
                for (String hobbyToMatch : contactToMatch.Hobbies) {
                    if (!TextUtils.isEmpty(hobbyToMatch) && contact.Hobbies.contains(hobbyToMatch)) {
                        sharedContacts.add(contact);
                        break;
                    }
                }
            }
        }
        return sharedContacts;
    }
}
