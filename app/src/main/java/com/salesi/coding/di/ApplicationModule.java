package com.salesi.coding.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salesi.coding.MainApp;
import com.salesi.coding.R;
import com.salesi.coding.mapper.IContactMapper;
import com.salesi.coding.mapper.adapter.ContactsAPI;
import com.salesi.coding.mapper.impl.ContactMapperImpl;
import com.salesi.coding.service.ContactServiceImpl;
import com.salesi.coding.service.IContactService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dependency Injection module
 *
 * Copyright © 2017 sales­i
 */
@Module
public class ApplicationModule {
    private final MainApp app;

    public ApplicationModule(MainApp application) {
        app = application;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return app;
    }

    @Singleton
    @Provides
    Gson providesGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls()
                .excludeFieldsWithoutExposeAnnotation();
        return builder.create();
    }

    @Singleton
    @Provides
    OkHttpClient providesClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                                                        .connectTimeout(30, TimeUnit.SECONDS)
                                                        .readTimeout(30, TimeUnit.SECONDS);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .client(client)
                                        .baseUrl(app.getString(R.string.base_url))
                                        .build();
        return retrofit;
    }

    @Singleton
    @Provides
    ContactsAPI providesContactsAPI(Retrofit retrofit) {
        return retrofit.create(ContactsAPI.class);
    }

    @Singleton
    @Provides
    IContactMapper providesContactMapper(ContactsAPI contactsAPI) {
        return new ContactMapperImpl(contactsAPI);
    }

    @Singleton
    @Provides
    IContactService providesContactService(IContactMapper mapper) {
        return new ContactServiceImpl(mapper);
    }
}
