package com.example.bookingmanagementgad.GUI.ADAPTERS;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OnItemClickListener {

    void onItemClick(DocumentSnapshot documentSnapshot, int position);
}
