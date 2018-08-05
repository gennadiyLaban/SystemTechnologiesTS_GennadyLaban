package com.laban.systemtechnologies.screens.currency;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.BaseActivity;
import com.laban.systemtechnologies.screens.Screen;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyListAdapter;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyView;
import com.laban.systemtechnologies.screens.currency.presentation.CurrencyViewModel;
import com.laban.systemtechnologies.screens.currency.presentation.recyclerview.CurrencyItemMover;
import com.laban.systemtechnologies.screens.currency.presentation.recyclerview.MoveAction;

import java.util.ArrayList;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class CurrencyListActivity extends BaseActivity<CurrencyViewModel, CurrencyView> implements CurrencyView {
    private CurrencyListAdapter currencyListAdapter;
    private CurrencyItemMover itemMover;
    private PublishSubject<Object> updateActionFlow = PublishSubject.create();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        initCurrencyList();

    }

    @SuppressLint("CheckResult")
    private void initCurrencyList() {
        RecyclerView recyclerView = findViewById(R.id.currency_course_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        currencyListAdapter = new CurrencyListAdapter(new ArrayList<>());
        recyclerView.setAdapter(currencyListAdapter);
        itemMover = new CurrencyItemMover();
        new ItemTouchHelper(itemMover).attachToRecyclerView(recyclerView);
        itemMover.getMoveActionFlow().observeOn(AndroidSchedulers.mainThread())
                .subscribe(moveAction -> currencyListAdapter.onItemMove(moveAction.fromPosition, moveAction.toPosition));
    }

    @Override
    protected void attachPresenter() {
        super.attachPresenter();
        disposable = getViewModel().getCurrencyFlow().observeOn(AndroidSchedulers.mainThread())
                .subscribe(currencyListAdapter::replaceData);
    }

    @Override
    protected void detachPresenter() {
        super.detachPresenter();
        disposable.dispose();
        updateActionFlow.onComplete();
        updateActionFlow = PublishSubject.create();
    }

    @Override
    protected Screen getScreen() {
        return Screen.CURRENCY_LIST;
    }

    @Override
    public Flowable<Object> updateDataFlow() {
        return updateActionFlow.toFlowable(BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<MoveAction> moveItemsFlow() {
        return itemMover.getMoveActionFlow();
    }

}
