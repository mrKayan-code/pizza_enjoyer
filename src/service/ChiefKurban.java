package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.ingredients.Ingredient;
import model.order.Order;
import model.order.OrderItem;
import model.pizza.Pizza;
import model.pizza.Size;
import model.pizza.Slice;
import model.base.Base;
import model.side.Side;
import model.side.CompatibilityMode;


public class ChiefKurban {
    private ProductCatalog<Base> bases = new ProductCatalog<>();
    private ProductCatalog<Ingredient> ingredients = new ProductCatalog<>();
    private ProductCatalog<Side> sides = new ProductCatalog<>();
    private ProductCatalog<Pizza> catalog_pizzas = new ProductCatalog<>();
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
    
    public ArrayList<Ingredient> getIngredients() {
        return ingredients.getAll();
    }

    public ArrayList<Base> getBases() {
        return bases.getAll();
    }

    public ArrayList<Side> getSides() {
        return sides.getAll();
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

    public Pizza createUniformPizza(String name, Base base, Size size, ArrayList<Ingredient> ingredients/*Side side*/) {
        Pizza pizza = new Pizza(name, base, size, ingredients);
        // pizza.setSide(side);
        catalog_pizzas.add(pizza);
        return pizza;
    }

    public Order createOrder() {
        Order order = new Order();
        orders.add(order);
        return order;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public List<Order> getScheduledOrders() {
        return orders.stream()
            .filter(order -> order.isScheduled())
            .collect(Collectors.toList());
    }

    public OrderItem createOrderItemFromCatalog(Pizza catalog_pizza, int quantity, boolean double_ingredients) {
        Pizza order_pizza = catalog_pizza.GetCopy();
        
        if (double_ingredients) {
            for (Slice slice : catalog_pizza.getSlices()) {
                for (Ingredient ingredient : slice.getIngredients()) {
                    slice.addIngredient(ingredient);
                }                
            }
        }

        return new OrderItem(order_pizza, quantity);
    }

    public OrderItem createCustomOrderItem(Pizza custom_pizza, int quantity) {
        return new OrderItem(custom_pizza, quantity, true);
    }

}