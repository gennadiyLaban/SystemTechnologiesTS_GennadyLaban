package com.laban.systemtechnologies.errorrs;

import io.reactivex.Single;
import io.reactivex.subjects.CompletableSubject;

public abstract class Error extends Exception {
    private CompletableSubject confirmSubject;

    public Error() {
        confirmSubject = CompletableSubject.create();
    }

    public abstract String getDialogMessage();

    public Single<Error> getConfirmCallback() {
        return confirmSubject.toSingle(() -> this);
    }

    public void confirmError() {
        confirmSubject.onComplete();
    }

}
