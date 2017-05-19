package com.salesi.coding.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contact Entity
 * Edit class members as required
 *
 * Copyright © 2017 sales­i
 */

public class ContactEntity implements Parcelable {

    @SerializedName("ContactID")
    @Expose
    private String contactID;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("FirstNane")
    @Expose
    private String firstNane;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("JobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Address")
    @Expose
    private Address address;
    @SerializedName("Hobbies")
    @Expose
    private List<String> hobbies = null;

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstNane() {
        return firstNane;
    }

    public void setFirstNane(String firstNane) {
        this.firstNane = firstNane;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        String addressString = "";
        if (!"".equals(address.getAddress1())) {
            addressString += address.getAddress1() + ", ";
        }
        if (!"".equals(address.getAddress2())) {
            addressString += address.getAddress2() + ", ";
        }
        if (!"".equals(address.getAddress3())) {
            addressString += address.getAddress3() + ", ";
        }
        if (!"".equals(address.getAddress4())) {
            addressString += address.getAddress4() + ", ";
        }
        if (!"".equals(address.getTown())) {
            addressString += address.getTown() + ", ";
        }
        if (!"".equals(address.getCounty())) {
            addressString += address.getCounty() + ", ";
        }
        if (!"".equals(address.getPostcode())) {
            addressString += address.getPostcode() + ", ";
        }
        if (!"".equals(address.getCountry())) {
            addressString += address.getCountry();
        }
        if (addressString.length() > 2 && ", ".equals(addressString.substring(addressString.length()-2))) {
            addressString = addressString.substring(0, addressString.length()-2);
        }
        return  addressString;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public String getHobbyString() {
        String hobbyString = "";
        for (int i=0; i<hobbies.size(); i++) {
            if (i < hobbies.size()-1) {
                hobbyString+=hobbies.get(i)+", ";
            } else {
                hobbyString+=hobbies.get(i);
            }
        }
        return hobbyString;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getName() {
        String name = "";
        if (title != null && !title.equals("null")) {
            name += title +" " + firstNane + " " + lastName;
        } else {
            name += firstNane + " " + lastName;
        }
        return name;
    }

    static class Address implements Parcelable {
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

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getAddress3() {
            return address3;
        }

        public void setAddress3(String address3) {
            this.address3 = address3;
        }

        public String getAddress4() {
            return address4;
        }

        public void setAddress4(String address4) {
            this.address4 = address4;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.address1);
            dest.writeString(this.address2);
            dest.writeString(this.address3);
            dest.writeString(this.address4);
            dest.writeString(this.town);
            dest.writeString(this.county);
            dest.writeString(this.postcode);
            dest.writeString(this.country);
        }

        public Address() {
        }

        protected Address(Parcel in) {
            this.address1 = in.readString();
            this.address2 = in.readString();
            this.address3 = in.readString();
            this.address4 = in.readString();
            this.town = in.readString();
            this.county = in.readString();
            this.postcode = in.readString();
            this.country = in.readString();
        }

        public static final Creator<Address> CREATOR = new Creator<Address>() {
            @Override
            public Address createFromParcel(Parcel source) {
                return new Address(source);
            }

            @Override
            public Address[] newArray(int size) {
                return new Address[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contactID);
        dest.writeString(this.title);
        dest.writeString(this.firstNane);
        dest.writeString(this.lastName);
        dest.writeString(this.jobTitle);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeParcelable(this.address, flags);
        dest.writeStringList(this.hobbies);
    }

    public ContactEntity() {
    }

    protected ContactEntity(Parcel in) {
        this.contactID = in.readString();
        this.title = in.readString();
        this.firstNane = in.readString();
        this.lastName = in.readString();
        this.jobTitle = in.readString();
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.hobbies = in.createStringArrayList();
    }

    public static final Parcelable.Creator<ContactEntity> CREATOR = new Parcelable.Creator<ContactEntity>() {
        @Override
        public ContactEntity createFromParcel(Parcel source) {
            return new ContactEntity(source);
        }

        @Override
        public ContactEntity[] newArray(int size) {
            return new ContactEntity[size];
        }
    };
}
