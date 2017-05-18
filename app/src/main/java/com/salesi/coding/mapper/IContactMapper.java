package com.salesi.coding.mapper;

import com.salesi.coding.entity.ContactEntity;

import java.util.List;

/**
 * Mapper layer functionality definitions
 *
 * Copyright © 2017 sales­i
 */
public interface IContactMapper {
    List<ContactEntity> fetchContacts();
    ContactEntity fetchContact(int position);
    List<ContactEntity> getContactsWithSimilarHobbies(int position);
}
