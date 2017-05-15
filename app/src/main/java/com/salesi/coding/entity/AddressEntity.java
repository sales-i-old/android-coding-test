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
        if (null != Address2 && Address2.length() > 0 && ! Address2.equals("null")) {
            address += "\n" + Address2;
        }
        if (null != Address3 && Address3.length() > 0 && ! Address3.equals("null")) {
            address += "\n" + Address3;
        }
        if (null != Address4 && Address4.length() > 0 && ! Address4.equals("null")) {
            address += "\n" + Address4;
        }
        if (null != Postcode && Postcode.length() > 0 && ! Postcode.equals("null")) {
            address += "\n" + Postcode + " " + Town;
        }  else {
            address += "\n" + Town;
        }
        if (null != County && County.length() > 0 && ! County.equals("null")) {
            address += "\n" + County;
        }
        if (null != Address2 && Country.length() > 0 && ! Country.equals("null")) {
            address += "\n" + Country;
        }
        return address;
    }
}
