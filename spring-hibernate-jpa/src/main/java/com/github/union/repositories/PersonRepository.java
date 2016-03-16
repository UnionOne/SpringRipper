package com.github.union.repositories;

import com.github.union.model.CreditCard;
import com.github.union.model.Person;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;

import static com.github.union.model.CreditCard.*;
import static com.github.union.model.Person.*;
import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;
import static org.hibernate.criterion.Restrictions.eq;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Person find(Long id) {
        return entityManager.find(Person.class, id);
    }

    public Collection<Person> findAll() {
        return getCurrentSession().createCriteria(Person.class)
                .setResultTransformer(DISTINCT_ROOT_ENTITY)
                .list();
    }

    public void remove(Person person) {
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
    }

    public Person createWithCard(String name, String cardNumber) {
        CreditCard creditCard = createCard(cardNumber);
        Person person = createPerson(name, Collections.singletonList(creditCard));

        entityManager.persist(person);
        return person;
    }

    public Collection<Person> searchByCreditCarNumber(String number) {
        return getCurrentSession()
                .createCriteria(Person.class)
                .setResultTransformer(DISTINCT_ROOT_ENTITY)
                .createAlias("creditCards", "c")
                .add(eq("c.number", number))
                .list();
    }

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }
}