package com.travelwithme.Data;

public class Booking {
    private String bookingID;
    private String hotelType;
    private String tourPackage;
    private double foodBudget;
    private double numOfTravellers;
    private double tourCost;
    private double dayCount;
    private String vehicleType;
    private String accomadationType;
    private double totalCost;

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(String tourPackage) {
        this.tourPackage = tourPackage;
    }

    public double getFoodBudget() {
        return foodBudget;
    }

    public void setFoodBudget(double foodBudget) {
        this.foodBudget = foodBudget;
    }

    public double getNumOfTravellers() {
        return numOfTravellers;
    }

    public void setNumOfTravellers(double numOfTravellers) {
        this.numOfTravellers = numOfTravellers;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getDayCount() {
        return dayCount;
    }

    public void setDayCount(double dayCount) {
        this.dayCount = dayCount;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getAccomadationType() {
        return accomadationType;
    }

    public void setAccomadationType(String accomadationType) {
        this.accomadationType = accomadationType;
    }

    public double getTourCost() {
        return tourCost;
    }

    public void setTourCost(double tourCost) {
        this.tourCost = tourCost;
    }
}
