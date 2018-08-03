package com.laban.systemtechnologies.screens;


public enum Screen {
    MAIN(1), CURRENCY_LIST(2), SETTINGS(3);

    private int id;

    Screen(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
