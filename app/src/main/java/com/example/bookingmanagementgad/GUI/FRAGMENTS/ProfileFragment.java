package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookingmanagementgad.MODELS.User;
import com.example.bookingmanagementgad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private TextView mVerifyMessage, mEmailAddressTextView;
    private Button mVerifyButton, mUpdateUser;
    private EditText mFirstNameTextEdit, mLastNameTextEdit;
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseUser fUser = fAuth.getCurrentUser();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private String userId = fAuth.getCurrentUser().getUid();
    CollectionReference userRef = fStore.collection("users");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DocumentReference documentReference = fStore.collection("users").document(userId);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mVerifyMessage = view.findViewById(R.id.emailNotVerifiedTextView);
        mVerifyButton = view.findViewById(R.id.verifyEmailButton);
        mUpdateUser = view.findViewById(R.id.buttonUpdateProfileFragment);
        mFirstNameTextEdit = view.findViewById(R.id.editTextDashboardFirstName);
        mLastNameTextEdit = view.findViewById(R.id.editTextDashboardLastName);
        mEmailAddressTextView = view.findViewById(R.id.textViewDashboardEmail);

        fetchUserData(documentReference, mFirstNameTextEdit, mLastNameTextEdit, mEmailAddressTextView);
        updateUserProfile(fAuth, documentReference, mUpdateUser, mFirstNameTextEdit, mLastNameTextEdit, mEmailAddressTextView);
        displayGraphicalElementsIfEmailNotVerified(fUser, mVerifyMessage, mVerifyButton);

        return view;
    }

    private void updateUserProfile(FirebaseAuth fAuth, DocumentReference documentReference, Button mUpdateUser, EditText mFirstNameTextEdit, EditText mLastNameTextEdit, TextView mEmailAddressTextView) {
        mUpdateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userID = fAuth.getCurrentUser().getUid();
                String firstName = mFirstNameTextEdit.getText().toString();
                String lastName = mLastNameTextEdit.getText().toString();
                String email = mEmailAddressTextView.getText().toString();

                User user = new User(firstName, lastName, email);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSucces: User profile is updated for " + userID);
                        Toast.makeText(getActivity(), "Profile has been updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                        Toast.makeText(getActivity(), "Fail! The profile has not been updated successfully.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void fetchUserData(DocumentReference documentReference, EditText mFirstNameTextEdit, EditText mLastNameTextEdit, TextView mEmailAddressTextView) {
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                User user = documentSnapshot.toObject(User.class);

                mFirstNameTextEdit.setText(user.getFirstName());
                mLastNameTextEdit.setText(user.getLastName());
                mEmailAddressTextView.setText(user.getEmail() );
            }
        });
    }

    private void displayGraphicalElementsIfEmailNotVerified(FirebaseUser fUser, TextView mVerifyMessage, Button mVerifyButton) {
        if (!fUser.isEmailVerified()) {
            mVerifyMessage.setVisibility(View.VISIBLE);
            mVerifyButton.setVisibility(View.VISIBLE);

            mVerifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Verification email has been sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                              Toast.makeText(getActivity(), "onFailure: Email not sent " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }


}
