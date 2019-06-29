package com.example.sourabh.sourabh_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button buttonreg1;
    private EditText edittxtemail;
    private EditText edittxtpass;
    private TextView txtviewsignin;
    private ProgressDialog prgDial;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        prgDial = new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,ViewProfile.class));
        }
        buttonreg1 = (Button) findViewById(R.id.b1Reg);

        edittxtemail = (EditText) findViewById(R.id.editTextEmail);
        edittxtpass = (EditText) findViewById(R.id.editTextPass);
        txtviewsignin = (TextView) findViewById(R.id.signin);

       buttonreg1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = edittxtemail.getText().toString().trim();
               String password = edittxtpass.getText().toString().trim();

               if (TextUtils.isEmpty(email)) {
                 Toast.makeText(getApplicationContext(),"Please Enter mail",Toast.LENGTH_LONG).show();
                   return;
               }

               if (TextUtils.isEmpty(password)) {
                   Toast.makeText(getApplicationContext(),"Please Enter password",Toast.LENGTH_LONG).show();
                   return;
               }

               prgDial.setMessage("Registering User...Please wait");
               prgDial.show();
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prgDial.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, ViewProfile.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Could not register...Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
           }
       });
        txtviewsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}
