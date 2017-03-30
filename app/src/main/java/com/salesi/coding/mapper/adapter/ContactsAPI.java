package com.salesi.coding.mapper.adapter;

import com.salesi.coding.entity.ContactEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Copyright © 2017 sales­i
 */

public interface ContactsAPI {
    @GET("/v2/58dd146c28000021049e4a23")
    Call<List<ContactEntity>> fetchContacts();
}
