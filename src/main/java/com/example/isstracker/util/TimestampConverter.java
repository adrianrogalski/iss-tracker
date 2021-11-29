package com.example.isstracker.util;

import java.time.Instant;
import java.time.ZoneId;

public abstract class TimestampConverter {
    public static String convert(long unixtimestamp) {
        return Instant.ofEpochSecond(unixtimestamp).atZone(ZoneId.systemDefault()).toString();
    }

}
