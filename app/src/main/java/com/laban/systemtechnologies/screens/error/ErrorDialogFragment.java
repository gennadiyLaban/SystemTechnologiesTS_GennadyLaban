package com.laban.systemtechnologies.screens.error;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.errorrs.exceptions.Error;

import io.reactivex.Single;
import io.reactivex.subjects.CompletableSubject;

public class ErrorDialogFragment extends AppCompatDialogFragment {
    private Error error;
    private CompletableSubject confirmFlow;

    @Override
    public void onResume() {
        super.onResume();
        error.showError();
    }

    public ErrorDialogFragment() {
        confirmFlow = CompletableSubject.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Single<Error> getConfirmErrorFlow() {
        return confirmFlow.toSingle(() -> error);
    }

    public static ErrorDialogFragment newInstance(Error error) {
        ErrorDialogFragment fragment = new ErrorDialogFragment();
        fragment.error = error;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCancelable(false);
        View root = inflater.inflate(R.layout.fragment_error_dialog, container, false);
        ((TextView) root.findViewById(R.id.message)).setText(error.getDialogMessage());
        root.findViewById(R.id.confirm_btn).setOnClickListener(v -> {
            error.confirmError();
            confirmFlow.onComplete();
            dismiss();
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
