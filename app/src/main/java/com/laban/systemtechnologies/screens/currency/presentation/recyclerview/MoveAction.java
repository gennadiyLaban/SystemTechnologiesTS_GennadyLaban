package com.laban.systemtechnologies.screens.currency.presentation.recyclerview;

public class MoveAction {
    public final int fromPosition;
    public final int toPositon;

    public MoveAction(int fromPosition, int toPositon) {
        this.fromPosition = fromPosition;
        this.toPositon = toPositon;
    }
}
