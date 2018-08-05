package com.laban.systemtechnologies.errorrs;

import android.annotation.SuppressLint;

import com.laban.systemtechnologies.errorrs.exceptions.Error;

import java.util.LinkedList;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class ErrorRepository {
    private LinkedList<Error> errors;
    private PublishSubject<Error> errorFlow;

    public ErrorRepository() {
        errors = new LinkedList<>();
        errorFlow = PublishSubject.create();
    }

    @SuppressLint("CheckResult")
    public void addError(Error error) {
        errors.add(error);
        error.getConfirmCallback().subscribe(errors::remove);
        errorFlow.onNext(error);
    }

    public Flowable<Error> getErrorFlow() {
        return Flowable.concat(Flowable.fromIterable(errors), errorFlow.toFlowable(BackpressureStrategy.BUFFER));
    }

}
