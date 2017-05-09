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
    private final IContactMapper mapper;

    public ContactServiceImpl(IContactMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ContactEntity> fetchContacts() {
        return mapper.fetchContacts();
    }
}
