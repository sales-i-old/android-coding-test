package com.salesi.coding.mapper.impl;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.mapper.IContactMapper;
import com.salesi.coding.mapper.adapter.ContactsAPI;

import java.io.IOException;
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

    @Override
    public List<ContactEntity> fetchContacts() {
        Call<List<ContactEntity>> call = mContactsAPI.fetchContacts();
        try {
            Response<List<ContactEntity>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
