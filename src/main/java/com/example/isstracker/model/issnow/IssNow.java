package com.example.isstracker.model.issnow;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "iss")
public class IssNow {
    @Id
    private UUID id;

    private String message;
    private long timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iss_position_id")
    private IssPosition iss_position;

    public IssNow() {
        this.id = UUID.randomUUID();
    }

    public IssNow(String message, long timestamp, IssPosition iss_position) {
        this.message = message;
        this.timestamp = timestamp;
        this.iss_position = iss_position;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public IssPosition getIss_position() {
        return iss_position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssNow issNow = (IssNow) o;
        return id.equals(issNow.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IssNow{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", iss_position=" + iss_position +
                '}';
    }
}
