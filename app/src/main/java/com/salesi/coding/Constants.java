package com.salesi.coding;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.salesi.coding.Constants.Serializables.CONTACT_ENTITY;

public final class Constants {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            CONTACT_ENTITY,
    })
    public @interface Serializables {
        String CONTACT_ENTITY = "CONTACT_ENTITY";
    }
}
