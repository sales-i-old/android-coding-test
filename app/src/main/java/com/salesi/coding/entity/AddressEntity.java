package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class AddressEntity {
    @Expose public String Address1;
    @Expose public String Address2;
    @Expose public String Address3;
    @Expose public String Address4;
    @Expose public String Town;
    @Expose public String County;
    @Expose public String Postcode;
    @Expose public String Country;

    public String toString() {
        String address = Address1;
        if (Address2.length() > 0) {
            address += "\n" + Address2;
        }
        if (Address3.length() > 0) {
            address += "\n" + Address3;
        }
        if (Address4.length() > 0) {
            address += "\n" + Address4;
        }
        if (Postcode.length() > 0) {
            address += "\n" + Postcode + " " + Town;
        }  else {
            address += "\n" + Town;
        }
        if (County.length() > 0) {
            address += "\n" + County;
        }
        if (Country.length() > 0) {
            address += "\n" + Country;
        }
        return address;
    }
}
