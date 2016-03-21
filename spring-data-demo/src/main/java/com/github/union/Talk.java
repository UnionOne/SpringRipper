package com.github.union;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Talk {
    @Id
    @GeneratedValue
    private Long talkId;

    private Date when;

    private String title;

    public Talk() {

    }

    public Talk(Date when, String title) {
        this.when = when;
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public Date getWhen() {
        return when;
    }
}