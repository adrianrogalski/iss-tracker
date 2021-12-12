package com.example.isstracker.model.astros;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "people_in_space")
public class Astros {
    @Id
    private UUID id;
    private String message;
    private int number;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "people_info_id")
    @OrderColumn(name = "INDEX")
    private List<Person> people;

    public Astros(String message, Integer number, List<Person> name) {
        this.message = message;
        this.number = number;
        this.people = name;
    }

    public Astros() {
        this.id = UUID.randomUUID();
    }

    public String getMessage() {
        return message;
    }
    public Integer getNumber() {
        return number;
    }
    public List<Person> getPeople() {
        return List.copyOf(people);
    }
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astros astros = (Astros) o;
        return id.equals(astros.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
