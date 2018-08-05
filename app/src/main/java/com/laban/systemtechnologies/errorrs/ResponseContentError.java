package com.laban.systemtechnologies.errorrs;

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
