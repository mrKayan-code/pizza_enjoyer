package model.base;

import model.common.Product;

public class Base extends Product{
    private static Double CLASSIC_BASE_COST = null;

    Base(String name, double cost, boolean is_classic) {
        super(name, cost);
        
        if(is_classic) {
            CLASSIC_BASE_COST = cost;
        } else {
            if (!checkCost(cost)) {
                throw new IllegalArgumentException("cost > 1.2*default_base_cost");
            }
        }
    }
    
    private static boolean checkCost(double cost) {
        return cost <= 1.2*CLASSIC_BASE_COST;
    }

    @Override
    protected void setCost(double cost) {
        if (!checkCost(cost)) {
            throw new IllegalArgumentException("cost > 1.2*default_base_cost");
        }
        super.setCost(cost);
    }
}