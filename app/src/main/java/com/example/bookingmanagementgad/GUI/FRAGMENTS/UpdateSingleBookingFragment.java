package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class UpdateSingleBookingFragment extends Fragment {


    private static final String TAG = "UpdateBookingFragment";
    private Button mButtonUpdateBooking;
    private EditText mUpdateFirstName, mUpdateLastName, mUpdatePhoneNumber;
    private EditText mUpdatePricePerNightBooking, mUpdateNumberOfRoomsBooking;
    private EditText mUpdateCheckInDateBooking, mUpdateCheckOutDateBooking;
    private EditText mUpdateTypeOfBooking;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String userID = fAuth.getCurrentUser().getUid();

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

            }
        });
    }

    private void updateBookingData(String documentId, DocumentReference documentReference) {
        mButtonUpdateBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> rooms = new ArrayList<>();
                rooms.add("bla bla");
                Booking booking = new Booking(mUpdateFirstName.getText().toString(),
                        mUpdateLastName.getText().toString(),
                        mUpdatePhoneNumber.getText().toString(),
                        mUpdateTypeOfBooking.getText().toString(),
                        Integer.parseInt(mUpdatePricePerNightBooking.getText().toString()),
                        Integer.parseInt(mUpdateNumberOfRoomsBooking.getText().toString()),
                        mUpdateCheckInDateBooking.getText().toString(),
                        mUpdateCheckOutDateBooking.getText().toString(), rooms);

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