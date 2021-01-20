package com.example.bookingmanagementgad;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseUser fUser = fAuth.getCurrentUser();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        String userId;
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView mVerifyMessage = (TextView) view.findViewById(R.id.emailNotVerifiedTextView);
        Button mVerifyButton = (Button) view.findViewById(R.id.verifyEmailButton);
        Button mUpdateUser = (Button) view.findViewById(R.id.buttonUpdateProfileFragment);
        EditText mFirstNameTextEdit = (EditText) view.findViewById(R.id.editTextDashboardFirstName);
        EditText mLastNameTextEdit = (EditText) view.findViewById(R.id.editTextDashboardLastName);
        TextView mEmailAddressTextView = (TextView) view.findViewById(R.id.textViewDashboardEmail);

        fetchUserData(documentReference, mFirstNameTextEdit, mLastNameTextEdit, mEmailAddressTextView);
        updateUserProfile(fAuth, documentReference, mUpdateUser, mFirstNameTextEdit, mLastNameTextEdit, mEmailAddressTextView);
        displayGraphicaElementsIfEmailNotVerified(fUser, mVerifyMessage, mVerifyButton);

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

                Map<String, Object> user = new HashMap<>();
                user.put("fName", firstName);
                user.put("lName", lastName);
                user.put("email", email);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSucces: user Profile is updated for " + userID);
                        Toast.makeText(v.getContext(), "Profile has been updated successfully.", Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                        Toast.makeText(v.getContext(), "Fail! The profile has not been updated successfully.", Toast.LENGTH_SHORT);
                    }
                });

            }
        });
    }

    private void fetchUserData(DocumentReference documentReference, EditText mFirstNameTextEdit, EditText mLastNameTextEdit, TextView mEmailAddressTextView) {
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mFirstNameTextEdit.setText(documentSnapshot.getString("fName"));
                mLastNameTextEdit.setText(documentSnapshot.getString("lName"));
                mEmailAddressTextView.setText(documentSnapshot.getString("email"));
            }
        });
    }

    private void displayGraphicaElementsIfEmailNotVerified(FirebaseUser fUser, TextView mVerifyMessage, Button mVerifyButton) {
        if (!fUser.isEmailVerified()) {
            mVerifyMessage.setVisibility(View.VISIBLE);
            mVerifyButton.setVisibility(View.VISIBLE);

            mVerifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification email has been sent.", Toast.LENGTH_SHORT);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(), "onFailure: Email not sent " + e.getMessage(), Toast.LENGTH_SHORT);
                        }
                    });
                }
            });
        }
    }


}
