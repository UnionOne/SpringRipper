package com.github.union.service;

import com.github.union.dao.PersonDAO;
import com.github.union.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Service
@ManagedBean(name = "personService")
@SessionScoped
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        this.personDAO.addPerson(person);
    }

    @Override
    @Transactional
    public void deletePerson(Person person) {
        this.personDAO.deletePerson(person);
    }

    @Override
    @Transactional
    public List<Person> listPersons() {
        return this.personDAO.listPersons();
    }
}
