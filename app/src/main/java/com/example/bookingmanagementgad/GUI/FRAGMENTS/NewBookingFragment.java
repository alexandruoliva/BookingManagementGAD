package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookingmanagementgad.MODELS.Booking;
import com.example.bookingmanagementgad.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class NewBookingFragment extends Fragment {

    private static final String TAG = "NewBookingFragment";
    private Button mButtonCreateNewBooking;
    private EditText mEditFirstName, mEditLastName, mEditPhoneNumber;
    private EditText mEditPricePerNightBooking;
    private EditText mEditTextCheckOutDateBooking;
    private EditText mEditTextCheckInDateBooking;
    private EditText mEditTypeOfBooking;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private CheckBox mCheckBoxRoom1,mCheckBoxRoom2,mCheckBoxRoom3;
    private CheckBox mCheckBoxRoom4,mCheckBoxRoom5,mCheckBoxRoom6;
    private CheckBox mCheckBoxRoomUnderHouse,mCheckBoxRoomAll;

    private boolean room1;
    private boolean room2;
    private boolean room3;
    private boolean room4;
    private boolean room5;
    private boolean room6;
    private int numberOfRooms = 0;
    private boolean roomUnderHouse;
    private boolean allRooms;

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
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
        mEditTextCheckInDateBooking = view.findViewById(R.id.editTextCheckInDateBooking);
        mEditTextCheckOutDateBooking = view.findViewById(R.id.editTextCheckOutDateBooking);
        mEditTypeOfBooking = view.findViewById(R.id.editTextTypeOfBookingBooking);

        mCheckBoxRoom1 = view.findViewById(R.id.room1CheckBox);
        checkBoxes.add(mCheckBoxRoom1);
        mCheckBoxRoom2 = view.findViewById(R.id.room2CheckBox);
        checkBoxes.add(mCheckBoxRoom2);
        mCheckBoxRoom3 = view.findViewById(R.id.room3CheckBox);
        checkBoxes.add(mCheckBoxRoom3);
        mCheckBoxRoom4 = view.findViewById(R.id.room4CheckBox);
        checkBoxes.add(mCheckBoxRoom4);
        mCheckBoxRoom5 = view.findViewById(R.id.room5CheckBox);
        checkBoxes.add(mCheckBoxRoom5);
        mCheckBoxRoom6 = view.findViewById(R.id.room6CheckBox);
        checkBoxes.add(mCheckBoxRoom6);
        mCheckBoxRoomUnderHouse = view.findViewById(R.id.roomUnderHouseCheckBox);
        checkBoxes.add(mCheckBoxRoomUnderHouse);
        mCheckBoxRoomAll = view.findViewById(R.id.roomAllCheckBox);

        disableOtherCheckboxes();
        createNewBooking();
        return view;

    }

    private void disableOtherCheckboxes(){
        mCheckBoxRoomAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (mCheckBoxRoomAll.isChecked()){
                    for(CheckBox checkBox : checkBoxes)
                    {
                        checkBox.setEnabled(false);
                        if(checkBox.isChecked()){
                            checkBox.toggle();
                            checkBox.setChecked(false);

                        }
                    }
                }
                if (!mCheckBoxRoomAll.isChecked()){
                    for(CheckBox checkBox : checkBoxes)
                    {
                        checkBox.setEnabled(true);
                    }
                }
            }
        });
    }


    private void createNewBooking() {
        mButtonCreateNewBooking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("bookings").document(userID);

                String firstName = mEditFirstName.getText().toString().trim();
                String lastName = mEditLastName.getText().toString().trim();

                if (TextUtils.isEmpty(firstName)) {
                    mEditFirstName.setError("First name is required!!");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    mEditLastName.setError("Last name is required!");
                    return;
                }

                if(!mCheckBoxRoom1.isChecked() &&
                   !mCheckBoxRoom2.isChecked() &&
                   !mCheckBoxRoom3.isChecked() &&
                   !mCheckBoxRoom4.isChecked() &&
                   !mCheckBoxRoom5.isChecked() &&
                   !mCheckBoxRoom6.isChecked() &&
                   !mCheckBoxRoomUnderHouse.isChecked() &&
                   !mCheckBoxRoomAll.isChecked()){
                    Toast.makeText(getActivity(), "At least one room should be selected!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(mCheckBoxRoom1.isChecked() && mCheckBoxRoom1.isEnabled()){
                    room1 = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoom2.isChecked() && mCheckBoxRoom2.isEnabled()){
                    room2 = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoom3.isChecked() && mCheckBoxRoom3.isEnabled()){
                    room3 = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoom4.isChecked() && mCheckBoxRoom4.isEnabled()){
                    room4 = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoom5.isChecked() && mCheckBoxRoom5.isEnabled()){
                    room5 = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoom6.isChecked() && mCheckBoxRoom6.isEnabled()){
                    room6 = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoomUnderHouse.isChecked() && mCheckBoxRoomUnderHouse.isEnabled()){
                    roomUnderHouse = true;
                    numberOfRooms++;
                }
                if(mCheckBoxRoomAll.isChecked() && mCheckBoxRoomAll.isEnabled()){
                    allRooms = true;
                    numberOfRooms=8;
                }


                Booking booking = new Booking(mEditFirstName.getText().toString(),
                        mEditLastName.getText().toString(),
                        mEditPhoneNumber.getText().toString(),
                        mEditTypeOfBooking.getText().toString(),
                        Integer.parseInt(mEditPricePerNightBooking.getText().toString()),
                        numberOfRooms,
                        mEditTextCheckInDateBooking.getText().toString(),
                        mEditTextCheckOutDateBooking.getText().toString(),
                        room1,
                        room2,
                        room3,
                        room4,
                        room5,
                        room6,
                        roomUnderHouse,
                        allRooms);

//                Booking booking = new Booking("Alexandru", "Oliva", "Airbnb", "0742096316", 234, 2, 233, 245);

                CollectionReference userRef = fStore.collection("users");
                userRef.document(userID).collection("bookings").add(booking);
                room1=false;
                room2=false;
                room3=false;
                room4=false;
                room5=false;
                room6=false;
                roomUnderHouse=false;
                allRooms=false;
                numberOfRooms=0;
                Toast.makeText(getActivity(), "New booking has been created successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}