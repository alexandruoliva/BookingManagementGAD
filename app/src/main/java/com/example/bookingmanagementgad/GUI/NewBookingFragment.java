package com.example.bookingmanagementgad.GUI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewBookingFragment extends Fragment {

    private static final String TAG = "NewBookingFragment";
    private Button mButtonCreateNewBooking;
    private EditText mEditFirstName, mEditLastName, mEditPhoneNumber;
    private EditText mEditPricePerNightBooking, mEditTextNumberOfRoomsBooking;
    private EditText mEditTextCheckInDateBooking, mEditTextCheckOutDateBooking;
    private EditText mEditTypeOfBooking;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseUser fUser = fAuth.getCurrentUser();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_booking, container, false);
        mButtonCreateNewBooking = view.findViewById(R.id.buttonCreateNewBooking);
        mEditLastName = view.findViewById(R.id.editTextLastNameBooking);
        mEditFirstName = view.findViewById(R.id.editTextFirstNameBooking);
        mEditPhoneNumber = view.findViewById(R.id.editTextPhoneNumberBooking);
        mEditPricePerNightBooking = view.findViewById(R.id.editTextPricePerNightBooking);
        mEditTextNumberOfRoomsBooking = view.findViewById(R.id.editTextNumberOfRoomsBooking);
        mEditTextCheckInDateBooking = view.findViewById(R.id.editTextCheckInDateBooking);
        mEditTextCheckOutDateBooking = view.findViewById(R.id.editTextCheckOutDateBooking);
        mEditTypeOfBooking = view.findViewById(R.id.editTextTypeOfBooking);

        mButtonCreateNewBooking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("bookings").document(userID);
                Booking booking = new Booking(mEditFirstName.getText().toString(),
                        mEditLastName.getText().toString(),
                        mEditPhoneNumber.getText().toString(),
                        mEditTypeOfBooking.getText().toString(),
                        Integer.parseInt(mEditPricePerNightBooking.getText().toString()),
                        Integer.parseInt(mEditTextNumberOfRoomsBooking.getText().toString()),
                        mEditTextCheckInDateBooking.getText().toString(),
                        mEditTextCheckOutDateBooking.getText().toString());

//                Booking booking = new Booking("Alexandru", "Oliva", "Airbnb", "0742096316", 234, 2, 233, 245);

                CollectionReference userRef = fStore.collection("users");
                userRef.document(userID).collection("bookings").add(booking);

                documentReference.set(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(v.getContext(), "Booking has been added successfully.", Toast.LENGTH_SHORT);
                        Log.d(TAG, "onSucces: new BOOKING is created for " + userID);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(), "There was a problem while adding the booking.", Toast.LENGTH_SHORT);
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
            }
        });


        return view;


    }

}