package org.example.service;

import org.example.entity.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBIngredientClient {
    private static final String URL = "jdbc:postgresql:meals_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";

    public void run(String query){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Ingredient select(String query){
        List<Ingredient> ingredients = selectList(query);
        if(ingredients.isEmpty()){
            return null;
        } else {
            return ingredients.getFirst();
        }
    }

    public List<Ingredient> selectList(String query){
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("ingredient_id");
                String name = rs.getString("ingredient");
                int mealId = rs.getInt("meal_id");
                Ingredient ingredient = new Ingredient(id, name, mealId);
                ingredients.add(ingredient);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }
}
