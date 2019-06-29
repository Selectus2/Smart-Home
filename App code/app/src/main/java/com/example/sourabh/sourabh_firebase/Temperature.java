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

public class Temperature extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;
    TextView temperatureValue,temperatureAlertText;
    Button refreshTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users/User1");
        temperatureValue = findViewById(R.id.temperatureValue);
        temperatureAlertText = findViewById(R.id.temperatureAlertText);
        refreshTemperature = findViewById(R.id.refreshTemperature);
    }

    public void refreshTemperature(View view){
        mRef.child("temp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                temperatureValue.setText(String.valueOf(value));
                if(value<=25){
                    temperatureAlertText.setText("Please power on AC");
                }else{
                    temperatureAlertText.setText("AC is no more required");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                temperatureAlertText.setText("Request Cancelled");
            }
        });
    }
}
