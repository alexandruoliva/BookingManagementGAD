package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bookingmanagementgad.GUI.ADAPTERS.BookingAdapter;
import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class EditBookingsFragment extends Fragment {

    private static final String TAG = "EditBookingsFragment";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userRef = db.collection("users");
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String userID = fAuth.getCurrentUser().getUid();


    private BookingAdapter bookingAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_bookings, container, false);


        setUpRecyclerView();

        return view;
    }

    private void setUpRecyclerView() {

            userRef.document(userID)
                    .collection("bookings").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            String data = "";
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Booking booking = documentSnapshot.toObject(Booking.class);

                                String firstName = booking.getFirstName();
                                String lastName = booking.getLastName();
                                String typeOfBooking = booking.getTypeOfBooking();

                                data += "first name: " + firstName + "last name: " + lastName + "type of booking: " + typeOfBooking;

                                Log.d(TAG, "onSucces:  booking" + data);


                            }
                        }
                    });

//        Query query = userRef.document(userID).getPath('bookings')

    }


}

