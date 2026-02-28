package model.order;

import java.util.UUID;

import model.common.Identifiable;
import model.pizza.Pizza;

public class OrderItem implements Identifiable {
    private final UUID id = UUID.randomUUID();
    
    private Pizza pizza;
    private int quantity;
    private boolean is_custom_pizza;

    public OrderItem(Pizza pizza, int quantity) {
        this(pizza, quantity, false);
    }
    
    public OrderItem(Pizza pizza, int quantity, boolean is_custom_pizza) {
        if (quantity < 1) throw new IllegalArgumentException("Количество должно быть >= 1");
        this.pizza = pizza;
        this.quantity = quantity;
        this.is_custom_pizza = is_custom_pizza;
    }
    
    @Override
    public UUID getId() {
        return id;
    }
    
    public Pizza getPizza() {
        return pizza;
    }
    
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public boolean setQuantity(int quantity) {
        if (quantity < 1){
            return false;
        } else { 
            this.quantity = quantity;
            return true;
        }
    }
    
    public boolean isCustomPizza() {
        return is_custom_pizza;
    }
    
    public double calculateCost() {
        return pizza.getCost() * quantity;
    }
    
    @Override
    public String toString() {
        if (is_custom_pizza) {
            return String.format("%d шт. ", quantity) + pizza.getFullPizzaCompositionString() + "*кастом*";
        }
        return String.format("%d шт. ", quantity) + pizza.getFullPizzaCompositionString();
    }


}
