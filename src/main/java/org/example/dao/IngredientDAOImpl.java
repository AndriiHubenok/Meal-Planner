package org.example.dao;

import org.example.entity.Ingredient;
import org.example.service.DBIngredientClient;

import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS ingredients (" +
            "ingredient VARCHAR(255), ingredient_id INT GENERATED always as IDENTITY PRIMARY KEY, meal_id INT," +
            "FOREIGN KEY (meal_id) REFERENCES meals(meal_id));";
    private static final String SELECT_ALL = "SELECT * FROM ingredients;";
    private static final String SELECT_INGREDIENTS_BY_MEAL = "SELECT * FROM ingredients WHERE meal_id = %d;";
    private static final String SELECT_BY_ID = "SELECT * FROM ingredients WHERE ingredient_id = %d;";
    private static final String ADD_INGREDIENT = "INSERT INTO ingredients (ingredient, meal_id) VALUES ('%s', %d);";
    private DBIngredientClient dbIngredientClient;

    public IngredientDAOImpl() {
        dbIngredientClient = new DBIngredientClient();
        dbIngredientClient.run(CREATE_DB);
    }

    @Override
    public List<Ingredient> getIngredientsByMeal(int mealId) {
        return dbIngredientClient.selectList(String.format(SELECT_INGREDIENTS_BY_MEAL, mealId));
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        dbIngredientClient.run(String.format(ADD_INGREDIENT, ingredient.getName(), ingredient.getMealId()));
    }
}
