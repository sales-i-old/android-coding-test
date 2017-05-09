package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity implements Serializable{
    @SerializedName("ContactID")
    @Expose
    public Integer contactID;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("FirstNane")
    @Expose
    public String firstNane;
    @SerializedName("LastName")
    @Expose
    public String lastName;
    @SerializedName("JobTitle")
    @Expose
    public String jobTitle;
    @SerializedName("PhoneNumber")
    @Expose
    public String phoneNumber;
    @SerializedName("Email")
    @Expose
    public String email;
    @SerializedName("Address")
    @Expose
    public AddressEntity address;
    @SerializedName("Hobbies")
    @Expose
    public List<String> hobbies = null;
}
