package com.salesi.coding.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.salesi.coding.R;
import com.salesi.coding.ui.screens.FContacts;

/**
 * Tabs adapter for ViewPager
 * Copyright © 2017 sales­i
 */

public class TabsAdapter extends FragmentStatePagerAdapter {
    private final Context context;

    public TabsAdapter(final FragmentManager fm, final Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 : {
                return FContacts.instance();
            }
            default : {
                return null;
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0 : {
                return context.getString(R.string.title_tab_question_1);
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return context.getResources().getInteger(R.integer.total_tab_count);
    }
}
