package org.example.entity;

public class Ingredient {
    private String name;
    private int id;
    private int mealId;

    public Ingredient(int id, String name, int mealId) {
        this.id = id;
        this.name = name;
        this.mealId = mealId;
    }

    public Ingredient(String name, int mealId) {
        this.name = name;
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
}
