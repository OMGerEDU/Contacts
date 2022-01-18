package com.build.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    Context context;
    ArrayList<Contact> contacts;
    Contact contact;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.phoneNum.setText(contact.getPhoneNum());
        holder.gender.setText(contact.getGender());
        holder.card.setClickable(true);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("phoneNumba");
                ref.setValue(holder.phoneNum.getText().toString());
                DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("message");
                DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("gender");
                ref3.setValue(holder.gender.getText().toString());

                if(holder.gender.getText().toString().equals("male")){
                    ref2.setValue("Do you know the difference between peanut-butter and jam?");
                }else{
                    ref2.setValue("Be careful from the shit hawks!");
                }

                holder.card.setBackgroundColor(Color.parseColor("gray"));
                Toast.makeText(context,holder.name.getText().toString()+" is selected",Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        holder.card.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    }
                }, 100);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView name, phoneNum, gender;
        CardView card;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Contactor_name);
            phoneNum = itemView.findViewById(R.id.Contactor_number);
            gender = itemView.findViewById(R.id.Contactor_gender);
            card=itemView.findViewById(R.id.cardview);


        }
    }



}
