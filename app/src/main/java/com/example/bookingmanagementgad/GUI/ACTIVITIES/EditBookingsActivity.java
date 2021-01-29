package com.example.bookingmanagementgad.GUI.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bookingmanagementgad.GUI.FRAGMENTS.EditBookingsFragment;
import com.example.bookingmanagementgad.GUI.FRAGMENTS.NewBookingFragment;
import com.example.bookingmanagementgad.R;

public class EditBookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bookings);

        getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.fragmentEditBookings, new EditBookingsFragment());
        ft.commit();
    }
}