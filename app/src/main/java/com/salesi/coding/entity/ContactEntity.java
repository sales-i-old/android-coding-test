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
    @Expose public final Integer ContactID;
    @Expose public final String Title;
    @Expose public final String FirstNane;
    @Expose public final String LastName;
    @Expose public final String[] Hobbies;
    @Expose public final String Email;
    @Expose public final String PhoneNumber;

    private ContactEntity(Parcel in) {
        ContactID = in.readInt();
        Title = in.readString();
        FirstNane = in.readString();
        LastName = in.readString();
        Email = in.readString();
        PhoneNumber = in.readString();
        Hobbies = in.createStringArray();
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

    }
}
