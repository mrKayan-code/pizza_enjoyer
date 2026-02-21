package model.pizza;
import java.util.ArrayList;
import java.util.UUID;

import model.base.Base;
import model.ingredients.Ingredient;
import model.common.Identifiable;
import model.common.Pricable;

public class Pizza implements Identifiable, Pricable {
    private final UUID id = UUID.randomUUID();
    Base base;
    ArrayList<Ingredient> ingredients;
    double cost;
    
    Pizza(String name, Base base) {
        this.base = base;
        this.ingredients = new ArrayList<>();

    }

    Pizza(Base base, ArrayList<Ingredient> ingredients) {
        this.base = base;
        this.ingredients = ingredients;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getCost() {
        return calculateCost(0);
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