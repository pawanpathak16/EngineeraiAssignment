package com.assignment.engineeraiassignment.model;

public class UserApiResult
{
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    private boolean status;
    private String message;
    private UserData data;

}
