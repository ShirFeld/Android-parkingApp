package com.nameofproject.parkms2;

public class Place {

    private String parkNumber;
    private boolean trueorfalse;
    public Place(){

    }

    public Place(String carNumber, boolean trueorfalse) {
        this.parkNumber = carNumber;
        this.trueorfalse = trueorfalse;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber;
    }

    public boolean isTrueorfalse() {
        return trueorfalse;
    }

    public void setTrueorfalse(boolean trueorfalse) {
        this.trueorfalse = trueorfalse;
    }
}
