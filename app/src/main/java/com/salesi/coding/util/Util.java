package com.salesi.coding.util;

import android.text.TextUtils;

import com.salesi.coding.entity.AddressEntity;

import java.util.List;

/**
 * Created by Rao on 15/09/2017.
 * Utilities class for all the methods that can be re used.
 */

public class Util implements iUtil {
    @Override
    public String addressBuilder(AddressEntity addressEntity) {
        String addressline1 = addressEntity.Address1;
        String addressline2 = addressEntity.Address2;
        String addressline3 = addressEntity.Address3;
        String addressline4 = addressEntity.Address4;
        String town = addressEntity.Town;
        String County = addressEntity.County;
        String postcode = addressEntity.Postcode;
        String country = addressEntity.Country;

        StringBuilder addressStringBuilder = new StringBuilder();
        if(!TextUtils.isEmpty(addressline1) && !addressline1.equals("null")){
            addressStringBuilder.append(addressline1);
            addressStringBuilder.append(", ");
        }
        if(!TextUtils.isEmpty(addressline2) && !addressline2.equals("null")){
            addressStringBuilder.append(addressline2);
            addressStringBuilder.append(", ");
        }
        if(!TextUtils.isEmpty(addressline3) && !addressline3.equals("null")){
            addressStringBuilder.append(addressline3);
            addressStringBuilder.append(", ");
        }
        if(!TextUtils.isEmpty(addressline4) && !addressline4.equals("null")){
            addressStringBuilder.append(addressline4);
            addressStringBuilder.append(", ");
        }

        if(!TextUtils.isEmpty(town) && !town.equals("null")){
            addressStringBuilder.append(town);
            addressStringBuilder.append(", ");
        }
        if(!TextUtils.isEmpty(County) && !County.equals("null")){
            addressStringBuilder.append(County);
            addressStringBuilder.append(", ");
        }

        if(!TextUtils.isEmpty(postcode) && !postcode.equals("null")){
            addressStringBuilder.append(postcode);
            addressStringBuilder.append(", ");
        }
        if(!TextUtils.isEmpty(country) && !country.equals("null")){
            addressStringBuilder.append(country);
        }
        return addressStringBuilder.toString();
    }

    @Override
    public String HobbiesBuilder(List<String> hobbies) {
        StringBuilder hobbiesSB = new StringBuilder();
        if(hobbies != null){
            for(int i = 0; i < hobbies.size(); i++){
                if(!hobbies.get(i).toString().isEmpty() ){
                    hobbiesSB.append(hobbies.get(i));
                    if(i < hobbies.size()-1){
                        hobbiesSB.append(", ");
                    }
                }

            }
        }
        return hobbiesSB.toString();
    }
}
