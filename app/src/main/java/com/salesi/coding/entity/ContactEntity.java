package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity implements Serializable {
    @Expose public Integer ContactID;
    @Expose public String Title;
    @SerializedName("FirstNane")
    @Expose public String FirstName;
    @Expose public String LastName;
    @Expose public String PhoneNumber;
    @Expose public String Email;
    @Expose public AddressEntity Address;
    @Expose public String[] Hobbies;

    public static class AddressEntity implements Serializable{
        @Expose public String Address1;
        @Expose public String Address2;
        @Expose public String Address3;
        @Expose public String Address4;
        @Expose public String Town;
        @Expose public String Postcode;
        @Expose public String County;
        @Expose public String Country;
        @Override
        public String toString() {
            String[] pendingValue = new String[]{Address1,Address2,Address3,Address4,Town,County,Postcode,Country};
            StringBuilder sb=new StringBuilder();

            for(int i=0;i<pendingValue.length;i++){
                if(pendingValue[i]!=null&&!pendingValue[i].isEmpty()){
                    sb.append(pendingValue[i]+"\n");
                }
            }
            return sb.toString();
        }
    }
}
