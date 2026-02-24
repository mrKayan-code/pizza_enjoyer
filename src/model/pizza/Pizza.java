package model.pizza;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import model.base.Base;
import model.ingredients.Ingredient;
import model.common.Identifiable;
import model.common.Named;
import model.common.Pricable;
import model.side.Side;

public class Pizza implements Identifiable, Pricable, Named {
    private final UUID id = UUID.randomUUID();
    
    private String name;
    private Size size;
    private Base base;
    private Side side;
    private ArrayList<Slice> slices = new ArrayList<>();
    //private double cost; TODO(потом мб сделать is_updated_cost а ща оно динамически)
    
    public Pizza(String name, Base base, Size size) {
        this.name = name;
        this.base = base;
        this.size = size;
        side = null;
    }

    public Pizza(String name, Base base, Size size, ArrayList<Ingredient> ingredients) {
        this(name, base, size);
        createUniformSlices(ingredients);
    }

    public static Pizza createHalfCombined(String name, Base base, Size size, ArrayList<Ingredient> first_half_ingrs, ArrayList<Ingredient> second_half_ingrs) {
        Pizza pizza = new Pizza(name, base, size);

        int slices_count = size.getSlicesCount();
        for (int i = 0; i < slices_count/2; i++) {
            pizza.addSlice(new Slice(i, first_half_ingrs));
        }

        for (int i = slices_count/2; i < size.getSlicesCount(); i++) {
            pizza.addSlice(new Slice(i, second_half_ingrs));
        }

        return pizza;
        
    }

    private void createUniformSlices(ArrayList<Ingredient> ingredients) {
        for (int i = 0; i < size.getSlicesCount(); i++) {
            Slice slice = new Slice(i, ingredients);
            slices.add(slice);
        }
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

    public boolean addSlice(Slice slice) {
        this.slices.add(slice);
        return true;
    }

    public double calculateCost() {
        double all_cost = 0;
        
        all_cost += base.getCost();
        
        for (Slice slice : slices) {
            all_cost += slice.getCost();
        }

        if (side != null) {
            all_cost += side.getCost();
        }

        // cost = all_cost;
        return all_cost;
    }

    public Base getBase() {
        return base;
    }

    public ArrayList<Slice> getSlices() {
        return slices;
    }

    public Size getSize() {
        return size;
    }

    public boolean setSide(Side side) {
        if (side.isCompatibily(this)) {
            this.side = side;
            return true;
        } else {
            return false;
        }
    }

    public Side getSide() {
        return side;
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

    public Pizza GetCopy() {
        Pizza copy = new Pizza(name, base, size);
        copy.setSide(side);

        for (Slice slice : slices) {
            copy.addSlice(slice);
        }

        return copy;
    }

    public boolean isUniform() {
        if (slices.isEmpty()) return true;

        Slice first = slices.get(0);
        
        for (int i = 1; i < size.getSlicesCount(); i++) {
            if (!first.hasSameIngredientsAs(slices.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<Ingredient> getAllIngredients() {
        ArrayList<Ingredient> all = new ArrayList<>();
        for (Slice slice : slices) {
            all.addAll(slice.getIngredients());
        }
        return all;
    }

    public ArrayList<Ingredient> getUniqueIngredients() {
        Map<String, Ingredient> uniqueMap = new LinkedHashMap<>();
        
        for (Slice slice : slices) {
            for (Ingredient ing : slice.getIngredients()) {
                if (!uniqueMap.containsKey(ing.getName())) {
                    uniqueMap.put(ing.getName(), ing);
                }
            }
        }
        
        return new ArrayList<>(uniqueMap.values());
    }

    public boolean hasIngredient(String ingredientName) {
        return slices.stream()
            .flatMap(slice -> slice.getIngredients().stream())
            .anyMatch(ing -> ing.getName().equalsIgnoreCase(ingredientName));
    }

}   