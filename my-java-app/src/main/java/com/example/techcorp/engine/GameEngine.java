package com.example.techcorp.engine;

import com.example.techcorp.AIPlayer;
import com.example.techcorp.Company;
import com.example.techcorp.Developer;
import com.example.techcorp.Employee;
import com.example.techcorp.GameResult;
import com.example.techcorp.Intern;
import com.example.techcorp.Project;
import com.example.techcorp.Tester;
import com.example.techcorp.events.BonusFundingEvent;
import com.example.techcorp.events.GameEvent;
import com.example.techcorp.events.HighDemandEvent;
import com.example.techcorp.events.MarketSlowdownEvent;
import com.example.techcorp.ui.ConsoleUI;

import java.util.Random;

public class GameEngine {

    private Company playerCompany;
    private AIPlayer aiPlayer;
    private ConsoleUI ui;
    private int turn;
    private int maxTurns;
    private GameResult result;
    private Random random;

    public GameEngine(Company playerCompany, AIPlayer aiPlayer, ConsoleUI ui, int maxTurns) {
        if (playerCompany == null || aiPlayer == null || ui == null) {
            throw new IllegalArgumentException("Game objects cannot be null.");
        }

        if (maxTurns <= 0) {
            throw new IllegalArgumentException("Max turns must be positive.");
        }

        this.playerCompany = playerCompany;
        this.aiPlayer = aiPlayer;
        this.ui = ui;
        this.turn = 1;
        this.maxTurns = maxTurns;
        this.result = GameResult.IN_PROGRESS;
        this.random = new Random();
    }

    public void run() {
        while (result == GameResult.IN_PROGRESS) {
            ui.showTurn(turn);
            showGameState();
            ui.showMenu();

            int choice = ui.readChoice();

            switch (choice) {
                case 1 -> workOneTurn();
                case 2 -> hireDeveloper();
                case 3 -> hireTester();
                case 4 -> showEmployees();
                case 5 -> assignEmployeeToProject();
                case 6 -> hireIntern();
                case 7 -> showProjectDetails();
                case 8 -> showScoreboard();
                case 0 -> result = GameResult.DRAW;
                default -> ui.showMessage("Invalid option.");
            }

            aiPlayer.makeDecision();

            evaluateGameResult();

            turn++;
        }

        showFinalResult();
    }

    private void workOneTurn() {
        playerCompany.workOnProjects();
        aiPlayer.getCompany().workOnProjects();

        playerCompany.paySalaries();
        aiPlayer.getCompany().paySalaries();

        playerCompany.collectRevenue();
        aiPlayer.getCompany().collectRevenue();

        triggerRandomEvent(playerCompany);
        triggerRandomEvent(aiPlayer.getCompany());

        ui.showMessage("One turn processed.");
        ui.showMessage("Player budget: " + playerCompany.getBudget());
        ui.showMessage("AI budget: " + aiPlayer.getCompany().getBudget());
    }

    private void triggerRandomEvent(Company company) {
        int chance = random.nextInt(100);

        if (chance < 30) {
            GameEvent event;
            int eventType = random.nextInt(3);

            if (eventType == 0) {
                event = new MarketSlowdownEvent();
            } else if (eventType == 1) {
                event = new BonusFundingEvent();
            } else {
                event = new HighDemandEvent();
            }

            event.apply(company);
            ui.showMessage("EVENT for " + company.getName() + ": " + event.getDescription());
        }
    }

    private void hireDeveloper() {
        Employee developer = new Developer(
            "Developer" + (playerCompany.getEmployees().size() + 1),
            8,
            800
        );

        playerCompany.hire(developer);
        ui.showMessage("Developer hired.");
    }

    private void hireTester() {
        Employee tester = new Tester(
            "Tester" + (playerCompany.getEmployees().size() + 1),
            6,
            650
        );

        playerCompany.hire(tester);
        ui.showMessage("Tester hired.");
    }

    private void hireIntern() {
        Employee intern = new Intern(
            "Intern" + (playerCompany.getEmployees().size() + 1),
            4,
            300
        );

        playerCompany.hire(intern);
        ui.showMessage("Intern hired.");
    }

    private void showEmployees() {
        ui.showMessage("=== PLAYER EMPLOYEES ===");

        for (int i = 0; i < playerCompany.getEmployees().size(); i++) {
            Employee employee = playerCompany.getEmployees().get(i);

            ui.showMessage(
                (i + 1) + ". "
                + employee.getName()
                + " | role: " + employee.getRole()
                + " | skill: " + employee.getSkill()
                + " | salary: " + employee.getSalary()
            );
        }
    }

    private void assignEmployeeToProject() {
        if (playerCompany.getEmployees().isEmpty()) {
            ui.showMessage("No employees available.");
            return;
        }

        if (playerCompany.getProjects().isEmpty()) {
            ui.showMessage("No projects available.");
            return;
        }

        ui.showMessage("Choose employee:");
        showEmployees();

        int employeeChoice = ui.readChoice();

        if (employeeChoice < 1 || employeeChoice > playerCompany.getEmployees().size()) {
            ui.showMessage("Invalid employee choice.");
            return;
        }

        ui.showMessage("Choose project:");

        for (int i = 0; i < playerCompany.getProjects().size(); i++) {
            Project project = playerCompany.getProjects().get(i);

            ui.showMessage(
                (i + 1) + ". "
                + project.getName()
                + " | status: " + project.getStatus()
                + " | progress: " + project.getProgress()
                + "/" + project.getRequiredWork()
                + " | strategic: " + project.isStrategic()
            );
        }

        int projectChoice = ui.readChoice();

        if (projectChoice < 1 || projectChoice > playerCompany.getProjects().size()) {
            ui.showMessage("Invalid project choice.");
            return;
        }

        Employee selectedEmployee = playerCompany.getEmployees().get(employeeChoice - 1);
        Project selectedProject = playerCompany.getProjects().get(projectChoice - 1);

        try {
            selectedProject.addEmployee(selectedEmployee);

            ui.showMessage(
                selectedEmployee.getName()
                + " assigned to "
                + selectedProject.getName()
            );

        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        }
    }

    private void showProjectDetails() {
        ui.showMessage("=== PLAYER PROJECT DETAILS ===");
        for (Project project : playerCompany.getProjects()){
            ui.showMessage("");
            ui.showMessage("Project: " + project.getName());
            ui.showMessage("Status: " + project.getStatus());
            ui.showMessage("Progress: " + project.getProgress() + "/" + project.getRequiredWork());
            ui.showMessage("Reward: " + project.getReward());
            ui.showMessage("Strategic: " + project.isStrategic());
            ui.showMessage("Team:");

            if (project.getTeam().isEmpty()){
                ui.showMessage("- No employees assigned");
            } else{
                for (Employee employee : project.getTeam()) {
                     ui.showMessage(
                        "- " + employee.getName()
                         + " | " + employee.getRole()
                         + " | work: " + employee.work()
                     );
                }

            }
        }
    }

    private void evaluateGameResult() {
        Company aiCompany = aiPlayer.getCompany();

        if (playerCompany.isBankrupt()) {
            result = GameResult.AI_WINS;
            return;
        }

        if (aiCompany.isBankrupt()) {
            result = GameResult.PLAYER_WINS;
            return;
        }

        if (turn >= maxTurns) {
            double playerValue = playerCompany.calculateCompanyValue();
            double aiValue = aiCompany.calculateCompanyValue();

            if (playerValue > aiValue) {
                result = GameResult.PLAYER_WINS;
            } else if (aiValue > playerValue) {
                result = GameResult.AI_WINS;
            } else {
                result = GameResult.DRAW;
            }
        }
    }

    private void showGameState() {
        showCompany(playerCompany);
        showCompany(aiPlayer.getCompany());
    }

    private void showCompany(Company company) {
        ui.showMessage("");
        ui.showMessage("Company: " + company.getName());
        ui.showMessage("Budget: " + company.getBudget());
        ui.showMessage("Reputation: " + company.getReputation());
        ui.showMessage("Value: " + company.calculateCompanyValue());

        for (Project project : company.getProjects()) {
            ui.showMessage(
                "- " + project.getName()
                + " | progress: " + project.getProgress()
                + "/" + project.getRequiredWork()
                + " | status: " + project.getStatus()
                + " | strategic: " + project.isStrategic()
            );
        }
    }

    private void showScoreboard() {
        Company aiCompany = aiPlayer.getCompany();

        ui.showMessage("");
        ui.showMessage("===== SCOREBOARD =====");

        ui.showMessage("");
        ui.showMessage("PLAYER");
        ui.showMessage("Company: " + playerCompany.getName());
        ui.showMessage("Budget: " + playerCompany.getBudget());
        ui.showMessage("Reputation: " + playerCompany.getReputation());
        ui.showMessage("Finished Projects: " + playerCompany.countFinishedProjects());
        ui.showMessage("Company Value: " + playerCompany.calculateCompanyValue());

        ui.showMessage("");
        ui.showMessage("AI");
        ui.showMessage("Company: " + aiCompany.getName());
        ui.showMessage("Budget: " + aiCompany.getBudget());
        ui.showMessage("Reputation: " + aiCompany.getReputation());
        ui.showMessage("Finished Projects: " + aiCompany.countFinishedProjects());
        ui.showMessage("Company Value: " + aiCompany.calculateCompanyValue());

        ui.showMessage("======================");
    }
private void showFinalResult() {

    Company aiCompany = aiPlayer.getCompany();

        ui.showMessage("");
        ui.showMessage("===== FINAL SCOREBOARD =====");

        ui.showMessage("");
        ui.showMessage("PLAYER");
        ui.showMessage("Company: " + playerCompany.getName());
        ui.showMessage("Budget: " + playerCompany.getBudget());
        ui.showMessage("Reputation: " + playerCompany.getReputation());
        ui.showMessage("Finished Projects: " + playerCompany.countFinishedProjects());
        ui.showMessage("Company Value: " + playerCompany.calculateCompanyValue());

        ui.showMessage("");
        ui.showMessage("AI");
        ui.showMessage("Company: " + aiCompany.getName());
        ui.showMessage("Budget: " + aiCompany.getBudget());
        ui.showMessage("Reputation: " + aiCompany.getReputation());
        ui.showMessage("Finished Projects: " + aiCompany.countFinishedProjects());
        ui.showMessage("Company Value: " + aiCompany.calculateCompanyValue());

        ui.showMessage("");
        ui.showMessage("========== GAME OVER ==========");
        ui.showMessage("Result: " + result);
    }
}