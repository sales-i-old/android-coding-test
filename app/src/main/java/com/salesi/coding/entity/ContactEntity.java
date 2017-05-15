package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity {
    @Expose public Integer ContactID;
    @Expose public String Title;
    @Expose public String FirstNane;
    @Expose public String LastName;
    @Expose public String JobTitle;
    @Expose public String PhoneNumber;
    @Expose public String Email;

    @Expose public String[] Hobbies;

    @Expose public AddressEntity Address;

    public String getCompleteName() {
        String name = Title + " " + FirstNane + " " + LastName;
        return name.trim();
    }

}
