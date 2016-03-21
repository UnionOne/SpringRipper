package com.github.union;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    private String country;

    public Person() {

    }

    public Person(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}