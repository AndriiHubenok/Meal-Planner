package org.example.service;

import org.example.entity.Category;
import org.example.entity.Plan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBPlanClient {
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

    public Plan select(String query){
        List<Plan> plans = selectList(query);
        if(plans.isEmpty()){
            return null;
        } else {
            return plans.getFirst();
        }
    }

    public List<Plan> selectList(String query){
        List<Plan> plans = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                String mealOption = rs.getString("meal_option");
                Category category = Category.valueOf(rs.getString("meal_category").toUpperCase());
                int mealId = rs.getInt("meal_id");
                Plan plan = new Plan(mealOption, category, mealId);
                plans.add(plan);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plans;
    }
}
