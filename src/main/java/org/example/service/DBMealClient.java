package org.example.service;

import org.example.entity.Category;
import org.example.entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBMealClient {
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

    public Meal select(String query){
        List<Meal> meals = selectList(query);
        if(meals.isEmpty()){
            return null;
        } else {
            return meals.getFirst();
        }
    }

    public List<Meal> selectList(String query){
        List<Meal> meals = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("meal_id");
                String name = rs.getString("meal");
                Category category = Category.valueOf(rs.getString("category").toUpperCase());
                Meal meal = new Meal(id, name, category);
                meals.add(meal);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return meals;
    }
}
