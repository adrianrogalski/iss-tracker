package com.example.isstracker.model.astros;

import java.util.Objects;

public class People {
    private String name;
    private String craft;

    public People(String name, String craft) {
        this.name = name;
        this.craft = craft;
    }

    public People() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return name.equals(people.name) && craft.equals(people.craft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, craft);
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", craft='" + craft + '\'' +
                '}';
    }
}
