package com.yilenda.job;

public class Job {
    private String occupation;
    private String occupationLevel;
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

