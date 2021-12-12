package com.example.isstracker.model.issnow;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "iss_position")
public class IssPosition{
    @Id
    private UUID id;
    private String latitude;
    private String longitude;

    public IssPosition(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public IssPosition() {
        this.id = UUID.randomUUID();
    }

    public Double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public Double getLongitude() {
        return Double.parseDouble(longitude);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssPosition that = (IssPosition) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IssPosition{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
