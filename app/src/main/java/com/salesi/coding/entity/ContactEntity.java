package com.salesi.coding.entity;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity{
    @Expose public Integer ContactID;
    @Expose public String Title;
    @Expose public String JobTitle;
    @Expose public String FirstNane;
    @Expose public String LastName;
    @Expose public String Email;
    @Expose public String PhoneNumber;
    @Expose public String[] Hobbies;
    @Expose public Address Address;

    public class Address{
        @Expose public String Address1;
        @Expose public String Address2;
        @Expose public String Address3;
        @Expose public String Address4;
        @Expose public String Town;
        @Expose public String County;
        @Expose public String Postcode;
        @Expose public String Country;
    }

}
