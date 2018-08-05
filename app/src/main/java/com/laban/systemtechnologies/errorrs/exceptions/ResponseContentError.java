package com.laban.systemtechnologies.errorrs.exceptions;

public class ResponseContentError extends NetworkException {
    private String message;

    public ResponseContentError() {
        message = "Invalid response";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getDialogMessage() {
        return message;
    }
}
