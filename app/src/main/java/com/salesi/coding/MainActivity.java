package com.salesi.coding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.salesi.coding.ui.adapter.TabsAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{
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
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.actionCloseApp) {
             MainActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void makePhoneCall(Context context, String phoneNum) {
        try {
            Intent dialIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phoneNum));
            context.startActivity(dialIntent);
        } catch (Exception e) {
            Toast.makeText(context,R.string.app_phone_call_permission,Toast.LENGTH_LONG).show();
        }
    }

    public static void sendEmail(Context context,String email){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",email, null));
        context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.sending_email)));
    }

    public static void shareText(Activity activity, String text, String title) {
        ShareCompat.IntentBuilder.from(activity)
                .setText(text)
                .setType("text/plain")
                .setChooserTitle(title)
                .startChooser();
    }

    public void navigate(@NonNull Fragment fragment, @Nullable String backstackTag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fvContent, fragment);
        if (backstackTag != null) {
            transaction.addToBackStack(backstackTag);
        }

        transaction.commit();
    }

}
