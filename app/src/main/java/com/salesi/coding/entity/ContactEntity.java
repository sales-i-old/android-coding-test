package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity {

    @Expose @SerializedName("ContactID")    public int contactId;
    @Expose @SerializedName("Title")        public String title;
    @Expose @SerializedName("FirstNane")    public String firstName;
    @Expose @SerializedName("LastName")     public String lastName;

    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstName);
        stringBuilder.append(" ");
        stringBuilder.append(lastName);

        return stringBuilder.toString();
    }
}
