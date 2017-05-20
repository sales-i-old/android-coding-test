package com.salesi.coding;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.salesi.coding.ui.adapter.TabsAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.layout_tabs) protected TabLayout mTabLayout;
    @Bind(R.id.view_pager) protected ViewPager mViewPager;
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), getApplicationContext());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
        System.exit(0);
        return super.onOptionsItemSelected(item);
    }
}
