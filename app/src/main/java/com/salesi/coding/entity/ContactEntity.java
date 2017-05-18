package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact Entity
 * Edit class members as required
 * <p>
 * Copyright © 2017 sales­i
 */

public class ContactEntity {
    @Expose
    public Integer ContactID;
    @Expose
    public String Title;
    @Expose
    public String FirstNane;
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
