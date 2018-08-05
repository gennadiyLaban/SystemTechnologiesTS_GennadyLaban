package com.laban.systemtechnologies.errorrs.exceptions;

public class NetworkConnectionException extends NetworkException {
    private String message;

    public NetworkConnectionException() {
        message = "Network connection fail";
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getDialogMessage() {
        return message;
    }
}
