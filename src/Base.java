
public class Base {
    String name;
    double cost;

    Base(String name) {
        this.name = name;
    }

    Base(String name, double cost, double default_base_cost) {
        this.name = name;
        boolean setcost_success = this.setCost(cost, default_base_cost);
        if(!setcost_success) {
            throw new IllegalArgumentException("cost > 1.2*default_base_cost");
        }
    }
    
    private static boolean checkCost(double cost, double default_base_cost) {
        return cost <= 1.2*default_base_cost;
    }

    public boolean setCost(double cost, double default_base_cost) {
        if (checkCost(cost, default_base_cost)) {
            this.cost = cost;
            return true;
        } else {
            return false;
        }
    }
}