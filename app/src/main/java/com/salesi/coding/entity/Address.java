package com.salesi.coding.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;


public class Address implements Parcelable{
    @Expose public String Address1;
    @Expose public String Address2;
    @Expose public String Address3;
    @Expose public String Address4;
    @Expose public String Town;
    @Expose public String County;
    @Expose public String Postcode;
    @Expose public String Country;

    protected Address(Parcel in) {
        Address1 = in.readString();
        Address2 = in.readString();
        Address3 = in.readString();
        Address4 = in.readString();
        Town = in.readString();
        County = in.readString();
        Postcode = in.readString();
        Country = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Address1);
        dest.writeString(Address2);
        dest.writeString(Address3);
        dest.writeString(Address4);
        dest.writeString(Town);
        dest.writeString(County);
        dest.writeString(Postcode);
        dest.writeString(Country);

    }
}
