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
        return String.format("%s - %dшт. = %.2fтнг", item.getName(), quantity, getTotalCost());
    }

}
//TODO(мб потом, но пока я не знаю как это заинжектить в структуру ну мне впадлу просто на еще один уровень понижать product)