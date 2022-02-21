package com.build.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllContacts_fragment extends Fragment {

    ContactAdapter contactAdapter;
    FragmentManager fragmentManager2;
    Fragment fragment;
    Button add_contact;
    EditText Search;
    String searched;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_all_contacts_fragment, container, false);
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        contactAdapter=new ContactAdapter(this.getActivity(),contacts);
        fragmentManager2 =  getActivity().getSupportFragmentManager();
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
                fragmentManager2.beginTransaction().replace(R.id.fragmentContainerView2, fragment, null).setReorderingAllowed(true).addToBackStack(null).commit();



            }
        });



        fragment= new Constructor_Fragment();
        fragmentManager2.beginTransaction().replace(R.id.fragmentContainerView2, fragment, null).setReorderingAllowed(true).addToBackStack(null).commit();

        add_contact = v.findViewById(R.id.Add_contact_btnMain);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity() ,Activity_addContact.class);
                startActivity(intent);
            }
        });







        return v;
    }
}
