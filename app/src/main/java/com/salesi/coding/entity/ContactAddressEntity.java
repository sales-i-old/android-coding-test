package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactAddressEntity implements Serializable {
    @Expose public String Address1;
    @Expose public String Address2;
    @Expose public String Address3;
    @Expose public String Address4;
    @Expose public String Town;
    @Expose public String County;
    @Expose public String Postcode;
    @Expose public String Country;

}
