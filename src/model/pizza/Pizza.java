package model.pizza;
import java.util.ArrayList;
import java.util.UUID;

import model.base.Base;
import model.ingredients.Ingredient;
import model.common.Identifiable;
import model.common.Named;
import model.common.Pricable;
import model.pizza.Size;
import model.side.Side;

public class Pizza implements Identifiable, Pricable, Named {
    private final UUID id = UUID.randomUUID();
    
    private String name;
    private Size size;
    private Base base;
    // private Side side;
    private ArrayList<Ingredient> ingredients;
    //private double cost; TODO(потом мб сделать is_updated_cost а ща оно динамически)
    
    public Pizza(String name, Base base, Size size) {
        this.name = name;
        this.base = base;
        this.size = size;
        this.ingredients = new ArrayList<>();

    }

    public Pizza(String name, Base base, Size size, ArrayList<Ingredient> ingredients) {
        this(name, base, size);
        this.ingredients = ingredients;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public double getCost() {
        return calculateCost();
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return true;
    }

    public double calculateCost() {
        double all_cost = 0;
        all_cost += base.getCost();
        for (Ingredient ingredient : ingredients) {
            all_cost += ingredient.getCost();
        }

        all_cost *= size.getCostMultiplier();

        // cost = all_cost;
        return all_cost;
    }

    public Base getBase() {
        return base;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        String str = String.format("%s %s : %.2f$\n", size.getName(), name, getCost());
        // str += "\t" + "\tСостав: \n";
        
        // str += base.toString();
        // for (Ingredient ingredient : ingredients) {
        //     str += "\t" + ingredient.toString();
        // }

        return str;
    }

}