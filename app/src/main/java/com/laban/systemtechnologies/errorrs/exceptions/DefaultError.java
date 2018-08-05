package com.laban.systemtechnologies.errorrs.exceptions;

public class DefaultError extends NetworkException {
    private String message;

    public DefaultError() {
        message = "Unknown error";
    }

    public DefaultError(String message) {
        this.message = message;
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
