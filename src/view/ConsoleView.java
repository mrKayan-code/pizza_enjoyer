package view;

import java.util.List;
import java.util.Scanner;

import model.common.Product;
import model.pizza.Pizza;
import util.Comparators;
import util.FilterUtils;
import model.order.Order;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public void printHeader(String header) {
        println("\n================JACKSON-PIZZA================");
        println(header);
        println("================JACKSON-PIZZA================\n");
    }

    public void printOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            println(String.format("\t[%d] %s", i, options.get(i)));
        }
        println("\t[:e] Выход");
    }

    public <T> void printList(List<T> list, java.util.function.Function<T, String> displayFunc) {
        if (list.isEmpty()) {
            printError("Список пуст");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            println(String.format("  %d. %s", i, displayFunc.apply(t)));
        }
    }

    public void printError(String message) {
        println("ERROR: " + message);
    }
    
    public void printSuccess(String message) {
        println("SUCCES: " + message);
    }

    public String readLine() {
        print(" ->");
        return scanner.nextLine();
    }
    
    public String readLine(String prompt) {
        print(prompt);
        return readLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                println(prompt + ":");
                
                String input = readLine();
                
                return Integer.parseInt(input);
            } catch (Exception e) {
                printError("Неправильное число, снова");
            }
        }
    }
    
    public <T> T selectFromList(String prompt, List<T> items, java.util.function.Function<T, String> displayFunc) {
        if (items.isEmpty()) {
            printError("Список пуст");
            return null;
        }
        
        println(prompt + ":");
        for (int i = 0; i < items.size(); i++) {
            println(String.format("  [%d] %s", i, displayFunc.apply(items.get(i))));
        }
        println("  [:e] Выход");
        
        while (true) {
            String input = readLine("Ваш выбор: ");
            if (input.equals(":e")) return null;
            
            try {
                int index = Integer.parseInt(input);
                if (index >= 0 && index < items.size()) {
                    return items.get(index);
                }
                printError("Неверный индекс");
            } catch (NumberFormatException e) {
                printError("Введите число");
            }
        }
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void awaitContinue() {
        println("\nНажмите что-то для продолжения...");
        readLine();
        clear();
    }
    
    public <T extends Product> void showProductCatalog(List<T> original_catalog, String header, String message) {
        boolean running = true;
        while (running) {
            clear();
            printHeader(header);
            
            List<T> filtered = getFilteredProductCatalog(original_catalog);
            
            if (filtered == null) {
                running = false;
                break;
            }
            
            println(message);
            printList(filtered, item -> item.toString());

            awaitContinue();
        }
    }

    public <T extends Product> List<T> getFilteredProductCatalog(List<T> original_catalog) {
        List<T> copy = List.copyOf(original_catalog);

        List<String> options = List.of(
            "Искать по названию",
            "Сортировать по цене /",
            "Сортировать по цене \\",
            "Сортировать по названию"
        );
        
        printOptions(options);
        println("\t[any] Без сортировки");            
        
        String choice = readLine();
        
        switch (choice) {
            case "0":
                String filter = readLine("Искать");
                copy = FilterUtils.filter(copy, ing -> 
                    ing.getName().toLowerCase().contains(filter.toLowerCase())
                );
                break;
            case "1":
                copy = FilterUtils.filterAndSort(copy, ing -> true, 
                    Comparators.byPrice(false));
                break;
            case "2":
                copy = FilterUtils.filterAndSort(copy, ing -> true, 
                    Comparators.byPrice(true));
                break;
            case "3":
                copy = FilterUtils.filterAndSort(copy, ing -> true, 
                    Comparators.byName(false));
                break;
            case ":e":
                return null;
        }

        return copy;
    }

    public void showPizzaCatalog(List<Pizza> original_catalog, String header, String message) {
        boolean running = true;
        while (running) {
            clear();
            printHeader(header);
            
            List<Pizza> filtered = getFilteredCatalogPizzas(original_catalog);
            
            if (filtered == null) {
                running = false;
                return;
            }

            println(message);
            printList(filtered, item -> item.getFullPizzaCompositionStringForCatalog());

            awaitContinue();

        }
    }

    public List<Pizza> getFilteredCatalogPizzas(List<Pizza> original_catalog) {
        List<Pizza> copy = List.copyOf(original_catalog);
            
        List<String> options = List.of(
            "Искать по названию",
            "Искать по ингредиенту",
            "Сортировать по цене /",
            "Сортировать по цене \\",
            "Сортировать по названию"
        );

        printOptions(options);
        println("\t[any] Без сортировки");          
        
        String choice = readLine();
        String filter;
        
        switch (choice) {
            case "0":
                filter = readLine("Искать");
                copy = FilterUtils.filter(copy, pizza -> 
                    pizza.getName().toLowerCase().contains(filter.toLowerCase())
                );
                break;
            case "1":
                filter = readLine("Искать");
                copy = FilterUtils.filter(copy, pizza -> 
                    pizza.getUniqueIngredients().stream().anyMatch(ing -> ing.getName().toLowerCase().contains(filter.toLowerCase()))
                );
                break;
            case "2":
                copy = FilterUtils.filterAndSort(copy, pizza -> true, 
                    Comparators.byPrice(false));
                break;
            case "3":
                copy = FilterUtils.filterAndSort(copy, pizza -> true, 
                    Comparators.byPrice(true));
                break;
            case "4":
                copy = FilterUtils.filterAndSort(copy, pizza -> true, 
                    Comparators.byName(false));
                break;
            case ":e":
                return null;
        }

        return copy;
    }

    public List<Order> getFilteredOrders(List<Order> original_orders) {
        List<Order> copy = List.copyOf(original_orders);
            
        List<String> options = List.of(
            "Искать по названию пиццы",
            "Сортировать по цене /",
            "Сортировать по цене \\",
            "Сортировать по дате новые",
            "Сортировать по дате старые"
        );

        printOptions(options);
        println("\t[any] Без сортировки");

        String choice = readLine();
        
        switch (choice) {
            case "0":
                String filter = readLine();
                copy = FilterUtils.filter(copy, order -> order.getPositions().stream()
                                                                             .anyMatch(position -> position.getPizza().getName().toLowerCase()
                                                                         .contains(filter.toLowerCase())));
                break;
            case "1":
                copy = FilterUtils.filterAndSort(copy, order -> true, Comparators.orderByTotalCost(false));
                break;
            case "2":
                copy = FilterUtils.filterAndSort(copy, order -> true, Comparators.orderByTotalCost(true));
                break;
            case "3":
                copy = FilterUtils.filterAndSort(copy, order -> true, Comparators.orderByTime(false));
                break;
            case "4":
                copy = FilterUtils.filterAndSort(copy, order -> true, Comparators.orderByTime(true));
                break;
            case ":e":
                return null;
        }

        return copy;
    }

    public void showOrders(List<Order> original_orders, String header, String message) {
        boolean running = true;
        while (running) {
            clear();
            printHeader(header);
            
            List<Order> filtered = getFilteredOrders(original_orders);

            if (filtered == null) {
                running = false;
                return;
            }

            println(message);
            printList(filtered, item -> item.toString());

            awaitContinue();
            
        }
    }

}
