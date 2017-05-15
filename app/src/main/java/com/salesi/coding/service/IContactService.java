package com.salesi.coding.service;

import com.salesi.coding.entity.ContactEntity;

import java.util.List;

/**
 * Service layer functionality definitions
 *
 * Copyright © 2017 sales­i
 */
public interface IContactService {

    List<ContactEntity> fetchContacts();

    ContactEntity fetchContact(int postion);

    List<ContactEntity> hobbyFriends(int contactPosition);

}
