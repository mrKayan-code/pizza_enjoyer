package view;

import java.util.ArrayList;
import java.util.List;

import model.order.Order;
import model.order.OrderItem;
import service.ChiefKurban;
import model.pizza.Pizza;

public class OrderView {
    private final ChiefKurban kitchen;
    private final ConsoleView view;
    private final PizzaBuilderView pizzaBuilder;
    
    public OrderView(ChiefKurban kitchen, ConsoleView view) {
        this.kitchen = kitchen;
        this.view = view;
        this.pizzaBuilder = new PizzaBuilderView(kitchen, view);
    }

    public void startOrdering() {
        Order order = kitchen.createOrder();

        boolean choosing = true;
        while (choosing) {
            view.clear();
            view.printHeader("ЗАКАЗАТЬ");
            
            List<String> options = List.of(
                "Из каталога",
                "Комбинированная",
                "Кастом",
                "Сформировать заказ",
                "Предпросмотр заказа"
            );
            
            view.printOptions(options);
            String choice = view.readLine();

            switch (choice) {
                case "0":
                    Pizza ordered_from_catalog = selectPizzaFromCatalog();
                    if (ordered_from_catalog != null) {
                        int quantity = view.readInt("Сколько заказать? (количество)");
                        OrderItem position = new OrderItem(ordered_from_catalog, quantity);
                        order.addPosition(position);
                    }
                    break;
                case "1":
                    
                    break;
                case "2":
                    
                    break;
                case "3":
                    
                    break;
                case "4":
                    
                case ":e":
                    choosing = false;
                    break;
                default:
                    break;
            }

            if (!choosing)
                break;

            view.awaitContinue();
        }
    }

    private Pizza selectPizzaFromCatalog() {
        view.printHeader("ВЫБОР ПИЦЦЫ ИЗ КАТАЛОГА");
        List<Pizza> catalog = view.getFilteredCatalogPizzas(kitchen.getPizzas());

        if (catalog == null) {
            view.printError("Выбрать пиццу не удалось");
            return null;
        }

        return view.selectFromList("Каталог", catalog, pizza -> pizza.getFullPizzaCompositionString());
    }
}


//  я все завайбкодил клянус островом эпштейна!!!!!
