package com.example.bookingmanagementgad.GUI.ACTIVITIES;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingmanagementgad.MODELS.User;
import com.example.bookingmanagementgad.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class DashboardActivityCardView extends AppCompatActivity {

    private Button mLogOutButton;
    private TextView mTitleDashboard;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseUser fUser = fAuth.getCurrentUser();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private String userId = fAuth.getCurrentUser().getUid();
    private CardView mProfileCardView, mNewBookingCardView, mEditBookingsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard_card_view);

        mLogOutButton = findViewById(R.id.logoutButtonDashboard);
        mTitleDashboard = findViewById(R.id.userTitleTextViewDashboard);
        mProfileCardView = findViewById(R.id.profileCardView);
        mNewBookingCardView = findViewById(R.id.addNewBookingCardView);
        mEditBookingsCardView = findViewById(R.id.editBookingsCardView);

        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                User user = documentSnapshot.toObject(User.class);
                mTitleDashboard.setText("Welcome back " + user.getFirstName().toString() + " !");
            }
        });

        mLogOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mProfileCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        mNewBookingCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewBookingActivity.class);
                startActivity(intent);
            }
        });


        mEditBookingsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditBookingsActivity.class);
                startActivity(intent);
            }
        });




    }

}