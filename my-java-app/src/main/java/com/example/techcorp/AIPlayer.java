package com.example.techcorp;

public class AIPlayer {

    private Company company;

    public AIPlayer(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null.");
        }

        this.company = company;
    }

    public void makeDecision() {
        if (company.getProjects().isEmpty()) {
            return;
        }

        Project firstProject = company.getProjects().get(0);

        for (Employee employee : company.getEmployees()) {
            try {
                firstProject.addEmployee(employee);
            } catch (IllegalArgumentException e) {
                // Employee is already assigned, so ignore it
            }
        }
    }

    public Company getCompany() {
        return company;
    }
}