import java.util.ArrayList;

public class Order {
    //double id;
    Pizza pizza;
    double total_cost;
    boolean payed;

    Order(Pizza pizza) {
        this.pizza = pizza;
        total_cost = pizza.calculateCost(total_cost);
        payed = false;
    }

    public void printCheck() {
        System.out.println("чек по заказу");

        System.out.printf("база\t стоимость\n");
        System.out.printf("%s\t %.2f$\n", pizza.base.name, pizza.base.cost);
        System.out.println();

        System.out.printf("ингредиент\t стоимость\n");
        for (Ingredient ingredient : pizza.ingredients) {
            System.out.printf("%s\t %.2f$\n", ingredient.name, ingredient.cost);
        }
        System.out.println();
        
        System.out.printf("общая стоимость:\t%.2f$\n", total_cost);
        System.out.println();
    }

    public void pay() {
        payed = true;
    }

}
