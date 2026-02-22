package model.side;

import java.util.ArrayList;

import model.common.Product;
import model.ingredients.Ingredient;
import model.pizza.Pizza;

public class Side extends Product{
    
    private ArrayList<Ingredient> ingredients;
    private CompatibilityMode compatibility_mode;
    private ArrayList<String> compatibility_list_of_pizza_names;

    public Side(String name, double cost, CompatibilityMode mode) {
        super(name, cost);
        this.compatibility_mode = mode;
        this.ingredients = new ArrayList<>();
    }

    public Side(String name, double cost, CompatibilityMode mode, ArrayList<Ingredient> ingredients, ArrayList<String> compatibility_list) {
        this(name, cost, mode);
        this.ingredients = ingredients;
        compatibility_list_of_pizza_names = compatibility_list;
    }

    public boolean addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return true;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setCompatibilityMode(CompatibilityMode mode) {
        compatibility_mode = mode;
    }

    public CompatibilityMode getCompatibilityMode() {
        return compatibility_mode;
    }

    public boolean addPizzaToCompatibilityList(String pizza_name) {
        if (!compatibility_list_of_pizza_names.contains(pizza_name)) {
            compatibility_list_of_pizza_names.add(pizza_name);
            return true;
        } else {
            return false;
        }

    }

    public void removePizzaFromCompatibilityList(String pizza_name) {
        compatibility_list_of_pizza_names.remove(pizza_name);
    }

    public ArrayList<String> getCompatibilityList() {
        return compatibility_list_of_pizza_names;
    }

    public boolean isCompatibily(Pizza pizza) {
        String pizza_name = pizza.getName();
        boolean contains_in_list = compatibility_list_of_pizza_names.contains(pizza_name);
        
        switch (compatibility_mode) {
            case WHITELIST:
                return contains_in_list;
            case BLACKLIST:
                return !contains_in_list;
            default:
                return true;
        }
    }


}
