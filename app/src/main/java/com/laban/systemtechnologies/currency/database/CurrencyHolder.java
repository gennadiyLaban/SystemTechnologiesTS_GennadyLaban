package com.laban.systemtechnologies.currency.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.laban.systemtechnologies.model.entity.CurrencyItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CurrencyHolder {
    private static final String SHARED_PREFERENCE_KEY = "shared_currency_prefs";
    private static final String CURRENCY_COURSE_KEY = "currency_course_key";

    private SharedPreferences preferences;
    private Gson gson;

    public CurrencyHolder(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setCurrencyCourses(List<CurrencyItem> courses) {
        HashSet<String> jsonCourses = new HashSet<>();
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
