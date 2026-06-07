package com.example.techcorp.events;

import com.example.techcorp.Company;

public class BonusFundingEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.addBudget(10000);
    }

    @Override
    public String getDescription() {
        return "Investor funding! The company gained 10000 budget.";
    }
}