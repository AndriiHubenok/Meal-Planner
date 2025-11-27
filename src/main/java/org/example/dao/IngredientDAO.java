package org.example.dao;

import org.example.entity.Ingredient;

import java.util.List;

public interface IngredientDAO {
    List<Ingredient> getIngredientsByMeal(int mealId);
    void addIngredient(Ingredient ingredient);
}
