package org.example.dao;

import org.example.entity.Category;
import org.example.entity.Meal;

import java.util.List;

public interface MealDAO {
    List<Meal> getMealsByCategory(Category category, boolean byOrder);
    Meal getMealByName(String name);
    void addMeal(Meal meal);
}
