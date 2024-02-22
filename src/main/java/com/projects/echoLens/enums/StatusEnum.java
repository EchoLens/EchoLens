package com.projects.echoLens.enums;

public enum StatusEnum {
    SUCCESS(0),
    FAILURE(1);

    private final int code;
    StatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return String.valueOf(this.name());
    }

    public static StatusEnum fromValue(int value) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getCode() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }

    public static StatusEnum fromName(String name) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unexpected name: " + name);
    }



}
