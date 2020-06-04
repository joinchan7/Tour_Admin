package com.chan.ssm.utils;

import java.util.UUID;

public class IdUtils {
    public static String randomId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
