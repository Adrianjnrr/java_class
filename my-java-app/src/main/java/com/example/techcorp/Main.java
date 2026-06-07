package com.example.techcorp;

import com.example.techcorp.engine.GameEngine;
import com.example.techcorp.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        Company playerCompany = new Company("Player TechCorp", 50000);

        Employee anna = new Developer("Anna", 9, 800);
        Employee piotr = new Tester("Piotr", 6, 650);
        Employee ewa = new Manager("Ewa", 7, 900);

        playerCompany.hire(anna);
        playerCompany.hire(piotr);
        playerCompany.hire(ewa);

        Project playerProject = new Project(
            "Strategic AI Platform",
            60,
            50000,
            true
        );

        
        Project websiteProject = new Project(
            "Company Website",
            40,
            15000,
            false
        );

        Project mobileAppProject = new Project(
            "Mobile App",
            50,
            25000,
            false
        );

        //playerProject.addEmployee(anna);
        //playerProject.addEmployee(piotr);
        //playerProject.addEmployee(ewa);

        playerCompany.startProject(playerProject);
        playerCompany.startProject(websiteProject);
        playerCompany.startProject(mobileAppProject);       

        Company aiCompany = new Company("AI Systems Ltd", 50000);

        Employee botDev = new Developer("Bot Dev", 7, 650);
        Employee botTester = new Tester("Bot Tester", 5, 550);

        aiCompany.hire(botDev);
        aiCompany.hire(botTester);

        Project aiProject = new Project(
            "Competing AI Platform",
            60,
            50000,
            true
        );

        aiCompany.startProject(aiProject);

        AIPlayer aiPlayer = new AIPlayer(aiCompany);

        ConsoleUI ui = new ConsoleUI();

        GameEngine engine = new GameEngine(
            playerCompany,
            aiPlayer,
            ui,
            12
        );

        engine.run();
    }
}