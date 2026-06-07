package com.example.techcorp.events;

import com.example.techcorp.Company;

public class HighDemandEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.addBudget(7000);
    }

    @Override
    public String getDescription() {
        return "High demand! A client paid 7000 extra budget.";
    }
}