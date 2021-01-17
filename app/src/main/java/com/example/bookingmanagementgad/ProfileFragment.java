package com.example.bookingmanagementgad;

import android.os.Bundle;
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

import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {

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
        TextView mFirstNameTextView = (TextView) view.findViewById(R.id.textViewDashboardFirstName);
        TextView mLastNameTextView = (TextView) view.findViewById(R.id.textViewDashboardLastName);
        TextView mEmailAddressTextView = (TextView) view.findViewById(R.id.textViewDashboardEmail);

        fetchUserData(documentReference, mFirstNameTextView, mLastNameTextView, mEmailAddressTextView);

        displayGraphicaElementsIfEmailNotVerified(fUser, mVerifyMessage, mVerifyButton);

        return view;
    }

    private void fetchUserData(DocumentReference documentReference, TextView mFirstNameTextView, TextView mLastNameTextView, TextView mEmailAddressTextView) {
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                mFirstNameTextView.setText(documentSnapshot.getString("fName"));
                mLastNameTextView.setText(documentSnapshot.getString("lName"));
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
