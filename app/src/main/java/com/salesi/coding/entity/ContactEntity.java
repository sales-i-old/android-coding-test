package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity {
    @Expose public Integer ContactID;
    @Expose public String Title;
    @SerializedName("FirstNane") @Expose public String FirstName;
    @Expose public String LastName;
}
