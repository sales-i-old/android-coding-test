package com.salesi.coding;

import android.app.Application;
import android.content.Context;

import com.salesi.coding.di.ApplicationComponent;
import com.salesi.coding.di.ApplicationContext;
import com.salesi.coding.di.ApplicationModule;
import com.salesi.coding.di.DaggerApplicationComponent;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Application class
 * Copyright © 2017 sales­i
 */
public class MainApp extends Application {
    @Inject @ApplicationContext protected Context mApp;

    private ApplicationComponent mApplicationComponent;
    public static HashMap contactHobbiesMap = new HashMap();
    @Override
    public void onCreate() {
        super.onCreate();

        if(null == mApplicationComponent) mApplicationComponent = getComponent();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        if(null == mApplicationComponent) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
