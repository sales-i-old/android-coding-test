package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.security.spec.PSSParameterSpec;
import java.util.List;
import java.util.Locale;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity implements Serializable{
    @Expose public Integer ContactID;
    @Expose public String Title;
    @Expose public String FirstNane;
    @Expose public String LastName;
    @Expose public String JobTitle;
    @Expose public String PhoneNumber;
    @Expose public String Email;

    /**
     * Address : {"Address1":"Marine Drive","Address2":"","Address3":"Clove Lane","Address4":"","Town":"Solihull","County":"","Postcode":"","Country":"CCV23A"}
     * Hobbies : ["table-tennis","badminton"]
     */

    @Expose
    public AddressBean Address;
    @Expose
    public List<String> Hobbies;

    public static class AddressBean implements Serializable {
        /**
         * Address1 : Marine Drive
         * Address2 :
         * Address3 : Clove Lane
         * Address4 :
         * Town : Solihull
         * County :
         * Postcode :
         * Country : CCV23A
         */

        @Expose
        public String Address1;
        @Expose
        public String Address2;
        @Expose
        public String Address3;
        @Expose
        public String Address4;
        @Expose
        public String Town;
        @Expose
        public String County;
        @Expose
        public String Postcode;
        @Expose
        public String Country;

        @Override
        public String toString() {
            return String.format(Locale.UK, "%s, %s, %s, %s, %s, %s, %s, %s",
                    Address1, Address2, Address3, Address4, Town, County,Postcode, Country);
        }
    }
}
