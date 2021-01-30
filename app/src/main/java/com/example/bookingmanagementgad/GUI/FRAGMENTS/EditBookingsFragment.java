package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmanagementgad.GUI.ADAPTERS.BookingAdapter;
import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);


        Query query = db.collection("users").document(userID).collection("bookings").orderBy("checkInDate", Query.Direction.DESCENDING); //get bookings collection
        FirestoreRecyclerOptions<Booking> options = new FirestoreRecyclerOptions.Builder<Booking>()
                .setQuery(query, Booking.class)
                .build();
        bookingAdapter = new BookingAdapter(options);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookingAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        bookingAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        bookingAdapter.stopListening();
    }

//    private void setUpRecyclerView() {
// This would be the solution before, when Google didn't have a compound query
// and this would imply to use the usual RecycleView
//
//        userRef.document(userID)
//                .collection("bookings").get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        String data = "";
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Booking booking = documentSnapshot.toObject(Booking.class);
//
//                            String firstName = booking.getFirstName();
//                            String lastName = booking.getLastName();
//                            String typeOfBooking = booking.getTypeOfBooking();
//
//                            data += "first name: " + firstName + "last name: " + lastName + "type of booking: " + typeOfBooking;
//
//                            Log.d(TAG, "onSucces:  booking" + data);
//
//
//                        }
//                    }
//                });
//    }


}

