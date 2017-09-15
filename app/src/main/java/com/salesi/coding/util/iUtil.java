package com.salesi.coding.util;

import com.salesi.coding.entity.AddressEntity;

import java.util.List;

/**
 * Created by Rao on 15/09/2017.
 * Interface for Util class
 */


public interface iUtil {
    String addressBuilder(AddressEntity addressEntity);
    String HobbiesBuilder(List<String> hobbies);
}
