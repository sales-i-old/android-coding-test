package com.salesi.coding.service;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.mapper.IContactMapper;

import java.util.List;

/**
 * Service layer implementations
 *
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

    @Override
    public ContactEntity fetchContact(int position) {
        return mMapper.fetchContact(position);
    }

    @Override
    public List<ContactEntity> getContactsWithSimilarHobbies(int position) {
        return mMapper.getContactsWithSimilarHobbies(position);
    }
}
