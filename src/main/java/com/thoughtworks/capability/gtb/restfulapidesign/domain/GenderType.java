package com.thoughtworks.capability.gtb.restfulapidesign.domain;

public enum  GenderType {
    MALE("男"),
    FEMALE("女");

    private String gender;

    GenderType(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
}
