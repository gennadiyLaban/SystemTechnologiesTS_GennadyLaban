package com.laban.systemtechnologies.currency.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import laban.ts.systemtechnologies.com.currency_model.model.entity.CurrencyItem;

public class CurrencyHolder {
    private static final String SHARED_PREFERENCE_KEY = "shared_currency_prefs";
    private static final String CURRENCY_COURSE_KEY = "currency_course_key";

    private SharedPreferences preferences;
    private Gson gson;

    public CurrencyHolder(SharedPreferences preferences) {
        this.preferences = preferences;
        this.gson = new Gson();
    }

    public void setCurrencyCourses(List<CurrencyItem> courses) {
        ConcurrentSkipListSet<String> jsonCourses = new ConcurrentSkipListSet<>();
        for (CurrencyItem item : courses) {
            jsonCourses.add(gson.toJson(item));
        }
        preferences.edit().putStringSet(CURRENCY_COURSE_KEY, jsonCourses).apply();
    }

    public List<CurrencyItem> getCurrencyCourses() {
        Set<String> jsonCourses = preferences.getStringSet(CURRENCY_COURSE_KEY, null);
        return jsonCourses == null ? Collections.emptyList() : fromJsonSet(jsonCourses);
    }

    private List<CurrencyItem> fromJsonSet(Set<String> jsonCourses) {
        List<CurrencyItem> items = new ArrayList<>();
        for (String json : jsonCourses) {
            items.add(gson.fromJson(json, CurrencyItem.class));
        }
        return items;
    }

    public static CurrencyHolder newInstance(Context context) {
        return new CurrencyHolder(context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE));
    }
}
