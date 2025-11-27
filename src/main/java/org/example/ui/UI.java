package org.example.ui;

import org.example.entity.Category;
import org.example.entity.Ingredient;
import org.example.entity.Meal;
import org.example.entity.Plan;
import org.example.service.MealPlannerService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UI {
    private static final List<String> DAYS = List.of("Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    private final Scanner scanner = new Scanner(System.in);
    private MealPlannerService mealPlannerService;

    public UI() {
        mealPlannerService = new MealPlannerService();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("What would you like to do (add, show, plan, list plan, save, exit)?");
            String input = scanner.nextLine();
            switch (input) {
                case "add":
                    addMenu();
                    break;
                case "show":
                    showMenu();
                    break;
                case "plan":
                    planMenu();
                    break;
                case "list plan":
                    listPlanMenu();
                    break;
                case "save":
                    saveMenu();
                    break;
                case "exit":
                    running = false;
                    System.out.println("Bye!");
                    break;
            }
        }
    }

    private void addMenu(){
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        Category category;
        while (true) {
            try{
                category = Category.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            }

        }

        System.out.println("Input the meal's name:");
        String name;
        while(true){
            name = scanner.nextLine();
            if(checkCorrectInput(name)){
                break;
            }
        }


        System.out.println("Input the ingredients:");
        List<String> ingredients =  new ArrayList<>();
        boolean isLooping = true;
        while(isLooping){
            ingredients = List.of(scanner.nextLine().split(","));
            for(String ingredient : ingredients){
                if(checkCorrectInput(ingredient)){
                    isLooping = false;
                } else {
                    isLooping = true;
                    break;
                }
            }
        }
        List<String> cleanedIngredients = ingredients.stream()
                .map(String::stripLeading)
                .toList();

        mealPlannerService.addMeal(category, name, cleanedIngredients);
        System.out.println("The meal has been added!");
    }

    private void showMenu(){
        boolean isWorking = true;
        Category category = null;
        while(isWorking){
            System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
            String input = scanner.nextLine();
            switch (input) {
                case "breakfast":
                    category = Category.BREAKFAST;
                    isWorking = false;
                    break;
                case "lunch":
                    category = Category.LUNCH;
                    isWorking = false;
                    break;
                case "dinner":
                    category = Category.DINNER;
                    isWorking = false;
                    break;
                default:
                    System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            }

        }
        List<Meal> meals = mealPlannerService.getMealListByCategory(category);
        if(meals.isEmpty()){
            System.out.println("No meals found.");
            return;
        }

        System.out.println("Category: " + category.toString().toLowerCase());
        for(Meal meal : meals){
            System.out.println("\nName: " + meal.getName());
            System.out.println("Ingredients:");

            for(Ingredient i : mealPlannerService.getIngredientList(meal.getId())){
                System.out.println(i.getName());
            }
        }
    }

    private void planMenu(){
        mealPlannerService.deleteAllPlans();

        List<Meal> breakfasts = mealPlannerService.getMealListByCategoryByOrder(Category.BREAKFAST);
        List<String> breakfastsString = breakfasts.stream().map(Meal::getName).toList();
        List<Meal> lunches = mealPlannerService.getMealListByCategoryByOrder(Category.LUNCH);
        List<String> lunchesString = lunches.stream().map(Meal::getName).toList();
        List<Meal> dinners = mealPlannerService.getMealListByCategoryByOrder(Category.DINNER);
        List<String> dinnersString = dinners.stream().map(Meal::getName).toList();

        for(String day : DAYS){
            System.out.println(day);
            boolean isLooping = true;
            while(isLooping){
                breakfastsString.forEach(System.out::println);
                System.out.println("Choose the breakfast for " + day + " from the list above:");
                String input = scanner.nextLine();

                if(breakfastsString.contains(input)){
                    int index = breakfastsString.indexOf(input);
                    mealPlannerService.addPlan(breakfasts.get(index).getName(), Category.BREAKFAST, breakfasts.get(index).getId());
                    isLooping = false;
                } else {
                    System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                }
            }
            isLooping = true;
            while(isLooping){
                lunchesString.forEach(System.out::println);
                System.out.println("Choose the lunch for " + day + " from the list above:");
                String input = scanner.nextLine();

                if(lunchesString.contains(input)){
                    int index = lunchesString.indexOf(input);
                    mealPlannerService.addPlan(lunches.get(index).getName(), Category.BREAKFAST, lunches.get(index).getId());
                    isLooping = false;
                } else {
                    System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                }
            }
            isLooping = true;
            while(isLooping){
                dinnersString.forEach(System.out::println);
                System.out.println("Choose the dinner for " + day + " from the list above:");
                String input = scanner.nextLine();

                if(dinnersString.contains(input)){
                    int index = dinnersString.indexOf(input);
                    mealPlannerService.addPlan(dinners.get(index).getName(), Category.BREAKFAST, dinners.get(index).getId());
                    isLooping = false;
                } else {
                    System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                }
            }
            System.out.println("Yeah! We planned the meals for " + day + ".");
        }
        System.out.println();
        listPlanMenu();
    }

    private void listPlanMenu(){
        List<Plan> plans = mealPlannerService.getPlanList();
        int index = 0;
        for(String day : DAYS){
            System.out.println(day);
            System.out.println("Breakfast: " + plans.get(index++).getMealOption());
            System.out.println("Lunch: " + plans.get(index++).getMealOption());
            System.out.println("Dinner: " + plans.get(index++).getMealOption());
            System.out.println();
        }
    }

    private void saveMenu(){
        List<Integer> plans = mealPlannerService.getPlanList().stream().map(Plan::getMealId).toList();
        if(plans.isEmpty()){
            System.out.println("Unable to save. Plan your meals first.");
            return;
        }

        System.out.println("Input a filename:");
        File file = new File(scanner.nextLine());
        List<String> ingredients = new ArrayList<>();
        for(Integer i : plans){
            ingredients.addAll(mealPlannerService.getIngredientList(i)
                    .stream().map(Ingredient::getName).toList());
        }

        Map<String, Integer> ingredientAmount = new HashMap<>();
        for(String ingredient : ingredients){
            ingredientAmount.put(ingredient,
                    ingredientAmount.getOrDefault(ingredient, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        Set<String> uniqueIngredients = ingredientAmount.keySet();
        for(String ingredient : uniqueIngredients){
            sb.append(ingredient);
            if(ingredientAmount.get(ingredient) > 1){
                sb.append(" x").append(ingredientAmount.get(ingredient));
            }
            sb.append("\n");
        }

        try(FileWriter writer = new FileWriter(file, false)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved!");
    }

    private boolean checkCorrectInput(String name){
        if (name.isBlank() || !name.matches("[a-zA-Z ]+")) {
            System.out.println("Wrong format. Use letters only!");
            return false;
        } else {
            return true;
        }
    }
}
