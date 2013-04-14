package com.fdm.SpringJMSProject;


import java.io.Serializable;

public class SimpleObject implements Serializable{

    private String message;

    private int favouriteNumber;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    public void setFavouriteNumber(int favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
    }
}
