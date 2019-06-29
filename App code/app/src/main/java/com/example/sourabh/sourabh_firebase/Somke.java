package com.example.sourabh.sourabh_firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Somke extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;
    TextView smokeValue,smokeAlertText;
    Button refreshSmoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somke);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users/User1");
        smokeValue = findViewById(R.id.smokeValue);
        smokeAlertText = findViewById(R.id.smokeAlertText);
        refreshSmoke= findViewById(R.id.refreshSmoke);

        mRef.child("smoke").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                smokeValue.setText(String.valueOf(value));
                if(value==0){
                    smokeAlertText.setText("No Smoke Detected. Safe Zone");
                }else{
                    smokeAlertText.setText("Smoke Detected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                smokeAlertText.setText("Request Cancelled");
            }
        });

    }

    public void refreshSmoke(View view){
        mRef.child("smoke").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                smokeValue.setText(String.valueOf(value));
                if(value==0){
                    smokeAlertText.setText("No Smoke Detected. Safe Zone");
                }else{
                    smokeAlertText.setText("Smoke Detected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                smokeAlertText.setText("Request Cancelled");
            }
        });
    }
}
