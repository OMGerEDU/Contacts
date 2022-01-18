package com.build.contacts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Constructor_Fragment extends Fragment {

    DatabaseReference myRef;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    ArrayList<Contact> contacts;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        View v=inflater.inflate(R.layout.fragment_constructor_, container, false);
        recyclerView = v.findViewById(R.id.Recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        myRef = FirebaseDatabase.getInstance().getReference("Contacts");
        //
        contacts = new ArrayList<>();
        contactAdapter = new ContactAdapter(this.getActivity(),contacts);
        //

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String word=" ";

                    if(bundle!= null){
                        word=bundle.getString("word");
                    }
                    Contact contact = new Contact(dataSnapshot.child("name").getValue().toString(),
                            dataSnapshot.child("phoneNum").getValue().toString(),
                            dataSnapshot.child("gender").getValue().toString());
                    if(contact.getName().contains(word)||contact.getPhoneNum().contains(word)){
                        contacts.add(contact);
                    }

                }
                contactAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(contactAdapter);





        return v;
    }
}