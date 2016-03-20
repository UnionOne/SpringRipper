package com.github.union.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@ManagedBean(name = "person")
public class Person {
    private int id;
    private String name;
    private String country;

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
