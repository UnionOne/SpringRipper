package com.github.union;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AppTest {
    private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

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
//        LOG.info("Speaker count: " + personRepository.count());
        System.out.println("********** COUNT **********");

        System.out.println("Speaker count: " + personRepository.count());

        System.out.println("********** COUNT **********");
    }

    @Test
    public void testFindAll() {
        System.out.println("********** ALL SPEAKERS **********");

        Iterable<Person> allPersons = personRepository.findAll();
        for (Person person : allPersons) {
            System.out.println(person.getName());
        }
        System.out.println("********** ALL SPEAKERS **********");
    }

    @Test
    public void testFindByName() {
        System.out.println("********** STALIN LIVE IN USSR **********");

        Person person = personRepository.findByName("Stalin");
        assertTrue("USSR".equals(person.getCountry()));
        System.out.println(person.getCountry());

        System.out.println("********** STALIN LIVE IN USSR **********");
    }

    @Test
    public void testFindByNameLike() {
        System.out.println("********** NAME LIKE STALIN **********");

        List<Person> persons = personRepository.findByNameLike("%Stalin%");
        persons.forEach(s -> System.out.println(s.toString()));

        System.out.println("********** NAME LIKE STALIN **********");
    }

    @After
    public void clean() {
        personRepository.deleteAll();
    }
}
