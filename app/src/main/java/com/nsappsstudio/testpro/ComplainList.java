package com.nsappsstudio.testpro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ComplainList extends AppCompatActivity {
    private Context context;
    private String dept;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list);
        context=this;
        loadDeptList();
    }
    private void loadDeptList(){
        Spinner dept_spinner=findViewById(R.id.spinner2);
        ArrayList<String> departments = new ArrayList<>();

        departments.add("Select Department");
        //add all the department here
        departments.add("Fire Department");
        departments.add("Electricity Department");

        //Spinner 1
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept_spinner.setAdapter(adapter);

        dept_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    //do nothing
                }
                else {
                    dept = (String) adapterView.getItemAtPosition(i);
                    LoadList();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

    }
    private void LoadList(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference mComplainRef=databaseReference.child("UserComplain").child(dept);
        final ListView listView=findViewById(R.id.list_complain);

        final List<String> complainIds=new ArrayList<>();
        mComplainRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String complainId=dataSnapshot.getKey();
                complainIds.add(complainId);
                ArrayAdapter arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,complainIds);
                listView.setAdapter(arrayAdapter);



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // what happen when user click on list item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,complainIds.get(position),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ViewComplain.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("complainId",complainIds.get(position));
                intent.putExtra("dept",dept);

                startActivity(intent);
            }
        });
    }
}
