package com.salesi.coding.ui.screens;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by davemurphy on 28/04/2017.
 */

public class ContactDetailActivity extends Activity {
    private static final String TAG = "ContactDetailActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "ContactDetailActivity");
    }

    //TODO
    // Use something like Volley to get the json response string from http://www.mocky.io/v2/58e4f80f1100004e00bfc4b3
    // parse and traverse the json Object for the relevant fields and display here
}
