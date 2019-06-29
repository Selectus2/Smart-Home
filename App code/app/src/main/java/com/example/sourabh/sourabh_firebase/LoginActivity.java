package com.example.sourabh.sourabh_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class LoginActivity extends AppCompatActivity  {
    private Button buttonreg;
    private EditText edittxtemail;
    private EditText edittxtpass;
    private TextView txtviewsignup;
    private ProgressDialog prgDial;
    public static FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this,ViewProfile.class));
        }
        prgDial = new ProgressDialog(this);

        buttonreg = (Button) findViewById(R.id.b1Reg);

        edittxtemail = (EditText) findViewById(R.id.editTextEmail);
        edittxtpass = (EditText) findViewById(R.id.editTextPass);
        txtviewsignup = (TextView) findViewById(R.id.signup);

        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edittxtemail.getText().toString().trim();
                String password = edittxtpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter password", Toast.LENGTH_LONG).show();
                    return;

                }

                prgDial.setMessage("Please wait...");
                prgDial.show();

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        prgDial.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(LoginActivity.this, "Signedin Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, ViewProfile.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Username/Password...", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        txtviewsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
