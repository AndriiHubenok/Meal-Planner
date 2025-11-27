package org.example.entity;

public class Meal {
    private Category category;
    private String name;
    private int id;

    public Meal(int id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Meal(Category category, String name) {
        this.category = category;
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
}
