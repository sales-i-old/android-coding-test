package com.salesi.coding.ui.screens;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.salesi.coding.R;
import com.salesi.coding.common.Constants;
import com.salesi.coding.entity.ContactEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FContactDetailsActivity extends AppCompatActivity {

    @Bind(R.id.txtTitle) protected TextView txtTitle;
    @Bind(R.id.txtFirstName) protected TextView txtFirstName;
    @Bind(R.id.txtLastName) protected TextView txtLastName;
    @Bind(R.id.imgGender) protected ImageView imgGender;
    @Bind(R.id.hobbiesLinearLayout) protected LinearLayout hobbiesHolder;
    @Bind(R.id.phone) protected ImageView phoneImageView;
    @Bind(R.id.email) protected ImageView emailImageView;
    @Bind(R.id.closeApp) protected FloatingActionButton floatingActionButton;
    private ContactEntity contactEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcontact_details);
        ButterKnife.bind(this);

        contactEntity = getIntent().getParcelableExtra(Constants.KEY_CONTACT_DETAILS);
        setDetailsToView();

        phoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String uri = "tel:" + contactEntity.PhoneNumber.trim() ;
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);

            }
        });
        emailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{contactEntity.Email});
                    i.putExtra(Intent.EXTRA_SUBJECT, "");
                    i.putExtra(Intent.EXTRA_TEXT, "");
                   startActivity(Intent.createChooser(i, "Send mail..."));

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    private void setDetailsToView()
    {
        if(contactEntity != null)
        {
            if(contactEntity.Title != null)
            {
                if(contactEntity.Title.equals("Miss"))
                    imgGender.setBackgroundResource(R.mipmap.female_icon);
                else
                    imgGender.setBackgroundResource(R.mipmap.male_icon);

                txtTitle.setText(contactEntity.Title);
            }

            if(contactEntity.FirstNane != null)
                txtFirstName.setText(contactEntity.FirstNane);

            if(contactEntity.LastName != null)
                txtLastName.setText(contactEntity.LastName);

            if(contactEntity.Hobbies != null)
            {
                int size = contactEntity.Hobbies.length;

                final TextView[] myTextViews = new TextView[size]; // create an empty array;

                for (int i = 0; i < size; i++) {
                    // create a new textview
                    final TextView rowTextView = new TextView(this);

                    // set some properties of rowTextView or something
                    rowTextView.setText(contactEntity.Hobbies[i]);

                    // add the textview to the linearlayout
                    hobbiesHolder.addView(rowTextView);

                    // save a reference to the textview for later
                    myTextViews[i] = rowTextView;
                }
            }

        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
