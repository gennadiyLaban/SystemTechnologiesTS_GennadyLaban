package com.laban.systemtechnologies.screens.currency.presentation.recyclerview;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class CurrencyItemMover extends ItemTouchHelper.Callback {
    private PublishSubject<MoveAction> moveActionFlow;

    @SuppressLint("CheckResult")
    public CurrencyItemMover() {
        moveActionFlow = PublishSubject.create();
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        moveActionFlow.onNext(new MoveAction(viewHolder.getAdapterPosition(), target.getAdapterPosition()));
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public Flowable<MoveAction> getMoveActionFlow() {
        return moveActionFlow.toFlowable(BackpressureStrategy.LATEST);
    }

}
