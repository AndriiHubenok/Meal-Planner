package org.example.dao;

import org.example.entity.Plan;
import org.example.service.DBPlanClient;

import java.util.List;

public class PlanDAOImpl implements PlanDAO {
    private static final String CREATE_DB = "CREATE TABLE if not exists plan (" +
            "meal_option VARCHAR(255), meal_category VARCHAR(20), meal_id INT," +
            "FOREIGN KEY (meal_id) REFERENCES meals(meal_id));";
    private static final String DROP_DB = "DROP TABLE IF EXISTS plan;";
    private static final String ADD_PLAN = "INSERT INTO plan (meal_option, meal_category, meal_id)" +
            " VALUES ('%s', '%s', %d);";
    private static final String GET_ALL_PLANS = "SELECT * FROM plan;";
    private DBPlanClient dbPlanClient;

    public PlanDAOImpl() {
        this.dbPlanClient = new DBPlanClient();
        dbPlanClient.run(CREATE_DB);
    }

    @Override
    public List<Plan> getAllPlans() {
        return dbPlanClient.selectList(GET_ALL_PLANS);
    }

    @Override
    public void addPlan(Plan plan) {
        dbPlanClient.run(String.format(ADD_PLAN, plan.getMealOption(),
                plan.getMealCategory(), plan.getMealId()));
    }

    @Override
    public void deleteAllPlans() {
        dbPlanClient.run(DROP_DB);
        dbPlanClient.run(CREATE_DB);
    }
}
