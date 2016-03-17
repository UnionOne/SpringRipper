package com.github.union.model;

import javax.persistence.*;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue
    private Integer id;

    private String naame;

    @Column(name = "employees_number")
    private Integer employeesNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaame() {
        return naame;
    }

    public void setNaame(String naame) {
        this.naame = naame;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }
}