package util;

import java.util.Comparator;

import model.common.Named;
import model.common.Pricable;
import model.order.Order;

public class Comparators {

    public static <T extends Pricable> Comparator<T> byPrice(boolean reverse) {
        if (!reverse) 
            return Comparator.comparingDouble((T item) -> item.getCost());
        else
            return Comparator.comparingDouble((T item) -> item.getCost()).reversed();
    }
    
    public static <T extends Named> Comparator<T> byName(boolean reverse) {
        if (!reverse) 
            return Comparator.comparing((T item) -> item.getName());
        else
            return Comparator.comparing((T item) -> item.getName()).reversed();
    }
    
    public static Comparator<Order> orderByTime(boolean reverse) {
        if (!reverse) 
            return Comparator.comparing(Order::getOrderTime);
        else
            return Comparator.comparing(Order::getOrderTime).reversed();
    }
    
    public static Comparator<Order> orderByTotalCost(boolean reverse) {
        if (!reverse) 
            return Comparator.comparingDouble(Order::calculateCost);
        else
            return Comparator.comparingDouble(Order::calculateCost).reversed();
    }
}