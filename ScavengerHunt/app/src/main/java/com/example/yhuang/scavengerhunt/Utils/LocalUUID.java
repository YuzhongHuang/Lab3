package com.example.yhuang.scavengerhunt.Utils;

import java.util.UUID;

public class LocalUUID {
    public static String getUUID(String filename) {
        UUID uuid = UUID.fromString(filename);
        return uuid.randomUUID().toString();
    }
}
