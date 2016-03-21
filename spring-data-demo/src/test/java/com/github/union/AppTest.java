package com.github.union;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AppTest {
    @Autowired
    private PersonRepository personRepository;

    @Before
    @Rollback(false)
    public void setUp() {
        Person ivan = new Person("Ivan Govnov", "USSR");
        Person egor = new Person("Egor Letov", "USSR");
        Person volodimir = new Person("Volodimir Lenin", "USSR");
        personRepository.save(Arrays.asList(ivan, egor, volodimir));
    }

    @Test
    public void testCount() {
        System.out.println("Speaker count: " + personRepository.count());
    }

    @Test
    public void testFindAll() {
        Iterable<Person> allPersons = personRepository.getAllPersons();

        for (Person person : allPersons) {
            System.out.println(person.getName());
        }
    }

    @Test
    public void testFindByName() {
        Person person = personRepository.findByName("Stalin").get(0);
        System.out.println(person.getCountry());

        assertTrue("Success", "USSR".equals(person.getCountry()));
    }

    @After
    public void clean() {
        personRepository.deleteAll();
    }
}
