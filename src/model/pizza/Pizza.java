package model.pizza;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
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
    private List<Slice> slices = new ArrayList<>();
    private boolean is_combined = false;
    //private double cost; TODO(потом мб сделать is_updated_cost а ща оно динамически)

    public Pizza(String name, Base base, Size size) {
        this.name = name;
        this.base = base;
        setSize(size);
        initSlices();
    }

    public Pizza(String name, Base base, Size size, List<Ingredient> ingredients) {
        this(name, base, size);
        createUniformSlices(ingredients);
    }

    public Pizza(String name, Base base, Size size, List<Ingredient> ingredients, Side side) {
        this(name, base, size, ingredients);
        this.setSide(side);
    }

    public static Pizza createHalfCombined(Base base, Size size, Pizza first_half_pizza, Pizza second_half_pizza) {
        List<Ingredient> first_half_ingrs = first_half_pizza.getUniqueIngredients();
        List<Ingredient> second_half_ingrs = second_half_pizza.getUniqueIngredients();

        Pizza pizza = new Pizza("Половинка " + first_half_pizza.getName() + " + " + "половинка " + second_half_pizza.getName(), base, size);

        int slices_count = size.getSlicesCount();
        for (int i = 0; i < slices_count/2; i++) {
            pizza.addSlice(new Slice(i, first_half_ingrs));
        }

        for (int i = slices_count/2; i < size.getSlicesCount(); i++) {
            pizza.addSlice(new Slice(i, second_half_ingrs));
        }

        pizza.is_combined = true;

        return pizza;
        
    }

    private void initSlices() {
        for (int i = 0; i < size.getSlicesCount(); i++) {
            Slice slice = new Slice(i);
            slices.add(slice);
        }
    }

    private void createUniformSlices(List<Ingredient> ingredients) {
        for (Slice slice : slices) {
            for (Ingredient ingredient : ingredients) {
                slice.addIngredient(ingredient);
            }
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

    private boolean addSlice(Slice slice) {
        this.slices.add(slice);
        return true;
    }

    public boolean addIngredientLikeUniform(Ingredient ingredient) {
        if (slices.isEmpty() || slices.size() != size.getSlicesCount()) {
            return false;
        }

        for (Slice slice : slices) {
            slice.addIngredient(ingredient);
        }

        return true;
    }

    public boolean addIngredientToSlice(Ingredient ingredient, int position) {
        if (slices.isEmpty() || slices.size() != size.getSlicesCount()) {
            return false;
        }

        if (position < 0 || position >= slices.size()) {
            return false;
        }

        this.slices.get(position).addIngredient(ingredient);

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

    public List<Slice> getSlices() {
        return slices;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
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
        String str = String.format("%s Пицца %s : %.2fтнг", size.getName(), name, getCost());

        return str;
    }

    public String getFullPizzaCompositionString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("%s Пицца %s : %.2fтнг\n", size.getName(), name, getCost()));
        
        sb.append(String.format("\t%s\n", base.toString()));

        if (isCombined()) { 
            sb.append("      Первая половина:\n");
            Slice slc1 = slices.get(0);
            for (Ingredient ingr : slc1.getIngredients()) {
                sb.append(String.format("\t%s : %.2fтнг (%d кусков)\n", ingr.getName(), ingr.getCost()*size.getSlicesCount()/2, size.getSlicesCount()/2));
            }
            sb.append("      Вторая половина:\n");
            Slice slc2 = slices.get(size.getSlicesCount()-1);
            for (Ingredient ingr : slc2.getIngredients()) {
                sb.append(String.format("\t%s : %.2fтнг (%d кусков)\n", ingr.getName(), ingr.getCost()*size.getSlicesCount()/2, size.getSlicesCount()/2));
            }

        } else if (isUniform()) {
            Slice first = slices.get(0);

            for (Ingredient ingr : first.getIngredients()) {
                sb.append(String.format("\t%s : %.2fтнг (%d кусков)\n", ingr.getName(), ingr.getCost()*size.getSlicesCount(), size.getSlicesCount()));
            }
        } else {
            for (Slice slice : slices) {
                sb.append(String.format("\t%s\n", slice.toString()));
            }
        }

        if (side != null) {
            sb.append("      Дополнительно: \n");
            sb.append(String.format("\t%s\n", side.toString()));
        }
        return sb.toString();
    }

    public String getFullPizzaCompositionStringForCatalog() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s : от %.2fтнг\n", name, getCost()));
        
        sb.append(String.format("\tОснова для пиццы %s\n", base.getName()));
        
        Slice first = slices.get(0);

        for (Ingredient ingr : first.getIngredients()) {
            sb.append(String.format("\t%s\n", ingr.getName()));
        }

        if (side != null) {
            sb.append(String.format("\tБортик с %s\n", side.getName()));
        }

        // } else {
        //     sb.append(String.format("Кастомная пицца %s : %.2fтнг\\n", name, getCost()));

        //     for (Slice slice : slices) {
        //         sb.append(String.format("\t%s", slice.toString()));
        //     }
        // }

        return sb.toString();
    }
   
    public Pizza getCopy() {
        Pizza copy = new Pizza(name, base, size);
        
        if (side != null) { 
            copy.setSide(side);
        }
        

        for (Slice slice : slices) {
            copy.addSlice(slice);
        }
        
        copy.is_combined = this.is_combined;

        return copy;
    }

    public Pizza getCopyWithSize(Size size) {
        Pizza copy = new Pizza(name, base, size, this.getUniqueIngredients());
        if (side != null) { 
            copy.setSide(side);
        }
        //TODO(разные классы для разных пицц)
        
        copy.is_combined = this.is_combined;

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
        List<Ingredient> all = new ArrayList<>();
        for (Slice slice : slices) {
            all.addAll(slice.getIngredients());
        }
        return all;
    }

    public List<Ingredient> getUniqueIngredients() {
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

    public boolean isCombined() {
        return is_combined;
    }

    public void setCombined(boolean combined) {
        is_combined = combined;
    }
}   