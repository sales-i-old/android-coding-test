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

public class ContactEntity
        implements Serializable
{
    @Expose @SerializedName("ContactID")    public int contactId;
    @Expose @SerializedName("Title")        public String title;
    @Expose @SerializedName("FirstNane")    public String firstName;
    @Expose @SerializedName("LastName")     public String lastName;
    @Expose @SerializedName("JobTitle")     public String jobTitle;
    @Expose @SerializedName("PhoneNumber")  public String phoneNumber;
    @Expose @SerializedName("Email")        public String email;
    @Expose @SerializedName("Address")      public AddressEntity address;

    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstName);
        stringBuilder.append(" ");
        stringBuilder.append(lastName);

        return stringBuilder.toString();
    }

    public String getJobTitle() {
        return jobTitle == null ? "" : jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public String getFullAddress() {
        return address.getFullAddress();
    }

    public class AddressEntity
            implements Serializable
    {
        @Expose @SerializedName("Address1") public String address1;
        @Expose @SerializedName("Address2") public String address2;
        @Expose @SerializedName("Address3") public String address3;
        @Expose @SerializedName("Address4") public String address4;
        @Expose @SerializedName("Town")     public String town;
        @Expose @SerializedName("County")   public String county;
        @Expose @SerializedName("Postcode") public String postcode;
        @Expose @SerializedName("Country")  public String country;

        public String getFullAddress() {
            return address1 +" " +address2 +" "+address3 +" "+address4 +" "+town +" "+county +" "+postcode +" "+country;
        }
    }
}
