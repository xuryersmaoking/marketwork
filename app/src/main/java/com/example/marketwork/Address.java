package com.example.marketwork;

public class Address {
    private int id;
    private int userId;
    private String details;

    public Address(int id, int userId, String details) {
        this.id = id;
        this.userId = userId;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDetails() {
        return details;
    }
}



