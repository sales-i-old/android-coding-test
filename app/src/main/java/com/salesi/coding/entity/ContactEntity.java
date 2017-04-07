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
        ContactID = in.readInt();
        Title = in.readString();
        FirstNane = in.readString();
        LastName = in.readString();
        Email = in.readString();
        PhoneNumber = in.readString();
        Hobbies = in.createStringArray();
        Address = in.readParcelable(com.salesi.coding.entity.Address.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ContactID);
        dest.writeString(Title);
        dest.writeString(FirstNane);
        dest.writeString(LastName);
        dest.writeString(Email);
        dest.writeString(PhoneNumber);
        dest.writeStringArray(Hobbies);
        dest.writeParcelable(Address, flags);

    }
}