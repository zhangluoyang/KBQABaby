package com.utils;

import java.util.List;

public class O {

    public static <T> boolean empty(List<T> list) {
        if (list == null) {
            return true;
        }
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean empty(String text) {
        if (text == null) {
            return true;
        }
        if (text.trim().length() == 0) {
            return true;
        }
        return false;
    }

}
