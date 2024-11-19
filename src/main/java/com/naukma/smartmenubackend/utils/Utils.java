package com.naukma.smartmenubackend.utils;

import java.util.List;

public class Utils {
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
