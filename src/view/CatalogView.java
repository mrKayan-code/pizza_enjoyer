package view;

import java.util.List;

import service.ChiefKurban;

public class CatalogView {
    private final ChiefKurban kitchen;
    private final ConsoleView view;

    public CatalogView(ChiefKurban kitchen, ConsoleView view) {
        this.kitchen = kitchen;
        this.view = view;
    }

    public void showCatalogMenu() {
        boolean running = true;
        while (running) {
            view.clear();
            view.printHeader("КАТАЛОГ");
            
            List<String> options = List.of(
                "Ингредиенты",
                "Основы для пиццы",
                "Бортики",
                "Пиццы"
            );
            
            view.printOptions(options);
            String choice = view.readLine();
            
            switch (choice) {
                case "0":
                    showIngredients();
                    break;
                case "1":
                    showBases();
                    break;
                case "2":
                    showSides();
                    break;
                case "3":
                    showPizzas();
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

    private void showIngredients() {
        view.showProductCatalog(kitchen.getIngredients(), "ИНГРЕДИЕНТЫ", "Ингредиенты: ");
    }

    private void showBases() {
        view.showProductCatalog(kitchen.getBases(), "ОСНОВЫ", "Основы: ");
    }

    private void showSides() {
        view.showProductCatalog(kitchen.getSides(), "БОРТИКИ", "Бортики: ");
    }

    private void showPizzas() {
        view.showPizzaCatalog(kitchen.getPizzas(), "ПИЦЦЫ", "Пиццы: ");
    }
}
