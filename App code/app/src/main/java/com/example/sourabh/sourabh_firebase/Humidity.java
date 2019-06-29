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

public class Humidity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;
    TextView humidityValue,humidityAlertText;
    Button refreshHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users/User1");
        humidityValue = findViewById(R.id.humidityValue);
        humidityAlertText = findViewById(R.id.humidityAlertText);
        refreshHumidity = findViewById(R.id.refreshHumidity);
    }

    public void refreshHumidity(View view){
        mRef.child("humidity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                humidityValue.setText(String.valueOf(value));
                if(value<=25){
                    humidityAlertText.setText("Close the Door");
                }else{
                    humidityAlertText.setText("Open the Doors and Windows.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                humidityAlertText.setText("Request Cancelled");
            }
        });
    }
}
