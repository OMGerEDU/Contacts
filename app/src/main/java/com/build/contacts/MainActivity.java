package com.build.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Button smser;
    String phoneNumber="0525237377";
    String smsText="Mammy i pooped m'pants";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.search_contact_menu_item:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, Search_Fragment.class, null).setReorderingAllowed(true).addToBackStack(null).commit();


                break;

            case R.id.add_contact_menu_item:
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, add_contact.class, null).setReorderingAllowed(true).addToBackStack(null).commit();


                break;

            case R.id.back_to_reality_menu_item:

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,  Picture_Fragment.class, null).setReorderingAllowed(true).addToBackStack(null).commit();


                break;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("phoneNumba").setValue("056-6666666");
        ref.child("message").setValue("Be careful from the shit hawks!");
        ref.child("gender").setValue("male");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phoneNumber = snapshot.child("phoneNumba").getValue(String.class);
                smsText = snapshot.child("message").getValue(String.class);
                if (smsText.isEmpty()) {
                    if (snapshot.child("gender").equals("male")) {
                        ref.child("message").setValue("Do you know the difference between peanut-butter and jam?");
                    } else {
                        ref.child("message").setValue("Be careful from the shit hawks!");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            phoneNumber=extra.getString("name");
        }
        setContentView(R.layout.activity_main);
        Button caller=findViewById(R.id.callBtn);
        smser=findViewById(R.id.smsBtn);

        caller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        smser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms();
            }
        });
    }


    private void sms() {
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber,null,smsText,null,null);
        Toast.makeText(MainActivity.this,"Message Sent",Toast.LENGTH_LONG).show();
    }

    private void call() {
        String phone="tel:"+phoneNumber;
        Intent call=new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse(phone));
        startActivity(call);
    }

}