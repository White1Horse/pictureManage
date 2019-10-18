package com.azxx.picture.utils;

import java.util.UUID;

/**
 * @author hetao
 * @create 2019-10-18 12:45
 */
public class IDGenerator {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
