package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.ingredients.Ingredient;
import model.order.Order;
import model.pizza.Pizza;
import model.pizza.Size;
import model.base.Base;
import model.side.Side;
import model.side.CompatibilityMode;
public class ChiefKurban {
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Side> sides = new ArrayList<>();
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public ChiefKurban() {
        this.addIngredient(new Ingredient("Конина", 100));
        this.addIngredient(new Ingredient("Помидоры", 150));
        this.addIngredient(new Ingredient("Перец халапеньо", 50));
        this.addIngredient(new Ingredient("Кунжут", 25));
        this.addIngredient(new Ingredient("Сыр", 40));

        this.addBase(new Base("Классическая", 100, true));
        this.addBase(new Base("Черная", 110));

        this.addSide(new Side(new Ingredient("Кунжут", 25), 25, null));
        this.addSide(new Side(new Ingredient("Сыр", 40), 40, CompatibilityMode.WHITELIST));
        
    }

    private boolean addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return true;
    }

    private boolean addBase(Base base) {
        bases.add(base);
        return true;
    }

    private boolean addSide(Side side) {
        sides.add(side);
        return true;
    }

    public Pizza createPizza(String name, Base base, Size size, List<Ingredient> ingredients) {
        Pizza pizza = new Pizza(name, base, size);
        
        for (Ingredient ingredient : ingredients) {
            pizza.addIngredient(ingredient);
        }
        
        pizzas.add(pizza);
        return pizza;
    }

    public List<Pizza> filterPizzasByIngredient(String ingredientName) {
        return pizzas.stream()
            .filter(pizza -> pizza.getIngredients().stream()
            .anyMatch(ingredient -> ingredient.getName().toLowerCase().contains(ingredientName.toLowerCase())))
            .collect(Collectors.toList());
    }
    
    // public List<Order> filterOrdersByDate(date) {
    //     TODO(нужно при создании заказов добавлять время)
    // }
}