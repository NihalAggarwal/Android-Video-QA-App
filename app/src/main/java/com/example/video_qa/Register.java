package com.example.video_qa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    TextView rsign_in;
    TextInputLayout t1,t2,name;
    ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        rsign_in = findViewById(R.id.rsign_in);
        t1 = (TextInputLayout) findViewById(R.id.r_email);
        t2 = findViewById(R.id.r_pass);
        name = (TextInputLayout) findViewById(R.id.r_name);
        p1 = findViewById(R.id.r_progressBar);
        p1.setVisibility(View.INVISIBLE);
        rsign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }

    public void signuphere(View view) {

        String n = name.getEditText().getText().toString();
        String email = t1.getEditText().getText().toString();
        String password = t2.getEditText().getText().toString();

        if(!email.isEmpty() && !password.isEmpty()) {
            p1.setVisibility(View.VISIBLE);
            FirebaseAuth r_auth = FirebaseAuth.getInstance();

            r_auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        p1.setVisibility(View.INVISIBLE);
                        name.getEditText().setText("");
                        t1.getEditText().setText("");
                        t2.getEditText().setText("");
                        Toast.makeText(getApplicationContext(), "Sign Up Success!!", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    } else {
                        p1.setVisibility(View.INVISIBLE);
                        name.getEditText().setText("");
                        t1.getEditText().setText("");
                        t2.getEditText().setText("");
                        Toast.makeText(getApplicationContext(), "Not Successful!!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter Email or Password", Toast.LENGTH_LONG).show();
        }

    }
}