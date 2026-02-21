package model.common;

public class Product {
    private String name;
    private double cost;

    Product(String name, double cost){
        this.name = name;
        this.cost = cost;
    }

    public String toString() {
        String str = String.format("%d. %s : %.2f$\n", name, cost);
        return str;
    }
}
