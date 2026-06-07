package com.example.techcorp.api;

public class EmployeeInfo {

    private String name;
    private String role;
    private int skill;
    private double salary;

    public EmployeeInfo(String name, String role, int skill, double salary) {
        this.name = name;
        this.role = role;
        this.skill = skill;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getSkill() {
        return skill;
    }

    public double getSalary() {
        return salary;
    }
}