package com.salesi.coding.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {
    @SerializedName("Address1")
    @Expose
    private String address1;

    @SerializedName("Address2")
    @Expose
    private String address2;

    @SerializedName("Address3")
    @Expose
    private String address3;

    @SerializedName("Address4")
    @Expose
    private String address4;

    @SerializedName("Town")
    @Expose
    private String town;

    @SerializedName("County")
    @Expose
    private String county;

    @SerializedName("Postcode")
    @Expose
    private String postcode;

    @SerializedName("Country")
    @Expose
    private String country;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(final String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(final String address4) {
        this.address4 = address4;
    }

    public String getTown() {
        return town;
    }

    public void setTown(final String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(final String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public Address() {

    }

    protected Address(Parcel in) {
        address1 = in.readString();
        address2 = in.readString();
        address3 = in.readString();
        address4 = in.readString();
        town = in.readString();
        county = in.readString();
        postcode = in.readString();
        country = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address1);
        dest.writeString(address2);
        dest.writeString(address3);
        dest.writeString(address4);
        dest.writeString(town);
        dest.writeString(county);
        dest.writeString(postcode);
        dest.writeString(country);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}