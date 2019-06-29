package com.example.sourabh.sourabh_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ViewProfile extends AppCompatActivity {

    CardView TemperatureSensor,PIRSensor,SmokeSensor,HumiditySensor;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        TemperatureSensor=findViewById(R.id.temp);
        HumiditySensor=findViewById(R.id.humidity);
        PIRSensor=findViewById(R.id.pir);
        SmokeSensor=findViewById(R.id.smoke);
        logout=(Button)findViewById(R.id.button);

        TemperatureSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Temperature.class));
            }
        });
        HumiditySensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Humidity.class));
            }
        });

        PIRSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Pir.class));
            }
        });
        SmokeSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Somke.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfile.this.finish();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ViewProfile.this,LoginActivity.class));
            }
        });
    }
}
