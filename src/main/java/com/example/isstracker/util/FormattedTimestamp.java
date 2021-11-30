package com.example.isstracker.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class FormattedTimestamp {
    private ZonedDateTime zonedDateTime;
    private FormattedTimestamp(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public static Optional<FormattedTimestamp> of(long unixtimestamp) {
        if (unixtimestamp < 0) {
            System.err.println("wrong unix timestamp");
            return Optional.empty();
        }
        ZonedDateTime zonedDateTime = Instant.ofEpochSecond(unixtimestamp).atZone(ZoneId.systemDefault());
        return Optional.of(new FormattedTimestamp(zonedDateTime));
    }

    @Override
    public String toString() {
        return zonedDateTime.toString();
    }
}
