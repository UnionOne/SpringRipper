package com.github.union.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.union.config.AppConfig;
import com.github.union.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:META-INF/database.xml")
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void create() {
        Person newPerson = new Person();
        newPerson.setName("New Person");
        personRepository.create(newPerson);

        boolean match = false;
        for (Person person : personRepository.findAll()) {
            System.out.println(person);
            match = person.getName().equals(newPerson.getName());
            if (match) break;
        }
        assertTrue(match);
    }

    @Test
    public void findById() {
        Person person = personRepository.find(1L);
        assertTrue(person.getName().equals("Morgan Freeman"));
    }

    @Test
    public void findAll() {
        Collection<Person> persons = personRepository.findAll();
        prettyPrint(persons);
        assertEquals(4, persons.size());
    }

    @Test
    public void remove() {
        personRepository.remove(personRepository.find(1L));
        prettyPrint(personRepository.findAll());
        assertEquals(3, personRepository.findAll().size());
    }

    @Test
    public void createWithCard() {
        String name = "New Person";
        String cardNumber = "7777";
        Person person = personRepository.createWithCard(name, cardNumber);

        prettyPrint(personRepository.findAll());
        assertEquals(5, personRepository.findAll().size());
        assertEquals(1, personRepository.find(person.getId()).getCreditCards().size());
    }

    @Test
    public void searchByCreditCardNumber() {
        String number = "1234";
        Collection<Person> persons = personRepository.searchByCreditCarNumber(number);

        prettyPrint(persons);
        assertEquals(1, persons.size());
        assertTrue("Morgan Freeman".equals(persons.iterator().next().getName()));
    }

    private <T> void prettyPrint(Iterable<T> iterable) {
        for (T obj : iterable) {
            System.out.println(obj);
        }
    }
}
