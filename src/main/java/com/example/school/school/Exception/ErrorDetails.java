package com.example.school.school.Exception;


import java.util.Date;

public class ErrorDetails {

    private String message;
    private String description;
    private Date date;

    // Constructor
    public ErrorDetails(String message, String description, Date date) {
        this.message = message;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
