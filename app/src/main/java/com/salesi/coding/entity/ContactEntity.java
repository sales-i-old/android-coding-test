package com.salesi.coding.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright Â© 2017 salesÂ­i
 */

public class ContactEntity implements Parcelable{
    @Expose public Integer ContactID;
    @Expose public String Title;
    @Expose public String FirstNane;
    @Expose public String LastName;
    @Expose public String[] Hobbies;
    @Expose public String Email;
    @Expose public String PhoneNumber;
    @Expose public Address Address;

    protected ContactEntity(Parcel in) {
        Title = in.readString();
        FirstNane = in.readString();
        LastName = in.readString();
        Hobbies = in.createStringArray();
        Email = in.readString();
        PhoneNumber = in.readString();
        Address = in.readParcelable(com.salesi.coding.entity.Address.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(FirstNane);
        dest.writeString(LastName);
        dest.writeStringArray(Hobbies);
        dest.writeString(Email);
        dest.writeString(PhoneNumber);
        dest.writeParcelable(Address, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactEntity> CREATOR = new Creator<ContactEntity>() {
        @Override
        public ContactEntity createFromParcel(Parcel in) {
            return new ContactEntity(in);
        }

        @Override
        public ContactEntity[] newArray(int size) {
            return new ContactEntity[size];
        }
    };
}
