package com.example.techcorp.ui;

import com.example.techcorp.Company;
import com.example.techcorp.Project;

public class ConsoleUI {

    public void showTurn(int turn) {
        System.out.println();
        System.out.println("=== TURN " + turn + " ===");
    }

    public void showCompanyStatus(Company company) {
        System.out.println("Company: " + company.getName());
        System.out.println("Budget: " + company.getBudget());

        System.out.println("Projects:");

        for (Project project : company.getProjects()) {
            System.out.println(
                "- " + project.getName()
                + " | status: " + project.getStatus()
                + " | progress: "
                + project.getProgress()
                + "/"
                + project.getRequiredWork()
            );
        }
    }

    public void showMenu() {
        System.out.println();
        System.out.println("1. Work one turn");
        System.out.println("2. Hire Developer");
        System.out.println("3. Hire Tester");
        System.out.println("4. Show Employees");
        System.out.println("5. Assign Employee to Project");
        System.out.println("6. Hire Intern");
        System.out.println("7. Show Project Details");
        System.out.println("8. Show Scoreboard");
        System.out.println("0. Exit");
    }

    public int readChoice() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextInt();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}