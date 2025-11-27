package org.example.dao;

import org.example.entity.Category;
import org.example.entity.Meal;
import org.example.service.DBMealClient;

import java.util.List;

public class MealDAOImpl implements MealDAO {
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS meals (" +
            "category VARCHAR(20), meal VARCHAR(255), meal_id INT GENERATED always as IDENTITY PRIMARY KEY);";
    private static final String SELECT_ALL = "SELECT * FROM meals;";
    private static final String SELECT_BY_CATEGORY = "SELECT * FROM meals WHERE category = '%s';";
    private static final String SELECT_BY_CATEGORY_BY_ORDER = "SELECT * FROM meals WHERE category = '%s' ORDER BY meal;";
    private static final String SELECT_BY_ID = "SELECT * FROM meals WHERE meal_id = %d;";
    private static final String SELECT_MEAL_BY_NAME = "SELECT * FROM meals WHERE meal = '%s';";
    private static final String ADD_MEAL = "INSERT INTO meals (category, meal) VALUES ('%s', '%s');";
    private DBMealClient dbMealClient;

    public MealDAOImpl() {
        dbMealClient = new DBMealClient();
        dbMealClient.run(CREATE_DB);
    }

    @Override
    public List<Meal> getMealsByCategory(Category category, boolean byOrder) {
        if(byOrder) return dbMealClient.selectList(String.format(SELECT_BY_CATEGORY_BY_ORDER, category.toString()));
        else return dbMealClient.selectList(String.format(SELECT_BY_CATEGORY, category.toString()));
    }

    @Override
    public Meal getMealByName(String name){
        return dbMealClient.select(String.format(SELECT_MEAL_BY_NAME, name));
    }

    @Override
    public void addMeal(Meal meal) {
        dbMealClient.run(String.format(ADD_MEAL, meal.getCategory(), meal.getName()));
    }
}
