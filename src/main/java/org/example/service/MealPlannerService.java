package org.example.service;

import org.example.dao.*;
import org.example.entity.Category;
import org.example.entity.Ingredient;
import org.example.entity.Meal;
import org.example.entity.Plan;

import java.util.List;

public class MealPlannerService {
    private MealDAO mealDAO;
    private IngredientDAO ingredientDAO;
    private PlanDAO planDAO;

    public MealPlannerService() {
        mealDAO = new MealDAOImpl();
        ingredientDAO = new IngredientDAOImpl();
        planDAO = new PlanDAOImpl();
    }

    public List<Meal> getMealListByCategory(Category category) {
        return mealDAO.getMealsByCategory(category, false);
    }

    public List<Meal> getMealListByCategoryByOrder(Category category) {
        return mealDAO.getMealsByCategory(category, true);
    }

    public List<Ingredient> getIngredientList(int mealId) {
        return ingredientDAO.getIngredientsByMeal(mealId);
    }

    public List<Plan> getPlanList() {
        return planDAO.getAllPlans();
    }

    public void addMeal(Category category, String name, List<String> ingredients) {
        mealDAO.addMeal(new Meal(category, name));
        Meal meal = mealDAO.getMealByName(name);

        for(String ingredient : ingredients){
            ingredientDAO.addIngredient(new Ingredient(ingredient, meal.getId()));
        }
    }

    public void addPlan(String mealOption, Category category, int mealId) {
        planDAO.addPlan(new Plan(mealOption, category, mealId));
    }

    public void deleteAllPlans(){
        planDAO.deleteAllPlans();
    }
}
