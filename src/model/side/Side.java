package model.side;

import java.util.ArrayList;
import java.util.List;

import model.common.Product;
import model.ingredients.Ingredient;
import model.pizza.Pizza;

public class Side extends Product{
    
    private Ingredient ingredient;
    private CompatibilityMode compatibility_mode;
    private List<String> compatibility_list_of_pizza_names  = new ArrayList<>();

    public Side(Ingredient ingredient, CompatibilityMode mode) {
        super(ingredient.getName(), ingredient.getCost());
        this.compatibility_mode = mode;
        this.ingredient = ingredient;
    }

    public Side(Ingredient ingredient, CompatibilityMode mode, List<String> compatibility_list) {
        this(ingredient, mode);
        compatibility_list_of_pizza_names = compatibility_list;
    }

    public boolean setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        return true;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setCompatibilityMode(CompatibilityMode mode) {
        compatibility_mode = mode;
    }

    
    public CompatibilityMode getCompatibilityMode() {
        return compatibility_mode;
    }

    public boolean addPizzaToCompatibilityList(String pizza_name) {        
        if (!compatibility_list_of_pizza_names.contains(pizza_name.toLowerCase())) {
            compatibility_list_of_pizza_names.add(pizza_name.toLowerCase());
            return true;
        } else {
            return false;
        }
    }

    public void addPizzasToCompatibilityList(List<String> pizza_names) {
        for (String p_name : pizza_names) {
            addPizzaToCompatibilityList(p_name);
        }
    }

    public void removePizzaFromCompatibilityList(String pizza_name) {
        compatibility_list_of_pizza_names.remove(pizza_name);
    }

    public List<String> getCompatibilityList() {
        return compatibility_list_of_pizza_names;
    }

    public boolean isCompatibily(Pizza pizza) {
        if (compatibility_mode == null) {
            return true;
        }
        
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

    @Override
    public String toString() {
        String str = "Бортик с " + super.toString();
        return str;
    }
}
