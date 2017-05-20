package com.salesi.coding.ui.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salesi.coding.MainActivity;
import com.salesi.coding.R;
import com.salesi.coding.entity.ContactEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liangbindu on 20/05/2017.
 */

public class ContactDetailFragment extends Fragment {
    public static final String ARG_CONTACT = "_contactEntry";
    public ContactEntity contactEntity;

    @Nullable @Bind(R.id.tvFirstName) protected TextView tvFirstName;
    @Nullable @Bind(R.id.tvLastName) protected TextView tvLastName;
    @Nullable @Bind(R.id.tvAddress) protected TextView tvAddress;
    @Nullable @Bind(R.id.tvPhone) protected TextView tvPhone;
    @Nullable @Bind(R.id.tvEmail) protected TextView tvEmail;
    @Nullable @Bind(R.id.tvHobbies) protected TextView tvHobbies;
    @Nullable @Bind(R.id.ivPhone) protected ImageView ivPhone;
    @Nullable @Bind(R.id.ivEmail) protected ImageView ivEmail;
    @Nullable @Bind(R.id.btnShare) protected Button btnShare;
    @Nullable @Bind(R.id.llEmail) protected LinearLayout llEmail;
    @Nullable @Bind(R.id.llPhone) protected LinearLayout llPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_contact_detail,container,false);
        if (getArguments().containsKey(ARG_CONTACT)) {
            contactEntity = (ContactEntity) getArguments().get(ARG_CONTACT);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupView();
    }

    private void setupView(){
        if(contactEntity!=null){
            tvFirstName.setText(contactEntity.FirstName);
            tvLastName.setText(contactEntity.LastName);
            tvAddress.setText(contactEntity.Address.toString());
            tvPhone.setText(contactEntity.PhoneNumber);
            tvEmail.setText(contactEntity.Email);
            if(contactEntity.Hobbies!=null&&contactEntity.Hobbies.length>0){
                StringBuilder sb=new StringBuilder();
                for(int i=0 ;i< contactEntity.Hobbies.length;i++){
                    if(contactEntity.Hobbies[i]!=null&& !contactEntity.Hobbies[i].isEmpty()) {
                        sb.append(contactEntity.Hobbies[i]+" ");
                    }
                }
                tvHobbies.setText(sb.toString());
            }

            if(contactEntity.Email!=null&&!contactEntity.Email.isEmpty()){
                ivEmail.setVisibility(View.VISIBLE);
                ivEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.sendEmail(getActivity(), contactEntity.Email);
                    }
                });
            }else{
                ivEmail.setVisibility(View.GONE);
            }

            if(contactEntity.PhoneNumber!=null&&!contactEntity.PhoneNumber.isEmpty()){
                llPhone.setVisibility(View.VISIBLE);
                ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.makePhoneCall(getActivity(), contactEntity.PhoneNumber);
                    }
                });
            }else{
                llPhone.setVisibility(View.GONE);
            }

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.shareText(getActivity(),tvHobbies.getText().toString(), getString(R.string.share_the_hobbies));
                }
            });

        }
    }
}
