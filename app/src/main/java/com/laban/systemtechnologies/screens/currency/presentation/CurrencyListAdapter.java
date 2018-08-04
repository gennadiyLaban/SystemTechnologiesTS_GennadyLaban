package com.laban.systemtechnologies.screens.currency.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.CurrencyHolder> {

    public class CurrencyHolder extends RecyclerView.ViewHolder {

        public CurrencyHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
