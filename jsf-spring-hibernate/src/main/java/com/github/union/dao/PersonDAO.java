package com.github.union.dao;

import com.github.union.model.Person;

import java.util.List;

public interface PersonDAO {
    void addPerson(Person person);

    void deletePerson(Person person);

    List<Person> listPersons();
}