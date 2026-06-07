package com.example.techcorp.api;

public class GameState {

    private String playerCompany;
    private double playerBudget;
    private int playerReputation;
    private double playerValue;
    private int playerEmployees;
    private int playerProjects;
    private int playerFinishedProjects;

    private String aiCompany;
    private double aiBudget;
    private int aiReputation;
    private double aiValue;
    private int aiEmployees;
    private int aiProjects;
    private int aiFinishedProjects;

    private int turn;
    private int maxTurns;
    private String result;

    public GameState(
            String playerCompany,
            double playerBudget,
            int playerReputation,
            double playerValue,
            int playerEmployees,
            int playerProjects,
            int playerFinishedProjects,
            String aiCompany,
            double aiBudget,
            int aiReputation,
            double aiValue,
            int aiEmployees,
            int aiProjects,
            int aiFinishedProjects,
            int turn,
            int maxTurns,
            String result) {

        this.playerCompany = playerCompany;
        this.playerBudget = playerBudget;
        this.playerReputation = playerReputation;
        this.playerValue = playerValue;
        this.playerEmployees = playerEmployees;
        this.playerProjects = playerProjects;
        this.playerFinishedProjects = playerFinishedProjects;

        this.aiCompany = aiCompany;
        this.aiBudget = aiBudget;
        this.aiReputation = aiReputation;
        this.aiValue = aiValue;
        this.aiEmployees = aiEmployees;
        this.aiProjects = aiProjects;
        this.aiFinishedProjects = aiFinishedProjects;

        this.turn = turn;
        this.maxTurns = maxTurns;
        this.result = result;
    }

    public String getPlayerCompany() { return playerCompany; }
    public double getPlayerBudget() { return playerBudget; }
    public int getPlayerReputation() { return playerReputation; }
    public double getPlayerValue() { return playerValue; }
    public int getPlayerEmployees() { return playerEmployees; }
    public int getPlayerProjects() { return playerProjects; }
    public int getPlayerFinishedProjects() { return playerFinishedProjects; }

    public String getAiCompany() { return aiCompany; }
    public double getAiBudget() { return aiBudget; }
    public int getAiReputation() { return aiReputation; }
    public double getAiValue() { return aiValue; }
    public int getAiEmployees() { return aiEmployees; }
    public int getAiProjects() { return aiProjects; }
    public int getAiFinishedProjects() { return aiFinishedProjects; }

    public int getTurn() { return turn; }
    public int getMaxTurns() { return maxTurns; }
    public String getResult() { return result; }
}