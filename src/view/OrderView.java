package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.order.Order;
import model.order.OrderItem;
import service.ChiefKurban;
import model.pizza.Pizza;
import model.side.Side;
import model.pizza.Size;
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
        Order order = new Order();

        boolean choosing = true;
        while (choosing) {
            view.clear();
            view.printHeader("ЗАКАЗАТЬ");
            
            List<String> options = List.of(
                "Из каталога",
                "Комбинированная",
                "Кастом",                
                "Предпросмотр заказа",
                "Отложить",
                "Сформировать заказ"
            );
            
            view.printOptions(options);
            String choice = view.readLine();

            int quantity;
            LocalDate scheduled_day = null;
            switch (choice) {
                case "0":
                    Pizza ordered_from_catalog = selectPizzaFromCatalog();
                    if (ordered_from_catalog == null) {
                        break;
                    }

                    boolean is_double = false;

                    is_double = view.readBoolean("Удвоить ингредиенты?");
                    
                    quantity = view.readInt("Сколько заказать? (количество)");
                    if (quantity > 0) {
                        OrderItem position = kitchen.createOrderItemFromCatalog(ordered_from_catalog, quantity, is_double);
                        order.addPosition(position);
                    } else {
                        view.printError("позиция в заказ не добавлена");
                    }
                    break;
                case "1":
                    Pizza ordered_combi = pizzaBuilder.createCombined();
                    if (ordered_combi == null) {
                        break;
                    }
                    quantity = view.readInt("Сколько заказать? (количество)");
                    if (quantity > 0) {
                        OrderItem position = new OrderItem(ordered_combi, quantity, true);
                        order.addPosition(position);
                    } else {
                        view.printError("позиция в заказ не добавлена");
                    }
                    break;
                case "2":
                    Pizza ordered_custom = pizzaBuilder.createCustom();
                    if (ordered_custom == null) {
                        break;
                    }
                    quantity = view.readInt("Сколько заказать? (количество)");
                    if (quantity > 0) {
                        OrderItem position = new OrderItem(ordered_custom, quantity, true);
                        order.addPosition(position);
                    } else {
                        view.printError("позиция в заказ не добавлена");
                    }
                    break;
                case "3":
                    view.println(order.toString());
                    break;
                case "4":
                    scheduled_day = view.readDate("Дата доставки");

                    if (scheduled_day != null && scheduled_day.atStartOfDay().isAfter(order.getOrderTime())) {
                        order.setScheduleTime(scheduled_day.atStartOfDay());
                    } else {
                        view.printError("Заказ не отложен");
                    }
                    break;
                case "5":
                    boolean is_payed = payOrder(order);
                    if (is_payed) {
                        kitchen.addOrder(order);
                    } else {
                        view.printError("Оплатить не удалось");
                        view.printError("Заказ отменен");
                        view.awaitContinue();
                    }
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

        Pizza ordering =  view.selectFromList("Каталог", catalog, pizza -> pizza.getFullPizzaCompositionStringForCatalog());

        
        if (ordering != null) {
            if (view.readBoolean("Добавить/изменить бортик?")) {
                Side side = selectSide(ordering);
                if (side != null) {
                    ordering.setSide(side);
                }
            }
        } else {
            return null;
        }

        view.println("Выберите размер");
        
        List<Pizza> pizzas_sizes = new ArrayList<>();

        for (Size size : Size.values()) {
            pizzas_sizes.add(
                ordering.getCopyWithSize(size)
            );
        }


        return view.selectFromList("Каталог", pizzas_sizes, pizza -> pizza.getFullPizzaCompositionString());
    }

    private Side selectSide(Pizza ordering) {
        List<Side> sides = kitchen.getPossibleSides(ordering);
        
        return view.selectFromList("Каталог", sides, side -> side.toString());
    }

    private boolean payOrder(Order order) {
        view.clear();
        view.printHeader("ОПЛАТА");
        view.println(order.toString());

        return view.readBoolean("Оплатить?");
    }

}
