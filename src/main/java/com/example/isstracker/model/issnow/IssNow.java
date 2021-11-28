package com.example.isstracker.model.issnow;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "iss")
public class IssNow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String message;
    private long timestamp;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iss_position_id")
    private IssPosition iss_position;
    private double iss_velocity;

    public IssNow(String message, long timestamp, IssPosition iss_position, double iss_velocity) {
        this.message = message;
        this.timestamp = timestamp;
        this.iss_position = iss_position;
        this.iss_velocity = iss_velocity;
    }

    public IssNow() {
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
    public UUID getId() {
        return id;
    }
    public double getIss_velocity() {
        return iss_velocity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssNow issNow = (IssNow) o;
        return timestamp == issNow.timestamp && Double.compare(issNow.iss_velocity, iss_velocity) == 0 && id.equals(issNow.id) && Objects.equals(message, issNow.message) && Objects.equals(iss_position, issNow.iss_position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, timestamp, iss_position, iss_velocity);
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
