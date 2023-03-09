package com.toyproject.util;

public class StringUtil {
    public static boolean isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }
}
