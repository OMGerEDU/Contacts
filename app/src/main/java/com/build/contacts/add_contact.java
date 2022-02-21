package com.build.contacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_contact extends Fragment {


    EditText contactName,contactNum;
    RadioGroup radioGroup;
    Button addBtn;
    Contact contact;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Write a message to the database

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Contacts");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_contact, container, false);
        radioGroup=v.findViewById(R.id.radioGroup);
        contactName=v.findViewById(R.id.contact_name);
        contactNum=v.findViewById(R.id.contact_number);
        addBtn=v.findViewById(R.id.add_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contactName.getText().length()>2){
                    if(contactNum.getText().length()==10){
                        int radioId=radioGroup.getCheckedRadioButtonId();
                        if(radioId%2!=0) {
                            contact = new Contact(contactName.getText().toString(), contactNum.getText().toString(), "male");
                        }else{
                            contact=new Contact(contactName.getText().toString(),contactNum.getText().toString(),"female");
                        }

                        myRef.push().setValue(contact);
                    }else{
                        Toast.makeText(getActivity(),"the number must  have 10 numbers",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"the name must contain atleast 2 letters buddy",Toast.LENGTH_SHORT).show();
                }
                FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, AllContacts_fragment.class, null).setReorderingAllowed(true).addToBackStack(null).commit();


            }
        });

        return v;
    }





}