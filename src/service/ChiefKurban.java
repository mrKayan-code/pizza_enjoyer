package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.ingredients.Ingredient;
import model.order.Order;
import model.order.OrderItem;
import model.order.OrderStatus;
import model.pizza.Pizza;
import model.pizza.Size;
import model.base.Base;
import model.side.Side;
import model.side.CompatibilityMode;
public class ChiefKurban {
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Side> sides = new ArrayList<>();
    private ArrayList<Pizza> catalog_pizzas = new ArrayList<>();
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
        
        catalog_pizzas.add(pizza);
        return pizza;
    }

    public List<Pizza> filterPizzasByIngredient(String ingredientName) {
        return catalog_pizzas.stream()
            .filter(pizza -> pizza.getIngredients().stream()
            .anyMatch(ingredient -> ingredient.getName().toLowerCase().contains(ingredientName.toLowerCase())))
            .collect(Collectors.toList());
    }

    public Order createOrder() {
        Order order = new Order();
        orders.add(order);
        return order;
    }

    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> filterOrdersByDate(LocalDate date) {
        return orders.stream()
            .filter(order -> order.getOrderTime().equals(date))
            .collect(Collectors.toList()); //TODO(разобраться с датами в джаве)
    }

    public List<Order> getScheduledOrders() {
        return orders.stream()
            .filter(order -> order.isScheduled())
            .collect(Collectors.toList());
    }

    public List<Order> filterOrdersByStatus(OrderStatus status) {
        return orders.stream()
            .filter(order -> order.getStatus() == status)
            .collect(Collectors.toList());
    }

    public OrderItem createOrderItemFromCatalog(Pizza catalog_pizza, int quantity, boolean double_ingredients) {
        Pizza order_pizza = catalog_pizza.GetCopy();
        
        if (double_ingredients) {
            for (Ingredient ingredient : catalog_pizza.getIngredients()) {
                order_pizza.addIngredient(ingredient);
            }
        }

        return new OrderItem(order_pizza, quantity);
    }

    public OrderItem createCustomOrderItem(Pizza custom_pizza, int quantity) {
        return new OrderItem(custom_pizza, quantity, true);
    }

    
    // public List<Order> filterOrdersByDate(date) {
    //     TODO(нужно при создании заказов добавлять время)
    // }
}