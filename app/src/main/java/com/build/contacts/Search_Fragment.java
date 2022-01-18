package com.build.contacts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Search_Fragment extends Fragment {

    ContactAdapter contactAdapter;
    FragmentManager fragmentManager2;
    Fragment fragment;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_search, container, false);
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        contactAdapter=new ContactAdapter(this.getActivity(),contacts);
        EditText search=v.findViewById(R.id.searchable);
        EditText message=v.findViewById(R.id.userMessage);
        fragmentManager2 =  getActivity().getSupportFragmentManager();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Bundle bundle = new Bundle();
                bundle.putString("word",search.getText().toString());


                fragment= new Constructor_Fragment();
                fragment.setArguments(bundle);
                fragmentManager2.beginTransaction().replace(R.id.fragmentContainerView2, fragment, null).setReorderingAllowed(true).addToBackStack(null).commit();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("message");
                ref.setValue(message.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        return v;
    }
}
