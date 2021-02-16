package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UpdateSingleBookingFragment extends Fragment {


    private static final String TAG = "UpdateBookingFragment";
    private Button mButtonUpdateBooking;
    private EditText mUpdateFirstName, mUpdateLastName, mUpdatePhoneNumber;
    private EditText mUpdatePricePerNightBooking, mUpdateNumberOfRoomsBooking;
    private EditText mUpdateCheckInDateBooking, mUpdateCheckOutDateBooking;
    private EditText mUpdateTypeOfBooking;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private CheckBox mUpdateCheckBoxRoom1, mUpdateCheckBoxRoom2, mUpdateCheckBoxRoom3;
    private CheckBox mUpdateCheckBoxRoom4, mUpdateCheckBoxRoom5, mUpdateCheckBoxRoom6;
    private CheckBox mUpdateCheckBoxRoomUnderHouse, mUpdateCheckBoxRoomAll;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String userID = fAuth.getCurrentUser().getUid();

    private boolean room1;
    private boolean room2;
    private boolean room3;
    private boolean room4;
    private boolean room5;
    private boolean room6;
    private boolean roomUnderHouse;
    private boolean allRooms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_update_single_booking, container, false);
        mButtonUpdateBooking = view.findViewById(R.id.buttonUpdateBooking);
        mUpdateLastName = view.findViewById(R.id.editTextLastNameUpdate);
        mUpdateFirstName = view.findViewById(R.id.editTextFirstNameUpdate);
        mUpdatePhoneNumber = view.findViewById(R.id.editTextPhoneNumberUpdate);
        mUpdatePricePerNightBooking = view.findViewById(R.id.editTextPricePerNightUpdate);
        mUpdateNumberOfRoomsBooking = view.findViewById(R.id.editTextNumberOfRoomsUpdate);
        mUpdateCheckInDateBooking = view.findViewById(R.id.editTextCheckInDateUpdate);
        mUpdateCheckOutDateBooking = view.findViewById(R.id.editTextCheckOutDateUpdate);
        mUpdateTypeOfBooking = view.findViewById(R.id.editTextTypeOfBookingUpdate);

        mUpdateCheckBoxRoom1 = view.findViewById(R.id.updateRoom1CheckBox);
        checkBoxes.add(mUpdateCheckBoxRoom1);
        mUpdateCheckBoxRoom2 = view.findViewById(R.id.updateRoom2CheckBox);
        checkBoxes.add(mUpdateCheckBoxRoom2);
        mUpdateCheckBoxRoom3 = view.findViewById(R.id.updateRoom3CheckBox);
        checkBoxes.add(mUpdateCheckBoxRoom2);
        mUpdateCheckBoxRoom4 = view.findViewById(R.id.updateRoom4CheckBox);
        checkBoxes.add(mUpdateCheckBoxRoom4);
        mUpdateCheckBoxRoom5 = view.findViewById(R.id.updateRoom5CheckBox);
        checkBoxes.add(mUpdateCheckBoxRoom5);
        mUpdateCheckBoxRoom6 = view.findViewById(R.id.updateRoom6CheckBox);
        checkBoxes.add(mUpdateCheckBoxRoom6);
        mUpdateCheckBoxRoomUnderHouse = view.findViewById(R.id.roomUnderHouseCheckBox);
        checkBoxes.add(mUpdateCheckBoxRoomUnderHouse);
        mUpdateCheckBoxRoomAll = view.findViewById(R.id.updateRoomAllCheckBox);
        checkBoxes.add(mUpdateCheckBoxRoomAll);

        Bundle bundle = this.getArguments();
        String documentId = bundle.getString("documentID");

        DocumentReference documentReference = db.collection("users").document(userID).collection("bookings").document(documentId);

        fetchBookingData(documentReference);

        updateBookingData(documentId, documentReference);

        return view;
    }

    private void fetchBookingData(DocumentReference documentReference) {
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                Booking booking = documentSnapshot.toObject(Booking.class);

                mUpdateFirstName.setText(booking.getFirstName());
                mUpdateLastName.setText(booking.getLastName());
                mUpdatePhoneNumber.setText(booking.getPhoneNumber());
                mUpdatePricePerNightBooking.setText(String.valueOf(booking.getPricePerNight()));
                mUpdateNumberOfRoomsBooking.setText(String.valueOf(booking.getNumberOfRooms()));
                mUpdateCheckInDateBooking.setText(booking.getCheckInDate());
                mUpdateCheckOutDateBooking.setText(booking.getCheckOutDate());
                mUpdateTypeOfBooking.setText(booking.getTypeOfBooking());

                if (booking.isRoom1()) {
                    mUpdateCheckBoxRoom1.setChecked(true);
                }
                if (booking.isRoom2()) {
                    mUpdateCheckBoxRoom2.setChecked(true);
                }
                if (booking.isRoom3()) {
                    mUpdateCheckBoxRoom3.setChecked(true);
                }
                if (booking.isRoom4()) {
                    mUpdateCheckBoxRoom4.setChecked(true);
                }
                if (booking.isRoom5()) {
                    mUpdateCheckBoxRoom6.setChecked(true);
                }
                if (booking.isRoom6()) {
                    mUpdateCheckBoxRoom6.setChecked(true);
                }
                if (booking.isRoomUnderHouse()) {
                    mUpdateCheckBoxRoomUnderHouse.setChecked(true);
                }
                if (booking.isAllRooms())
                    mUpdateCheckBoxRoomAll.setChecked(true);
            }


        });
    }

    private void updateBookingData(String documentId, DocumentReference documentReference) {
        mButtonUpdateBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUpdateCheckBoxRoom1.isChecked()) {
                    room1 = true;
                }
                if (mUpdateCheckBoxRoom2.isChecked()) {
                    room2 = true;
                }
                if (mUpdateCheckBoxRoom3.isChecked()) {
                    room3 = true;
                }
                if (mUpdateCheckBoxRoom4.isChecked()) {
                    room4 = true;
                }
                if (mUpdateCheckBoxRoom5.isChecked()) {
                    room5 = true;
                }
                if (mUpdateCheckBoxRoom6.isChecked()) {
                    room6 = true;
                }
                if (mUpdateCheckBoxRoomUnderHouse.isChecked()) {
                    roomUnderHouse = true;
                }
                if (mUpdateCheckBoxRoomAll.isChecked()) {
                    allRooms = true;
                }

                Booking booking = new Booking(mUpdateFirstName.getText().toString(),
                        mUpdateLastName.getText().toString(),
                        mUpdatePhoneNumber.getText().toString(),
                        mUpdateTypeOfBooking.getText().toString(),
                        Integer.parseInt(mUpdatePricePerNightBooking.getText().toString()),
                        Integer.parseInt(mUpdateNumberOfRoomsBooking.getText().toString()),
                        mUpdateCheckInDateBooking.getText().toString(),
                        mUpdateCheckOutDateBooking.getText().toString(), room1,
                        room2, room3, room4, room5, room6, roomUnderHouse, allRooms);

                documentReference.set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSucces: booking is updated, with id " + documentId);
                        Toast.makeText(getActivity(), "Booking has been updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                        Toast.makeText(getActivity(), "Fail! The booking has not been updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}