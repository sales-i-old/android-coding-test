package com.salesi.coding.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContactEntity implements Parcelable {
    @SerializedName("ContactID")
    @Expose
    private Integer contactID;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("FirstNane")
    @Expose
    private String firstName;

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
    private List<String> hobbies = new ArrayList<>();

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress1() {
        return address.getAddress1();
    }

    public void setAddress1(final String address1) {
        address.setAddress1(address1);
    }

    public String getAddress2() {
        return address.getAddress2();
    }

    public void setAddress2(final String address2) {
        address.setAddress2(address2);
    }

    public String getAddress3() {
        return address.getAddress3();
    }

    public void setAddress3(final String address3) {
        address.setAddress3(address3);
    }

    public String getAddress4() {
        return address.getAddress4();
    }

    public void setAddress4(final String address4) {
        address.setAddress4(address4);
    }

    public String getTown() {
        return address.getTown();
    }

    public void setTown(final String town) {
        address.setTown(town);
    }

    public String getCounty() {
        return address.getCounty();
    }

    public void setCounty(final String county) {
        address.setCounty(county);
    }

    public String getPostcode() {
        return address.getPostcode();
    }

    public void setPostcode(final String postcode) {
        address.setPostcode(postcode);
    }

    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(final String country) {
        address.setCountry(country);
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(final List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public ContactEntity() {

    }

    protected ContactEntity(Parcel in) {
        contactID = in.readByte() == 0x00 ? null : in.readInt();
        title = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        jobTitle = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        address = (Address) in.readValue(Address.class.getClassLoader());
        if (in.readByte() == 0x01) {
            hobbies = new ArrayList<String>();
            in.readList(hobbies, String.class.getClassLoader());
        } else {
            hobbies = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (contactID == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(contactID);
        }
        dest.writeString(title);
        dest.writeString(firstName);
        dest.writeString(lastName);
        if (jobTitle == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeString(jobTitle);
        }
        if (phoneNumber == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeString(phoneNumber);
        }
        if (email == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeString(email);
        }
        dest.writeValue(address);
        if (hobbies == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(hobbies);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContactEntity> CREATOR = new Parcelable.Creator<ContactEntity>() {
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