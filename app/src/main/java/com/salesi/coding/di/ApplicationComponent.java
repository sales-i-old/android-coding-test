package com.salesi.coding.di;

import android.content.Context;

import com.google.gson.Gson;
import com.salesi.coding.MainActivity;
import com.salesi.coding.MainApp;
import com.salesi.coding.mapper.adapter.ContactsAPI;
import com.salesi.coding.service.IContactService;
import com.salesi.coding.ui.screens.FContacts;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainApp app);
    void inject(MainActivity activity);
    void inject(FContacts contacts);

    @ApplicationContext Context context();

    Gson gson();
    OkHttpClient client();
    Retrofit retrofit();

    ContactsAPI contactsAPI();
    IContactService contactService();
}
