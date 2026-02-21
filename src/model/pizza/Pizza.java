package model.pizza;
import java.util.ArrayList;

import model.base.Base;
import model.ingredients.Ingredient;


public class Pizza {
    Base base;
    ArrayList<Ingredient> ingredients;
    double cost;

    Pizza(Base base) {
        this.base = base;
        this.ingredients = new ArrayList<>();
    }

    Pizza(Base base, ArrayList<Ingredient> ingredients) {
        this.base = base;
        this.ingredients = ingredients;
    }

    public boolean addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return true;
    }

    public double calculateCost(double overprice) {
        double all_cost = 0;
        all_cost += base.cost;
        for (Ingredient ingredient : ingredients) {
            all_cost += ingredient.cost;
        }

        all_cost += overprice;
        
        cost = all_cost;
        return all_cost;
    }
}