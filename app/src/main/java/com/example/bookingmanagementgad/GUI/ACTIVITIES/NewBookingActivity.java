package com.example.bookingmanagementgad.GUI.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bookingmanagementgad.GUI.FRAGMENTS.NewBookingFragment;
import com.example.bookingmanagementgad.R;

public class NewBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);
        getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fragmentNewBooking, new NewBookingFragment());
        ft.commit();
    }
}