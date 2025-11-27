package org.example.dao;

import org.example.entity.Plan;

import java.util.List;

public interface PlanDAO {
    List<Plan> getAllPlans();
    void addPlan(Plan plan);
    void deleteAllPlans();
}
