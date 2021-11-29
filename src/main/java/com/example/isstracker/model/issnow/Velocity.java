package com.example.isstracker.model.issnow;

import com.example.isstracker.util.TimestampConverter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static com.example.isstracker.util.TimestampConverter.convert;

@Entity
@Table(name = "velocities")
public class Velocity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private double value;
    private long timestamp;

    public Velocity(double value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
    public Velocity() {
    }

    public double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDateTimeTimestamp() {
        return convert(timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return Double.compare(velocity.value, value) == 0 && timestamp == velocity.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, timestamp);
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
