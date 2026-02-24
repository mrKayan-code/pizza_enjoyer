package view;

import service.ChiefKurban;

public class PizzaBuilderView {
     private final ChiefKurban kitchen;
    private final ConsoleView view;
    
    public PizzaBuilderView(ChiefKurban kitchen, ConsoleView view) {
        this.kitchen = kitchen;
        this.view = view;
    }
}
