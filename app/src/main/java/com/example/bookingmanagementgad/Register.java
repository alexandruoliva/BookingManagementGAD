package com.example.bookingmanagementgad;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mFirstName, mLastName, mEmail, mPassword, mConfirmPassword;
    Button mRegisterButton;
    TextView mLoginButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.editTextFirstName);
        mLastName = findViewById(R.id.editTextLastName);
        mEmail = findViewById(R.id.editTextTextEmailAddressRegister);
        mPassword = findViewById(R.id.editTextPasswordRegister);
        mConfirmPassword = findViewById(R.id.editTextPasswordConfirmRegister);

        mRegisterButton =findViewById(R.id.registerBtn);
        mLoginButton = findViewById(R.id.goToLoginPage);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);





    }
}