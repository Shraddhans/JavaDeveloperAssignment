package com.assignment.shraddha.entity;

import com.assignment.shraddha.exception.InvalidOccupationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Occupation {
    DEVELOPER("developer"),
    CHEF("chef"),
    PLUMBER("plumber"),
    CARPENTER("carpenter"),
    OTHER("other");
    private  String value;

    Occupation(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Occupation fromValue(String value) {
        for (Occupation occupation : Occupation.values()) {
            if (occupation.value.equalsIgnoreCase(value)) {
                return occupation;
            }
        }
        throw new InvalidOccupationException("Invalid occupation");
    }

}
