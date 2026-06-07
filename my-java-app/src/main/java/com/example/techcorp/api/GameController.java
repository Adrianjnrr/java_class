package com.example.techcorp.api;

import com.example.techcorp.Company;
import com.example.techcorp.Employee;
import com.example.techcorp.Project;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game/state")
    public GameState state() {
        Company player = gameService.getPlayerCompany();
        Company ai = gameService.getAiCompany();

        return new GameState(
                player.getName(),
                player.getBudget(),
                player.getReputation(),
                player.calculateCompanyValue(),
                player.getEmployees().size(),
                player.getProjects().size(),
                player.countFinishedProjects(),

                ai.getName(),
                ai.getBudget(),
                ai.getReputation(),
                ai.calculateCompanyValue(),
                ai.getEmployees().size(),
                ai.getProjects().size(),
                ai.countFinishedProjects(),

                gameService.getTurn(),
                gameService.getMaxTurns(),
                gameService.getResult().toString()
        );
    }

    @GetMapping("/game/employees")
    public List<EmployeeInfo> employees() {
        return buildEmployeeInfo(gameService.getPlayerCompany());
    }

    @GetMapping("/game/ai-employees")
    public List<EmployeeInfo> aiEmployees() {
        return buildEmployeeInfo(gameService.getAiCompany());
    }

    @GetMapping("/game/projects")
    public List<ProjectInfo> projects() {
        return buildProjectInfo(gameService.getPlayerCompany());
    }

    @GetMapping("/game/ai-projects")
    public List<ProjectInfo> aiProjects() {
        return buildProjectInfo(gameService.getAiCompany());
    }

    @PostMapping("/game/work")
    public String workOneTurn() {
        gameService.workOneTurn();
        return "One turn processed.";
    }

    @PostMapping("/game/hire/developer")
    public String hireDeveloper() {
        gameService.hireDeveloper();
        return "Developer hired.";
    }

    @PostMapping("/game/hire/tester")
    public String hireTester() {
        gameService.hireTester();
        return "Tester hired.";
    }

    @PostMapping("/game/hire/intern")
    public String hireIntern() {
        gameService.hireIntern();
        return "Intern hired.";
    }

    @PostMapping("/game/assign")
    public String assignEmployeeToProject(
            @RequestParam int employeeIndex,
            @RequestParam int projectIndex) {

        gameService.assignEmployeeToProject(employeeIndex, projectIndex);
        return "Employee assigned to project.";
    }

    @PostMapping("/game/new")
    public String newGame() {
        gameService.startNewGame();
        return "New game started.";
    }

    private List<EmployeeInfo> buildEmployeeInfo(Company company) {
        List<EmployeeInfo> result = new ArrayList<>();

        for (Employee employee : company.getEmployees()) {
            result.add(new EmployeeInfo(
                    employee.getName(),
                    employee.getRole(),
                    employee.getSkill(),
                    employee.getSalary()
            ));
        }

        return result;
    }

    private List<ProjectInfo> buildProjectInfo(Company company) {
        List<ProjectInfo> result = new ArrayList<>();

        for (Project project : company.getProjects()) {
            result.add(new ProjectInfo(
                    project.getName(),
                    project.getProgress(),
                    project.getRequiredWork(),
                    project.getReward(),
                    project.isStrategic(),
                    project.getStatus().toString()
            ));
        }

        return result;
    }
}