package com.example.techcorp.api;

public class ProjectInfo {

    private String name;
    private int progress;
    private int requiredWork;
    private double reward;
    private boolean strategic;
    private String status;

    public ProjectInfo(String name, int progress, int requiredWork, double reward, boolean strategic, String status) {
        this.name = name;
        this.progress = progress;
        this.requiredWork = requiredWork;
        this.reward = reward;
        this.strategic = strategic;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public int getRequiredWork() {
        return requiredWork;
    }

    public double getReward() {
        return reward;
    }

    public boolean isStrategic() {
        return strategic;
    }

    public String getStatus() {
        return status;
    }
}