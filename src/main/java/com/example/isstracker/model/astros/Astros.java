package com.example.isstracker.model.astros;

import java.util.List;
import java.util.Objects;

public class Astros {
    private String message;
    private Integer number;
    private List<People> people;

    public Astros(String message, Integer number, List<People> name) {
        this.message = message;
        this.number = number;
        this.people = name;
    }

    public Astros() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astros astros = (Astros) o;
        return message.equals(astros.message) && number.equals(astros.number) && people.equals(astros.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, number, people);
    }

    @Override
    public String toString() {
        return "Astros{" +
                "message='" + message + '\'' +
                ", number=" + number +
                ", name=" + people +
                '}';
    }
}
