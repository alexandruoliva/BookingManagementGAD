package com.example.bookingmanagementgad.MODELS;

public class Booking {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String typeOfBooking;
    private int pricePerNight;
    private int numberOfRooms;
    private String checkInDate;
    private String checkOutDate;

    public Booking() {
    }

    public Booking(String firstName,
                   String lastName,
                   String phoneNumber,
                   String typeOfBooking,
                   int pricePerNight,
                   int numberOfRooms,
                   String checkInDate,
                   String checkOutDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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
}
