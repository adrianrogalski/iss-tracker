package com.example.isstracker.service.people;

import com.example.isstracker.model.astros.Person;

import java.util.List;

public interface PeopleInSpaceService {
    int numberOfPeopleInSpace();
    List<Person> peopleInSpace();
    List<Person> peopleOnIss();
}
