package com.example.techcorp.api;

import com.example.techcorp.AIPlayer;
import com.example.techcorp.Company;
import com.example.techcorp.Developer;
import com.example.techcorp.Employee;
import com.example.techcorp.GameResult;
import com.example.techcorp.Intern;
import com.example.techcorp.Manager;
import com.example.techcorp.Project;
import com.example.techcorp.Tester;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private Company playerCompany;
    private Company aiCompany;
    private AIPlayer aiPlayer;
    private int turn;
    private int maxTurns;
    private GameResult result;

    public GameService() {
        startNewGame();
    }

    public void startNewGame() {
        playerCompany = new Company("Player TechCorp", 50000);

        Employee anna = new Developer("Anna", 9, 800);
        Employee piotr = new Tester("Piotr", 6, 650);
        Employee ewa = new Manager("Ewa", 7, 900);

        playerCompany.hire(anna);
        playerCompany.hire(piotr);
        playerCompany.hire(ewa);

        playerCompany.startProject(new Project("Strategic AI Platform", 60, 50000, true));
        playerCompany.startProject(new Project("Company Website", 40, 15000, false));
        playerCompany.startProject(new Project("Mobile App", 50, 25000, false));

        aiCompany = new Company("AI Systems Ltd", 50000);

        Employee botDev = new Developer("Bot Dev", 7, 650);
        Employee botTester = new Tester("Bot Tester", 5, 550);

        aiCompany.hire(botDev);
        aiCompany.hire(botTester);

        aiCompany.startProject(new Project("Competing AI Platform", 60, 50000, true));

        aiPlayer = new AIPlayer(aiCompany);

        turn = 1;
        maxTurns = 12;
        result = GameResult.IN_PROGRESS;
    }

    public Company getPlayerCompany() {
        return playerCompany;
    }

    public Company getAiCompany() {
        return aiCompany;
    }

    public int getTurn() {
        return turn;
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    public GameResult getResult() {
        return result;
    }

    public void workOneTurn() {
        if (result != GameResult.IN_PROGRESS) {
            return;
        }

        playerCompany.workOnProjects();

        aiPlayer.makeDecision();
        aiCompany.workOnProjects();

        playerCompany.paySalaries();
        aiCompany.paySalaries();

        playerCompany.collectRevenue();
        aiCompany.collectRevenue();

        evaluateGameResult();

        turn++;
    }

    public void hireDeveloper() {
        if (result != GameResult.IN_PROGRESS) {
            return;
        }

        playerCompany.hire(new Developer(
                "Developer" + (playerCompany.getEmployees().size() + 1),
                8,
                800
        ));
    }

    public void hireTester() {
        if (result != GameResult.IN_PROGRESS) {
            return;
        }

        playerCompany.hire(new Tester(
                "Tester" + (playerCompany.getEmployees().size() + 1),
                6,
                650
        ));
    }

    public void hireIntern() {
        if (result != GameResult.IN_PROGRESS) {
            return;
        }

        playerCompany.hire(new Intern(
                "Intern" + (playerCompany.getEmployees().size() + 1),
                4,
                300
        ));
    }

    public void assignEmployeeToProject(int employeeIndex, int projectIndex) {
        if (result != GameResult.IN_PROGRESS) {
            return;
        }

        Employee employee = playerCompany.getEmployees().get(employeeIndex);
        Project project = playerCompany.getProjects().get(projectIndex);

        project.addEmployee(employee);
    }

    private void evaluateGameResult() {
    boolean playerFinished = playerCompany.hasFinishedStrategicProject();
    boolean aiFinished = aiCompany.hasFinishedStrategicProject();

    if (playerCompany.isBankrupt()) {
        result = GameResult.AI_WINS;
        return;
    }

    if (aiCompany.isBankrupt()) {
        result = GameResult.PLAYER_WINS;
        return;
    }

    if (playerFinished && !aiFinished) {
        result = GameResult.PLAYER_WINS;
        return;
    }

    if (aiFinished && !playerFinished) {
        result = GameResult.AI_WINS;
        return;
    }

    if (playerFinished && aiFinished) {
        double playerValue = playerCompany.calculateCompanyValue();
        double aiValue = aiCompany.calculateCompanyValue();

        if (playerValue > aiValue) {
            result = GameResult.PLAYER_WINS;
        } else if (aiValue > playerValue) {
            result = GameResult.AI_WINS;
        } else {
            result = GameResult.DRAW;
        }

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
}