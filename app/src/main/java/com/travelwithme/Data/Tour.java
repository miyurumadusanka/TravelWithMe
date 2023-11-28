package com.travelwithme.Data;

public class Tour {
    private String tourID;
    private String hotelType;
    private String tourPackage;
    private double foodBudget;
    private double numOfTravellers;
    private double totalCost;

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
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

    public String getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(String tourPackage) {
        this.tourPackage = tourPackage;
    }
}
