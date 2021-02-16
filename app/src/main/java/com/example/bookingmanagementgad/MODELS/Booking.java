package com.example.bookingmanagementgad.MODELS;

import java.util.ArrayList;

public class Booking {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String typeOfBooking;
    private int pricePerNight;
    private int numberOfRooms;
    private String checkInDate;
    private String checkOutDate;
    private boolean room1;
    private boolean room2;
    private boolean room3;
    private boolean room4;
    private boolean room5;
    private boolean room6;
    private boolean roomUnderHouse;
    private boolean allRooms;

    public Booking() {
    }

    public Booking(String firstName,
                   String lastName,
                   String phoneNumber,
                   String typeOfBooking,
                   int pricePerNight,
                   int numberOfRooms,
                   String checkInDate,
                   String checkOutDate,
                   boolean room1,
                   boolean room2,
                   boolean room3,
                   boolean room4,
                   boolean room5,
                   boolean room6,
                   boolean roomUnderHouse,
                   boolean allRooms
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.typeOfBooking = typeOfBooking;
        this.pricePerNight = pricePerNight;
        this.numberOfRooms = numberOfRooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room1 = room1;
        this.room2 = room2;
        this.room3 = room3;
        this.room4 = room4;
        this.room5 = room5;
        this.room6 = room6;
        this.roomUnderHouse = roomUnderHouse;
        this.allRooms = allRooms;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTypeOfBooking() {
        return typeOfBooking;
    }

    public void setTypeOfBooking(String typeOfBooking) {
        this.typeOfBooking = typeOfBooking;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isRoom1() {
        return room1;
    }

    public void setRoom1(boolean room1) {
        this.room1 = room1;
    }

    public boolean isRoom2() {
        return room2;
    }

    public void setRoom2(boolean room2) {
        this.room2 = room2;
    }

    public boolean isRoom3() {
        return room3;
    }

    public void setRoom3(boolean room3) {
        this.room3 = room3;
    }

    public boolean isRoom4() {
        return room4;
    }

    public void setRoom4(boolean room4) {
        this.room4 = room4;
    }

    public boolean isRoom5() {
        return room5;
    }

    public void setRoom5(boolean room5) {
        this.room5 = room5;
    }

    public boolean isRoom6() {
        return room6;
    }

    public void setRoom6(boolean room6) {
        this.room6 = room6;
    }

    public boolean isRoomUnderHouse() {
        return roomUnderHouse;
    }

    public void setRoomUnderHouse(boolean roomUnderHouse) {
        this.roomUnderHouse = roomUnderHouse;
    }

    public boolean isAllRooms() {
        return allRooms;
    }

    public void setAllRooms(boolean allRooms) {
        this.allRooms = allRooms;
    }
}
