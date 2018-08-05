package com.laban.systemtechnologies.errorrs.exceptions;

public class ServerError extends NetworkException {
    private String message;
    private int errorCode;

    public ServerError(int errorCode) {
        this.errorCode = errorCode;
        this.message = "Server Error code " + errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getDialogMessage() {
        return message;
    }
}
