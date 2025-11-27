package org.example.entity;

public class Plan {
    private String mealOption;
    private Category mealCategory;
    private int mealId;

    public Plan(String mealOption, Category mealCategory, int mealId) {
        this.mealOption = mealOption;
        this.mealCategory = mealCategory;
        this.mealId = mealId;
    }

    public String getMealOption() {
        return mealOption;
    }

    public void setMealOption(String mealOption) {
        this.mealOption = mealOption;
    }

    public Category getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(Category mealCategory) {
        this.mealCategory = mealCategory;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
}
