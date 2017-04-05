package com.salesi.coding.mapper.adapter;

import com.salesi.coding.entity.ContactEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Copyright © 2017 sales­i
 */

public interface ContactsAPI {
    @GET("/v2/58e4f80f1100004e00bfc4b3")
    Call<List<ContactEntity>> fetchContacts();
}
