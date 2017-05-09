package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contact Entity
 * Edit class members as required
 * <p>
 * Copyright © 2017 sales­i
 */

public class ContactEntity implements Serializable {

    @Expose
    public Integer ContactID;

    @Expose
    public String Title;

    @Expose
    @SerializedName("FirstNane")
    public String FirstName;

    @Expose
    public String LastName;

    @Expose
    public String JobTitle;

    @Expose
    public String PhoneNumber;

    @Expose
    public String Email;

    @Expose
    public ContactAddressEntity Address;

    @Expose
    public ArrayList<String> Hobbies;
}
