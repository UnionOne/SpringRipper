package com.github.union;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;

@Entity
public class Speaker {
    @Id
    @GeneratedValue
    private Long speakerId;

    private String name;

    @OneToMany(fetch = EAGER, cascade = ALL)
    private Set<Talk> talks;

    public Speaker() {

    }

    public Speaker(String name) {
        this.name = name;
    }

    public void addTalke(Talk talk) {
        if (talks == null) talks = new HashSet<>();
        talks.add(talk);
    }

    public String getName() {
        return name;
    }

    public Set<Talk> getTalks() {
        return talks;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                '}';
    }
}