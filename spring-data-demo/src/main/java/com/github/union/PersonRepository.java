package com.github.union;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByName(String name);

    List<Person> findByNameLike(String nameLike);

    List<Person> findAllByCountry(String country);
}