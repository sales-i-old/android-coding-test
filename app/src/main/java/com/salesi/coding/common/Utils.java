package com.salesi.coding.common;

import com.salesi.coding.MainApp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Utils {
    public static String getContacts(String Hobbies,String fullname)
    {
        StringBuilder builder = new StringBuilder();
        if(Hobbies!=null && !Hobbies.isEmpty()) {
            List<String> hobbiesList = Arrays.asList(Hobbies.split(","));
            for (String my_hobby : hobbiesList) {
                my_hobby = my_hobby.trim();
                Iterator it = MainApp.contactHobbiesMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (pair.getValue().toString().contains(my_hobby)) {
                        if (!builder.toString().contains(pair.getKey().toString()) && !pair.getKey().toString().equals(fullname)) {
                            builder.append(pair.getKey());
                            builder.append(", ");
                        }
                    }
                }
            }
            if (builder.length() > 0) builder.deleteCharAt(builder.length() - 2);
            return builder.toString();
        }

        return "";
    }
}
