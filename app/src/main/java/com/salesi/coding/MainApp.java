package com.salesi.coding;

import android.app.Application;
import android.content.Context;

import com.salesi.coding.di.ApplicationComponent;
import com.salesi.coding.di.ApplicationContext;
import com.salesi.coding.di.ApplicationModule;
import com.salesi.coding.di.DaggerApplicationComponent;

import javax.inject.Inject;

/**
 * Application class
 * Copyright © 2017 sales­i
 */
public class MainApp extends Application {
    @Inject
    @ApplicationContext
    protected Context appContext;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if(null == applicationComponent) {
            applicationComponent = getComponent();
        }
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        if(null == applicationComponent) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }
}
