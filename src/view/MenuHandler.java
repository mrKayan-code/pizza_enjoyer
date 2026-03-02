package view;

import java.util.List;
import java.util.Scanner;

import service.ChiefKurban;

public class MenuHandler {
    private final ConsoleView view;
    private final CatalogView catalogView;
    private final OrderView orderView;

    private boolean running = true;

     public MenuHandler(ChiefKurban kitchen, Scanner scanner) {
        this.view = new ConsoleView(scanner);
        this.catalogView = new CatalogView(kitchen, view);
        this.orderView = new OrderView(kitchen, view);
    }

    public void start() {
        while (running) {
            view.clear();
            view.printHeader("ПИЦЦА ДЖЕКСОН");
            
            List<String> options = List.of(
                "Заказ",
                "Каталог"
            );
            
            view.printOptions(options);
            String choice = view.readLine();
            
            switch (choice) {
                case "0":
                    orderView.startOrdering();
                    break;
                case "1":
                    catalogView.showCatalogMenu();
                    break;
                case ":e":
                    running = false;
                    break;
                default:
                    view.printError("Неверная команда");
                    view.awaitContinue();
            }
        }
    }


    
}
