package com.laban.systemtechnologies.screens.currency.presentation.recyclerview;

public class MoveAction {
    public final int fromPosition;
    public final int toPosition;

    public MoveAction(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }
}
