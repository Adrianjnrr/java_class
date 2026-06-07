package com.example.techcorp.events;

import com.example.techcorp.Company;

public class MarketSlowdownEvent implements GameEvent {

    @Override
    public void apply(Company company) {
        company.reduceBudget(5000);
    }

    @Override
    public String getDescription() {
        return "Market slowdown! The company lost 5000 budget.";
    }
}