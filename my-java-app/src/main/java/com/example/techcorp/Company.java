package com.example.techcorp;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private double budget;
    private int reputation;
    private List<Employee> employees;
    private List<Project> projects;

    public Company(String name, double budget) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be blank.");
        }

        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative.");
        }

        this.name = name;
        this.budget = budget;
        this.reputation = 0;
        this.employees = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void hire(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }

        employees.add(employee);
    }

    public void startProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }

        projects.add(project);
    }

    public void workOnProjects() {
        for (Project project : projects) {
            project.workOneTurn();
        }
    }

    public double calculateTotalSalaryCost() {
        double total = 0;

        for (Employee employee : employees) {
            total += employee.getSalary();
        }

        return total;
    }

    public void paySalaries() {
        budget -= calculateTotalSalaryCost();
    }

    public void collectRevenue() {
        for (Project project : projects) {
            if (project.isFinished() && !project.isPaid()) {
                budget += project.getReward();
                reputation += 10;
                project.markAsPaid();
            }
        }
    }

    public boolean isBankrupt() {
        return budget < 0;
    }

    public int countFinishedProjects() {
        int count = 0;

        for (Project project : projects) {
            if (project.isFinished()) {
                count++;
            }
        }

        return count;
    }

    public boolean hasFinishedStrategicProject() {
        for (Project project : projects) {
            if (project.isStrategic() && project.isFinished()) {
                return true;
            }
        }

        return false;
    }

    public double calculateCompanyValue() {
        return budget + reputation * 1000 + countFinishedProjects() * 5000;
    }

    public void reduceBudget(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        budget -= amount;
    }

    public void addBudget(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        budget += amount;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public int getReputation() {
        return reputation;
    }

    public void showStatus() {
        System.out.println("=== COMPANY STATUS ===");
        System.out.println("Company: " + name);
        System.out.println("Budget: " + budget);
        System.out.println("Reputation: " + reputation);
        System.out.println("Value: " + calculateCompanyValue());
        System.out.println("Employees: " + employees.size());
        System.out.println("Projects: " + projects.size());
        System.out.println("======================");
    }
}