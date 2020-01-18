package com.nsappsstudio.testpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Objects;

public class ViewComplain extends AppCompatActivity {
    private TextView name;
    private TextView address;
    private TextView complain;
    private TextView lat;
    private TextView lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complain);
        String complainId = Objects.requireNonNull(getIntent().getExtras()).getString("complainId", "TEST");
        String dept = Objects.requireNonNull(getIntent().getExtras()).getString("dept", "TEST");
        name=findViewById(R.id.textView1);
        address=findViewById(R.id.textView2);
        complain=findViewById(R.id.textView3);
        lat=findViewById(R.id.textView4);
        lang=findViewById(R.id.textView5);


        LoadComplain(complainId,dept);

    }
    private void LoadComplain(String complainId,String dept){
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference mComplainRef=databaseReference.child("UserComplain").child(dept).child(complainId);
        mComplainRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserComplain userComplain=dataSnapshot.getValue(UserComplain.class);
                if (userComplain!=null) {
                    String nameString="Name: "+ userComplain.getName();
                    name.setText(nameString);
                    address.setText("Address: "+ userComplain.getAddress());
                    complain.setText("Complain: "+ userComplain.getComplain());
                    lat.setText("Latitude: "+ userComplain.getLat());
                    lang.setText("Longitude: "+ userComplain.getLang());
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
