package model.pizza;

import java.util.ArrayList;

import model.common.Pricable;
import model.ingredients.Ingredient;

public class Slice implements Pricable{
    private final int position;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public Slice (int position) {
        this.position = position;
    }

    public Slice(int position, ArrayList<Ingredient> ingredients) {
        this(position);
        this.ingredients = ingredients;
    }

    public int getPosition() { 
        return position; 
    }
    
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
    public ArrayList<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    @Override
    public double getCost() {
        return calculateCost();
    }

    public double calculateCost() {
        double all_cost = 0;

        for (Ingredient ingredient : ingredients) {
            all_cost += ingredient.getCost();
        }

        return all_cost;
    }

    public boolean isEmpty() {
        return ingredients.isEmpty();
    }

    public boolean hasSameIngredientsAs(Slice other) {
        if (other == null) return false;
        if (this.ingredients.size() != other.ingredients.size()) return false;
        
        for (Ingredient ing : this.ingredients) {
            if (!other.ingredients.contains(ing)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\tкусок %d: %.2f$", position, getCost()));
        for (Ingredient ingredient : ingredients) {
            sb.append("\t\t" + ingredient.toString());
        }

        return sb.toString();
    }

}
