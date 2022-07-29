package com.example.video_qa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView lsignup;
    TextInputLayout t1,t2,name;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1 = findViewById(R.id.l_email);
        t2 = findViewById(R.id.l_password);
        p1 = findViewById(R.id.l_progress_bar);
        p1.setVisibility(View.INVISIBLE);
        lsignup = findViewById(R.id.lsign_up);
        lsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }

    public void signinhere(View view) {

        String email = t1.getEditText().getText().toString();
        String passsword = t2.getEditText().getText().toString();

        if(!email.isEmpty()&&!passsword.isEmpty()) {
            p1.setVisibility(View.VISIBLE);
            FirebaseAuth r_auth = FirebaseAuth.getInstance();

            r_auth.signInWithEmailAndPassword(email, passsword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        p1.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("email", r_auth.getCurrentUser().getEmail());
                        intent.putExtra("uid", r_auth.getCurrentUser().getUid());
                        startActivity(intent);
                        t1.getEditText().setText("");
                        t2.getEditText().setText("");
                    } else {
                        p1.setVisibility(View.INVISIBLE);
                        t1.getEditText().setText("");
                        t2.getEditText().setText("");
                        Toast.makeText(getApplicationContext(), "Login Failed!!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter Email or Password", Toast.LENGTH_LONG).show();
        }
    }
}