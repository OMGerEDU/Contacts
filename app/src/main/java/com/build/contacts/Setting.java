package com.build.contacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Setting extends Fragment {
    Button submit;
    EditText DefaultMessage,DefaultContact;
    String message,contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v =inflater.inflate(R.layout.fragment_setting, container, false);


        submit = v.findViewById(R.id.setting_submit);
        DefaultMessage = v.findViewById(R.id.setting_defaultMessage);
        DefaultContact = v.findViewById(R.id.setting_defaultContact);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = DefaultMessage.getText().toString();
                contact = DefaultContact.getText().toString();


                ref.child("DefaultContact").setValue(contact);
                ref.child("message").setValue(message);
                ref.child("gender").setValue("male");


            }
        });


        // Inflate the layout for this fragment
        return v;
    }
}