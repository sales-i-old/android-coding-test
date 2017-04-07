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
    @Bind(R.id.txtAddress1)
    protected TextView txtAddress1;
    @Bind(R.id.txtAddress2)
    protected TextView txtAddress2;
    @Bind(R.id.txtAddress3)
    protected TextView txtAddress3;
    @Bind(R.id.txtAddress4)
    protected TextView txtAddress4;
    @Bind(R.id.txtTown)
    protected TextView txtTown;
    @Bind(R.id.txtCounty)
    protected TextView txtCounty;
    @Bind(R.id.txtPostcode)
    protected TextView txtPostcode;
    @Bind(R.id.txtCountry)
    protected TextView txtCountry;


    @Bind(R.id.imgGender) protected ImageView imgGender;
    @Bind(R.id.hobbiesLinearLayout) protected LinearLayout hobbiesHolder;
    @Bind(R.id.phone)
    private ImageView phoneImageView;
    @Bind(R.id.email)
    private ImageView emailImageView;
    @Bind(R.id.closeApp)
    private FloatingActionButton floatingActionButton;
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
                    // create a new text view
                    final TextView rowTextView = new TextView(this);

                    // set some properties of rowTextView or something
                    rowTextView.setText(contactEntity.Hobbies[i]);

                    // add the text view to the linear layout
                    hobbiesHolder.addView(rowTextView);

                    // save a reference to the text view for later
                    myTextViews[i] = rowTextView;
                }
            }

            // Address details
            if (contactEntity.Address != null) {
                if (contactEntity.Address.Address1 != null)
                    txtAddress1.setText(contactEntity.Address.Address1);
                else
                    txtAddress1.setVisibility(View.GONE);

                if (contactEntity.Address.Address2 != null)
                    txtAddress2.setText(contactEntity.Address.Address2);
                else
                    txtAddress2.setVisibility(View.GONE);

                if (contactEntity.Address.Address3 != null)
                    txtAddress3.setText(contactEntity.Address.Address3);
                else
                    txtAddress3.setVisibility(View.GONE);

                if (contactEntity.Address.Address4 != null)
                    txtAddress4.setText(contactEntity.Address.Address4);
                else
                    txtAddress4.setVisibility(View.GONE);

                if (contactEntity.Address.Town != null)
                    txtTown.setText(contactEntity.Address.Town);
                else
                    txtTown.setVisibility(View.GONE);

                if (contactEntity.Address.County != null)
                    txtCounty.setText(contactEntity.Address.County);
                else
                    txtCounty.setVisibility(View.GONE);

                if (contactEntity.Address.Postcode != null)
                    txtPostcode.setText(contactEntity.Address.Postcode);
                else
                    txtPostcode.setVisibility(View.GONE);

                if (contactEntity.Address.Country != null)
                    txtCountry.setText(contactEntity.Address.Country);
                else
                    txtCountry.setVisibility(View.GONE);

            }


        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
