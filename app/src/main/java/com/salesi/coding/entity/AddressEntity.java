package com.salesi.coding.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressEntity implements Serializable{

    @SerializedName("Address1")
    @Expose
    public String address1;
    @SerializedName("Address2")
    @Expose
    public String address2;
    @SerializedName("Address3")
    @Expose
    public String address3;
    @SerializedName("Address4")
    @Expose
    public String address4;
    @SerializedName("Town")
    @Expose
    public String town;
    @SerializedName("County")
    @Expose
    public String county;
    @SerializedName("Postcode")
    @Expose
    public String postcode;
    @SerializedName("Country")
    @Expose
    public String country;
}
