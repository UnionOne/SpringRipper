package com.github.union;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByName(String name);

    List<Person> findByNameLike(String nameLike);
}