package model.common;

import java.util.UUID;

public abstract class Product implements Identifiable, Pricable, Named{
    private final UUID id = UUID.randomUUID();
    private String name;
    private double cost;

    protected Product(String name, double cost){
        this.name = name;
        this.cost = cost;
        // setCost(cost); //TODO(пока костыль)
    }
    
    @Override public UUID getId() { 
        return id; 
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getCost() {
        return cost;
    }

    protected void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        String str = String.format("%s : %.2f$\n", name, cost);
        return str;
    }

}
