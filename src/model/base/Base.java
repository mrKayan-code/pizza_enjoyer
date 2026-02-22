package model.base;

import model.common.Product;

public class Base extends Product{
    private static Double CLASSIC_BASE_COST = null;

    public Base(String name, double cost, boolean is_classic) {
        super(name, cost);
        
        if(is_classic) {
            CLASSIC_BASE_COST = cost;
        } else {
            if (!checkCost(cost)) {
                throw new IllegalArgumentException("cost > 1.2*default_base_cost");
            }
        }
    }
    
    public Base(String name, double cost) {
        this(name, cost, false);
    }

    private static boolean checkCost(double cost) {

        if (CLASSIC_BASE_COST == null) {
            throw new IllegalStateException("CLASSIC_BASE_COST == null");
        }

        return cost <= 1.2*CLASSIC_BASE_COST;
    }

    // @Override
    // protected void setCost(double cost) {
    //     if (!checkCost(cost)) {
    //         throw new IllegalArgumentException("cost > 1.2*default_base_cost");
    //     }
    //     super.setCost(cost);
    // }

    @Override
    public String toString() {
        String str = "Основа для пиццы " + super.toString();
        return str;
    }
}