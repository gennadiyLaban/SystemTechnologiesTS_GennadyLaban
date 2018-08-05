package com.laban.systemtechnologies.errorrs.exceptions;

import io.reactivex.Single;
import io.reactivex.subjects.SingleSubject;

public abstract class Error extends Exception {
    private SingleSubject<Error> confirmSubject;
    private SingleSubject<Error> showingSubject;

    public Error() {
        confirmSubject = SingleSubject.create();
        showingSubject = SingleSubject.create();
    }

    public abstract String getDialogMessage();

    public Single<Error> getConfirmCallback() {
        return confirmSubject;
    }

    public Single<Error> getShowingCallback() {
        return showingSubject;
    }

    public void confirmError() {
        confirmSubject.onSuccess(this);
    }

    public void showError() {
        showingSubject.onSuccess(this);
    }

}
