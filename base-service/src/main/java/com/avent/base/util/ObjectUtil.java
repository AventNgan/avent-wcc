package com.avent.base.util;

public class ObjectUtil {

    public static boolean isAnyNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

}
