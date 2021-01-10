package com.example.bookingmanagementgad;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText mFirstName, mLastName, mEmail, mPassword, mConfirmPassword;
    Button mRegisterButton;
    TextView mGoToLoginTextView;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.editTextFirstName);
        mLastName = findViewById(R.id.editTextLastName);
        mEmail = findViewById(R.id.editTextTextEmailAddressRegister);
        mPassword = findViewById(R.id.editTextPasswordRegister);
        mConfirmPassword = findViewById(R.id.editTextPasswordConfirmRegister);

        mRegisterButton = findViewById(R.id.registerButton);
        mGoToLoginTextView = findViewById(R.id.goToLoginPage);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBarRegister);


//        if (fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
//            finish();
//        }

        mGoToLoginTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmPassword = mConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required!");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be longer than 6 characters.");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    mPassword.setError("Passwords don't match.");
                    mConfirmPassword.setError("Passwords don't match.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user using FireBase


                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // send email for verification

                            FirebaseUser fUser = fAuth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Verification email has been sent.", Toast.LENGTH_SHORT);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "onFailure: Email not sent " + e.getMessage(), Toast.LENGTH_SHORT);
                                }
                            });

                            Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}