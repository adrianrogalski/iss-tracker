package com.example.isstracker.model.issnow;

import com.example.isstracker.util.FormattedTimestamp;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "velocities")
public class Velocity {
    @Id
    private UUID id;
    private double value;
    private long timestamp;
    private long deltaTime;

    public Velocity(double value, long timestamp, long deltaTime) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.timestamp = timestamp;
        this.deltaTime = deltaTime;
    }

    public Velocity() {

    }

    public UUID getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        return FormattedTimestamp.of(timestamp).get().toString();
    }

    public long getDeltaTime() {
        return deltaTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return Double.compare(velocity.value, value) == 0 && timestamp == velocity.timestamp && deltaTime == velocity.deltaTime && id.equals(velocity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, timestamp, deltaTime);
    }

}
