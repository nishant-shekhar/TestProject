package com.nsappsstudio.testpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterComplain extends AppCompatActivity {
    private DatabaseReference databaseReference;
    //private FirebaseAuth auth;
    private String dept;
    private Context context;
    private boolean dept_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complain);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        //auth= FirebaseAuth.getInstance();
        context=this;
        dept_selected=false;
        setSpinner();


    }
    private void setSpinner(){
        Spinner dept_spinner=findViewById(R.id.spinner);
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
                    dept_selected = false;
                }
                else {
                    dept = (String) adapterView.getItemAtPosition(i);
                    dept_selected =true;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                dept_selected =false;

            }
        });

    }
    public void registerComplain(View view){
        EditText nameTextView=findViewById(R.id.editText);
        EditText addressTextView=findViewById(R.id.editText2);
        EditText complainTextView=findViewById(R.id.editText3);
        EditText userIdView=findViewById(R.id.editText4);

        String name=nameTextView.getText().toString();
        String address=addressTextView.getText().toString();
        String complain=complainTextView.getText().toString();
        String userId=userIdView.getText().toString().trim();
        //String userId=auth.getUid();

        if (name.length()>0 && address.length()>0 && complain.length()>0 && dept_selected && userId.length()>0){

            UserComplain userComplain=new UserComplain(name,address,complain,null,null,25.1,87.2);

            DatabaseReference userComplainRef=databaseReference.child("UserComplain").child(dept).child(userId);
            userComplainRef.setValue(userComplain).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(context,"Successfully Complain Registered",Toast.LENGTH_LONG).show();
                }
            });
        }


    }


}
