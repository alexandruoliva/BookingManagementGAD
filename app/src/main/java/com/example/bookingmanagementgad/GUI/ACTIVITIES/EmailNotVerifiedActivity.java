package com.example.bookingmanagementgad.GUI.ACTIVITIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookingmanagementgad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailNotVerifiedActivity extends AppCompatActivity {

    Button mVerifyButton;
    private FirebaseAuth fAuth;
    private FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_verified);
        mVerifyButton = findViewById(R.id.verifyEmailButton);
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        if (fAuth.getCurrentUser() != null) {
            fUser = fAuth.getCurrentUser();
            if (fUser != null && !fUser.isEmailVerified()) {
                mVerifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EmailNotVerifiedActivity.this, "Verification email has been sent.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EmailNotVerifiedActivity.this, "onFailure: Email not sent " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            } else if(fUser.isEmailVerified()) {
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        }



    }
}