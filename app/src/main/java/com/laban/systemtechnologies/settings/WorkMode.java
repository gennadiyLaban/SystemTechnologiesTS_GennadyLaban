package com.laban.systemtechnologies.settings;

public enum WorkMode {
    NORMAL_WORK(1), NOT_FOUND_EXCEPTION(2), CONTENT_EXCEPTION(3);

    private int code;

    WorkMode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static WorkMode fromCode(int code) {
        for (WorkMode mode : WorkMode.values()) {
            if (mode.code == code) {
                return mode;
            }
        }
        return WorkMode.NORMAL_WORK;
    }
}
