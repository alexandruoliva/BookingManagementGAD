package com.example.bookingmanagementgad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginButton;
    TextView mGoToRegisterButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.editTextEmailAddressLogin);
        mPassword = findViewById(R.id.editTextPasswordLogin);
        mLoginButton = findViewById(R.id.loginButton);
        mGoToRegisterButton = findViewById(R.id.textViewToRegister);
        progressBar = findViewById(R.id.progressBarLogin);

        mGoToRegisterButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}