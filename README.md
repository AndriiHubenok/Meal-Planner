# Meal Planner

A console-based Java application for storing recipes, planning a weekly menu, and automatically generating a shopping list. Data is persisted in a relational PostgreSQL database.

## 📋 Features

The application supports the following commands via a text-based interface:

* **Add:** Add a new meal.
    * Categories: `breakfast`, `lunch`, `dinner`.
    * Ingredients are entered as a comma-separated list (e.g., `eggs, milk, cheese`).
* **Show:** View stored meals for a specific category.
* **Plan:** Create a meal plan for the week (Monday through Sunday).
    * Users select meals from the stored list for every mealtime.
* **List Plan:** Display the current weekly plan.
* **Save:** Save the shopping list of ingredients for the planned meals to a text file.
    * Ingredients are automatically grouped (e.g., `eggs x3`).
* **Exit:** Terminate the program.

## 🛠 Technologies

* **Java 24**
* **Maven** — for project build and dependency management.
* **PostgreSQL** — database for storing meals and plans.
* **JDBC** — for database interaction.

## ⚙️ Prerequisites and Database Setup

Before running the application, you must configure the PostgreSQL database, as the connection parameters are hardcoded in the source code (`DBMealClient.java`, `DBIngredientClient.java`, `DBPlanClient.java`).

1.  Install PostgreSQL.
2.  Create a database and a user with the following parameters:
    * **Database URL:** `jdbc:postgresql:meals_db`
    * **Database Name:** `meals_db`
    * **User:** `postgres`
    * **Password:** `1111`

You can execute the following SQL commands in `psql` or pgAdmin:
