package com.example.isstracker.model.astros;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "people_info")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String craft;

    public Person(String name, String craft) {
        this.name = name;
        this.craft = craft;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id.equals(person.id) && Objects.equals(name, person.name) && Objects.equals(craft, person.craft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, craft);
    }
}
