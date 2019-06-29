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

public class Pir extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;
    TextView pirValue,pirAlertText;
    Button refreshPir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pir);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users/User1");
        pirValue = findViewById(R.id.pirValue);
        pirAlertText = findViewById(R.id.pirAlertText);
        refreshPir = findViewById(R.id.refreshPir);


        mRef.child("pir").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                pirValue.setText(String.valueOf(value));
                if (value == 0) {
                    pirAlertText.setText("No motion detected please turn off all the appliances.");
                } else {
                    pirAlertText.setText("Motion detected in room please switch on required appliances");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pirAlertText.setText("Request Cancelled");
            }
        });

    }

    public void refreshPIR(View view){
        mRef.child("pir").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                pirValue.setText(String.valueOf(value));
                if(value==0){
                    pirAlertText.setText("No motion detected please turn off all the appliances.");
                }else{
                    pirAlertText.setText("Motion detected in room please switch on required appliances");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pirAlertText.setText("Request Cancelled");
            }
        });
    }
}
