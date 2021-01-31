package com.example.bookingmanagementgad.GUI.ACTIVITIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.bookingmanagementgad.GUI.FRAGMENTS.ProfileFragment;
import com.example.bookingmanagementgad.GUI.FRAGMENTS.UpdateSingleBookingFragment;
import com.example.bookingmanagementgad.R;

public class UpdateSingleBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_single_booking);

        //getting the document reference from the EditBookingsFragment
        Intent intent = getIntent();
        String message = intent.getStringExtra("documentID");

        //sending the data to the UpdateSingleBooking fragment
        Bundle bundle=new Bundle();
        bundle.putString("documentID", message);
        //set Fragmentclass Arguments
        UpdateSingleBookingFragment fragobj = new UpdateSingleBookingFragment();
        fragobj.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.fragmentUpdateBooking, fragobj).commit();
    }
}