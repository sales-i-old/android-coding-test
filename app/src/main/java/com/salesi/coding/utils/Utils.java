package com.salesi.coding.utils;

import java.util.List;

/**
 * Created by buxtonj on 05/11/2017.
 */

public class Utils {
    public static boolean containsIgnoreCase(List<?> array, String item) {
        for(int i = 0; i < array.size(); i++) {
            if (array.get(i).toString().equalsIgnoreCase(item)) {
                return true;
            }
        }

        return false;
    }
}
