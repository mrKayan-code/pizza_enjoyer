package view;

import service.ChiefKurban;

public class KitchenView {
    private final ChiefKurban kitchen;
    private final ConsoleView view;
    
    public KitchenView(ChiefKurban kitchen, ConsoleView view) {
        this.kitchen = kitchen;
        this.view = view;
    }
}
