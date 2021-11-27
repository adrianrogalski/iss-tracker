package com.example.isstracker.model.issnow;

import java.util.Objects;

public class IssNow {
    private String message;
    private long timestamp;
    private IssPosition iss_position;

    public IssNow(String message, long timestamp, IssPosition iss_position) {
        this.message = message;
        this.timestamp = timestamp;
        this.iss_position = iss_position;
    }

    public IssNow() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public IssPosition getIss_position() {
        return iss_position;
    }

    public void setIss_position(IssPosition iss_position) {
        this.iss_position = iss_position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssNow issNow = (IssNow) o;
        return timestamp == issNow.timestamp && message.equals(issNow.message) && iss_position.equals(issNow.iss_position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, timestamp, iss_position);
    }

    @Override
    public String toString() {
        return "IssNow{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", iss_position=" + iss_position +
                '}';
    }
}
