package com.example.isstracker.model.astros;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "people_in_space")
public class Astros {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
        if (!(o instanceof Astros)) return false;
        Astros astros = (Astros) o;
        return number == astros.number && id.equals(astros.id) && Objects.equals(message, astros.message) && Objects.equals(people, astros.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, number, people);
    }
}
