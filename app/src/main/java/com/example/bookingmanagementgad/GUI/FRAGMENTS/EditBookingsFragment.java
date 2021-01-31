package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmanagementgad.GUI.ACTIVITIES.UpdateSingleBookingActivity;
import com.example.bookingmanagementgad.GUI.ADAPTERS.BookingAdapter;
import com.example.bookingmanagementgad.GUI.ADAPTERS.OnItemClickListener;
import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class EditBookingsFragment extends Fragment {

    private static final String TAG = "EditBookingsFragment";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private String userID = fAuth.getCurrentUser().getUid();


    private BookingAdapter bookingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_bookings, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);


        setUpRecycleView(recyclerView);

        return view;
    }

    private void setUpRecycleView(RecyclerView recyclerView) {
        Query query = db.collection("users").document(userID).collection("bookings").orderBy("checkInDate", Query.Direction.DESCENDING); //get bookings collection
        FirestoreRecyclerOptions<Booking> options = new FirestoreRecyclerOptions.Builder<Booking>()
                .setQuery(query, Booking.class)
                .build();
        bookingAdapter = new BookingAdapter(options);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookingAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            //our removed element can be swiped to LEFT or RIGHT
            @Override
            //this methods is used an element is moved with drag'n'drop
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //this method is used when an element is swiped
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                bookingAdapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);


        bookingAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                Booking booking = documentSnapshot.toObject(Booking.class);
                DocumentReference documentReference = documentSnapshot.getReference();

                Intent intent = new Intent(getActivity().getBaseContext(), UpdateSingleBookingActivity.class);
                intent.putExtra("documentID",documentReference.getId());
                getActivity().startActivity(intent);
                // documentSnapshot.getReference(); this is used to get the reference to the object . so you will need
                // here I can start another activity and pass the id of the document reference.
                // based on this document id reference I can update the document
            }
        });
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


}

