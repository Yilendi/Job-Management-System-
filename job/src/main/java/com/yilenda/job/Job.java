package com.yilenda.job;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long id;

    @Column(name="occupation")
    private String occupation;

    @Column(name="occupation_level")
    private String occupationLevel;

    @Column(name="annual_salary")
    private int annualSalary;

    // default constructor
    public Job() {}

    // constructor with parameters
    public Job(String occupation, String occupationLevel, int annualSalary) {
        this.occupation = occupation;
        this.occupationLevel = occupationLevel;
        this.annualSalary = annualSalary;
    }

    // getters and setters
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationLevel() {
        return occupationLevel;
    }

    public void setOccupationLevel(String occupationLevel) {
        this.occupationLevel = occupationLevel;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }
}

