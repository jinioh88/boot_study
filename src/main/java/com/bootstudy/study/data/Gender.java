package com.bootstudy.study.data;

public enum Gender {
    MALE("M"), FEMALE("F");
    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}
