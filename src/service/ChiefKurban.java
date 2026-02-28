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
        this.addIngredient(new Ingredient("Конина", 200));
        this.addIngredient(new Ingredient("Помидоры", 90));
        this.addIngredient(new Ingredient("Перчик халапеньо", 50));
        this.addIngredient(new Ingredient("Кунжут", 25));
        this.addIngredient(new Ingredient("Сыр", 40));
        this.addIngredient(new Ingredient("Моцарелла", 40));
        this.addIngredient(new Ingredient("Плавленный сыр", 40));
        this.addIngredient(new Ingredient("Сыр дорблю", 49));
        this.addIngredient(new Ingredient("Колбаска", 150));
        this.addIngredient(new Ingredient("Салями", 160));
        this.addIngredient(new Ingredient("Курочка", 170));
        this.addIngredient(new Ingredient("Ананасы", 75));
        this.addIngredient(new Ingredient("Баранина", 195));
        this.addIngredient(new Ingredient("Бекон", 140));


        this.addBase(new Base("Классическая", 100, true));
        this.addBase(new Base("Черная", 110));
        this.addBase(new Base("Лаваш", 110));

        this.addSide(new Side(ingredients.getByName("Кунжут"), null));

        this.addSide(new Side(ingredients.getByName("Сыр"), CompatibilityMode.WHITELIST, List.of("Сырная", "Четыре сыра")));
        this.addSide(new Side(ingredients.getByName("Бекон"), CompatibilityMode.BLACKLIST, List.of("Халяль")));
        
        this.addPizza(new Pizza("Пепперони", bases.getByName("Классическая"), Size.SMALL, List.of(
            ingredients.getByName("Колбаска"),
            ingredients.getByName("Салями"),
            ingredients.getByName("Сыр")
        ), sides.getByName("Кунжут")));


        this.addPizza(new Pizza("Сырная", bases.getByName("Черная"), Size.SMALL, List.of(
            ingredients.getByName("Сыр"),
            ingredients.getByName("Курочка"),
            ingredients.getByName("Кунжут")
        )));

        this.addPizza(new Pizza("Четыре сыра", bases.getByName("Классическая"), Size.SMALL, List.of(
            ingredients.getByName("Сыр"),
            ingredients.getByName("Моцарелла"),
            ingredients.getByName("Плавленный сыр"),
            ingredients.getByName("Сыр дорблю")
        )));        

        this.addPizza(new Pizza("Халяль", bases.getByName("Лаваш"), Size.SMALL, List.of(
            ingredients.getByName("Баранина"),
            ingredients.getByName("Помидоры"),
            ingredients.getByName("Плавленный сыр")
        )));

    }
    
    public List<Ingredient> getIngredients() {
        return ingredients.getAll();
    }

    public List<Base> getBases() {
        return bases.getAll();
    }

    public List<Side> getSides() {
        return sides.getAll();
    }

    public List<Side> getPossibleSides(Pizza pizza) {
        List<Side> possible = new ArrayList<>();
        for (Side side : sides.getAll()) {
            if (side.isCompatibily(pizza)) {
                possible.add(side);
            }
        }
        return possible;
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

    private boolean addPizza(Pizza pizza) {
        catalog_pizzas.add(pizza);
        return true;
    }

    public Pizza createUniformPizza(String name, Base base, Size size, ArrayList<Ingredient> ingredients, Side side) {
        Pizza pizza = new Pizza(name, base, size, ingredients);
        
        if (side != null) {
            pizza.setSide(side);
        }

        catalog_pizzas.add(pizza);
        return pizza;
    }

    public Order createOrder() {
        Order order = new Order();
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getScheduledOrders() {
        return orders.stream()
            .filter(order -> order.isScheduled())
            .collect(Collectors.toList());
    }

    public OrderItem createOrderItemFromCatalog(Pizza selected_pizza, int quantity, boolean double_ingredients) {
        if (double_ingredients) {
            for (Slice slice : selected_pizza.getSlices()) {
                for (Ingredient ingredient : List.copyOf(slice.getIngredients())) {
                    slice.addIngredient(ingredient);
                }                
            }
        }

        return new OrderItem(selected_pizza, quantity);
    }

    public OrderItem createCustomOrderItem(Pizza custom_pizza, int quantity) {
        return new OrderItem(custom_pizza, quantity, true);
    }

    public List<Pizza> getPizzas() {
        return catalog_pizzas.getAll();
    }
}