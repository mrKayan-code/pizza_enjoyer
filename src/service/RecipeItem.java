package service;

import model.common.Named;
import model.common.Pricable;

public class RecipeItem <T extends Named & Pricable> {
    private final T item;
    private int quantity;

    public RecipeItem(T item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public T getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return item.getCost() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - %dшт. = %.2f$", item.getName(), quantity, getTotalCost());
    }

}
