package view;

import service.ChiefKurban;

public class OrderView {
    private final ChiefKurban kitchen;
    private final ConsoleView view;
    private final PizzaBuilderView pizzaBuilder;
    
    public OrderView(ChiefKurban kitchen, ConsoleView view) {
        this.kitchen = kitchen;
        this.view = view;
        this.pizzaBuilder = new PizzaBuilderView(kitchen, view);
    }
}
