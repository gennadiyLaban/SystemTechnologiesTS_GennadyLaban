package com.laban.systemtechnologies.screens.currency.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.screens.currency.presentation.recyclerview.ItemMoveListener;

import java.util.Collections;
import java.util.List;

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.CurrencyHolder> implements ItemMoveListener {
    private List<CurrencyViewController> controllers;

    public CurrencyListAdapter(List<CurrencyViewController> controllers) {
        this.controllers = controllers;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(controllers, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(controllers, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }


    public class CurrencyHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView scale;
        public TextView scaleCharCode;
        public TextView rate;
        public TextView rateCharCode;

        public CurrencyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            scale = itemView.findViewById(R.id.scale);
            scaleCharCode = itemView.findViewById(R.id.scale_char_code);
            rate = itemView.findViewById(R.id.rate);
            rateCharCode = itemView.findViewById(R.id.rate_char_code);
        }
    }

    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int position) {
        controllers.get(position).bind(holder);
    }

    @Override
    public int getItemCount() {
        return controllers.size();
    }

    public void replaceData(List<CurrencyViewController> controllers) {
        clear();
        this.controllers.addAll(controllers);
        this.notifyItemRangeInserted(0, this.controllers.size());
    }

    public void clear() {
        int size = controllers.size();
        controllers.clear();
        notifyItemRangeRemoved(0, size);
    }

}
