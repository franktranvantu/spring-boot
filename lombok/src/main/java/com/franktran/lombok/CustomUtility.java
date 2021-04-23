package com.franktran.lombok;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomUtility {

    public String toUpperCase(String value) {
        return value.toUpperCase();
    }
}
