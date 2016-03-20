package com.github.union.service;

import com.github.union.model.Person;

import java.util.List;

public interface PersonService {
    void addPerson(Person person);

    void deletePerson(Person person);

    List<Person> listPersons();
}
