package view;

import java.util.List;

import model.common.Product;
import service.ChiefKurban;
import util.FilterUtils;
import util.Comparators;

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
                "Бортики"
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
        showProsuctCatalog(kitchen.getIngredients(), "ИНГРЕДИЕНТЫ", "Ингредиенты: ");
    }

    private void showBases() {
        showProsuctCatalog(kitchen.getBases(), "ОСНОВЫ", "Основы: ");
    }

    private void showSides() {
        showProsuctCatalog(kitchen.getSides(), "БОРТИКИ", "Бортики: ");
    }


    private <T extends Product> void showProsuctCatalog(List<T> original, String header, String message) {
        boolean running = true;
        while (running) {
            view.clear();
            view.printHeader(header);
            
            List<T> copy = List.copyOf(original);
            
            List<String> options = List.of(
                "Искать по названию",
                "Сортировать по цене /",
                "Сортировать по цене \\",
                "Сортировать по названию"
            );
            
            view.printOptions(options);            
            
            String choice = view.readLine();
            
            switch (choice) {
                case "0":
                    String filter = view.readLine("Искать");
                    copy = FilterUtils.filter(copy, ing -> 
                        ing.getName().toLowerCase().contains(filter.toLowerCase())
                    );
                    break;
                case "1":
                    copy = FilterUtils.filterAndSort(copy, i -> true, 
                        Comparators.byPrice(false));
                    break;
                case "2":
                    copy = FilterUtils.filterAndSort(copy, i -> true, 
                        Comparators.byPrice(true));
                    break;
                case "3":
                    copy = FilterUtils.filterAndSort(copy, i -> true, 
                        Comparators.byName(false));
                    break;
                case "4":
                case ":e":
                    running = false;
                    break;
                default:
                    view.printError("Неверная команда");
            }
            
            if (!running) 
                break;
            
            view.println(message);
            view.printList(copy, item -> item.toString());

            view.awaitContinue();
        }
    }
}
