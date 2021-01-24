package com.example.bookingmanagementgad.models;

public class Booking {

    private String firstName;
    private String lastName;
    private String phoneNumer;
    private String email;
    private String typeOfBooking;
    private int pricePerNight;
    private int numberOfRooms;
    private long checkInDate;
    private long checkOutDate;

    public Booking() {
    }

    public Booking(String firstName,
                   String lastName,
                   String phoneNumer,
                   String email,
                   String typeOfBooking,
                   int pricePerNight,
                   int numberOfRooms,
                   long checkInDate,
                   long checkOutDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumer = phoneNumer;
        this.email = email;
        this.typeOfBooking = typeOfBooking;
        this.pricePerNight = pricePerNight;
        this.numberOfRooms = numberOfRooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
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

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
