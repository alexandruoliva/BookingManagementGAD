package com.example.bookingmanagementgad.GUI.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bookingmanagementgad.GUI.FRAGMENTS.ProfileFragment;
import com.example.bookingmanagementgad.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fragmentNewBooking, new ProfileFragment());
        ft.commit();
    }
}