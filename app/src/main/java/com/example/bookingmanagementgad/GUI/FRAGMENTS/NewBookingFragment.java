package com.example.bookingmanagementgad.GUI.FRAGMENTS;

import android.os.Bundle;
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
    private EditText mEditPricePerNightBooking, mEditTextNumberOfRoomsBooking;
    private EditText mEditTextCheckInDateBooking, mEditTextCheckOutDateBooking;
    private EditText mEditTypeOfBooking;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private CheckBox mCheckBoxRoom1,mCheckBoxRoom2,mCheckBoxRoom3;
    private CheckBox mCheckBoxRoom4,mCheckBoxRoom5,mCheckBoxRoom6;
    private CheckBox mCheckBoxRoomUnderHouse,mCheckBoxRoomAll;

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
        mEditTextNumberOfRoomsBooking = view.findViewById(R.id.editTextNumberOfRoomsBooking);
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
                ArrayList<String> roomsArray = new ArrayList<>();
                if(mCheckBoxRoom1.isChecked()){
                    roomsArray.add("Camera 1");
                }
                if(mCheckBoxRoom2.isChecked()){
                    roomsArray.add("Camera 2");
                }
                if(mCheckBoxRoom3.isChecked()){
                    roomsArray.add("Camera 3");
                }
                if(mCheckBoxRoom4.isChecked()){
                    roomsArray.add("Camera 4");
                }
                if(mCheckBoxRoom5.isChecked()){
                    roomsArray.add("Camera 5");
                }
                if(mCheckBoxRoom6.isChecked()){
                    roomsArray.add("Camera 6");
                }
                if(mCheckBoxRoomUnderHouse.isChecked()){
                    roomsArray.add("Sub casa");
                }
                if(mCheckBoxRoomAll.isChecked()){
                    roomsArray.add("Toata pensiunea");
                }


                Booking booking = new Booking(mEditFirstName.getText().toString(),
                        mEditLastName.getText().toString(),
                        mEditPhoneNumber.getText().toString(),
                        mEditTypeOfBooking.getText().toString(),
                        Integer.parseInt(mEditPricePerNightBooking.getText().toString()),
                        Integer.parseInt(mEditTextNumberOfRoomsBooking.getText().toString()),
                        mEditTextCheckInDateBooking.getText().toString(),
                        mEditTextCheckOutDateBooking.getText().toString(),
                        roomsArray);

//                Booking booking = new Booking("Alexandru", "Oliva", "Airbnb", "0742096316", 234, 2, 233, 245);

                CollectionReference userRef = fStore.collection("users");
                userRef.document(userID).collection("bookings").add(booking);
                Toast.makeText(getActivity(), "New booking has been created successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}