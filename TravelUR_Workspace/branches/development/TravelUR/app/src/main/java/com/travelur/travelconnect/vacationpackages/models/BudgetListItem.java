package com.travelur.travelconnect.vacationpackages.models;

/**
 * @author by Abhijit.
 */

public class BudgetListItem {
    String budget_type;
    String budget_name;

    public BudgetListItem(String budget_type, String budget_name) {
        this.budget_type = budget_type;
        this.budget_name = budget_name;
    }
    public String getBudget_type() {
        return budget_type;
    }

    public void setBudget_type(String budget_type) {
        this.budget_type = budget_type;
    }

    public String getBudget_name() {
        return budget_name;
    }

    public void setBudget_name(String budget_name) {
        this.budget_name = budget_name;
    }



}
