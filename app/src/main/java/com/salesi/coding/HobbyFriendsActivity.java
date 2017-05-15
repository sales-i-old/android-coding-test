package com.salesi.coding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.salesi.coding.entity.ContactEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HobbyFriendsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) protected Toolbar mToolbar;

    @Bind(R.id.view_hobbyfriends_name) protected TextView mName;
    @Bind(R.id.view_hobbyfriends_friends) protected TextView mFriends;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby_friends);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        Bundle b = getIntent().getExtras();
        position = b.getInt("position");

        loadHobbyFriends();
    }

    private void loadHobbyFriends() {
        MainApp mainApp = ((MainApp) getApplication());
        ContactEntity contact = mainApp.getComponent().contactService().fetchContact(position);
        List<ContactEntity> friends = mainApp.getComponent().contactService().hobbyFriends(position);

        mName.setText(contact.getCompleteName());
        String friendList = "";
        for (ContactEntity friend : friends) {
            friendList += friend.getCompleteName() + ", ";
        }
        mFriends.setText(friendList);

        Log.d("H", "Hobby");
    }
}
