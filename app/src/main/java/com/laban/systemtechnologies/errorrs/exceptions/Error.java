package com.laban.systemtechnologies.errorrs.exceptions;

import io.reactivex.Single;
import io.reactivex.subjects.SingleSubject;

public abstract class Error extends Exception {
    private SingleSubject<Error> confirmSubject;

    public Error() {
        confirmSubject = SingleSubject.create();
    }

    public abstract String getDialogMessage();

    public Single<Error> getConfirmCallback() {
        return confirmSubject;
    }

    public void confirmError() {
        confirmSubject.onSuccess(this);
    }

}
