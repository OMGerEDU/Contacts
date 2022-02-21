package com.build.contacts;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class AllContacts_fragment extends Fragment {

    ContactAdapter contactAdapter;
    FragmentManager fragmentManager;
    Fragment fragment;
    Button add_contact;
    EditText Search;
    String searched;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_all_contacts_fragment, container, false);
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        contactAdapter=new ContactAdapter(this.getActivity(),contacts);
        fragmentManager =  getActivity().getSupportFragmentManager();
        Search = v.findViewById(R.id.et_searchEditText);

        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searched = Search.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("word",searched);
                fragment= new Constructor_Fragment();
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, fragment, null).setReorderingAllowed(true).addToBackStack(null).commit();



            }
        });



        fragment= new Constructor_Fragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, fragment, null).setReorderingAllowed(true).addToBackStack(null).commit();

        add_contact = v.findViewById(R.id.Add_contact_btnMain);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, add_contact.class, null).setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });







        return v;
    }
}
