package com.github.union;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class SpeakerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findByName(String name) {
        Query query = entityManager.createQuery("select s from Person as s where s.name=:name");
        return query.setParameter("name", name).getResultList();
    }

    public List<Person> getAllPersons() {
        return entityManager.createQuery("from Person").getResultList();
    }

    public void save(List<Person> persons) {
        for(Person person : persons) {
            entityManager.persist(person);
        }
    }

    public long count() {
        return (long) entityManager.createQuery("select count (s.name) from Person s").getSingleResult();
    }

    public void deleteAll() {
        entityManager.createQuery("delete from Person").executeUpdate();
    }
}