package com.github.union;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AppTest {
    @Autowired
    private SpeakerRepository speakerRepository;

    @Before
    @Rollback(false)
    public void setUp() {
        Person ivan = new Person("Ivan Govnov", "USSR");
        Person egor = new Person("Egor Letov", "USSR");
        Person volodimir = new Person("Volodimir Lenin", "USSR");
        speakerRepository.save(Arrays.asList(ivan, egor, volodimir));
    }

    @Test
    public void testCount() {
        System.out.println("Speaker count: " + speakerRepository.count());
    }
}
