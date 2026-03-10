package view;

import service.ChiefKurban;

import java.util.List;

import model.base.Base;
import model.ingredients.Ingredient;
import model.pizza.Pizza;
import model.pizza.Size;
import model.side.Side;
public class PizzaBuilderView {
     private final ChiefKurban kitchen;
    private final ConsoleView view;
    
    public PizzaBuilderView(ChiefKurban kitchen, ConsoleView view) {
        this.kitchen = kitchen;
        this.view = view;
    }

    public Pizza createCombined() {
        view.printHeader("КОМБИНИРОВАНИЕ");
    
        view.println("Основы");
        Base base = view.selectFromList("Выберите основу", view.getFilteredProductCatalog(kitchen.getBases()), bas -> bas.toString());
        if (base == null) {
            return null;
        }

        Size size = view.selectFromList("Выберите размер", 
            List.of(Size.values()), 
            s -> String.format("%s (%d кусков)", s.getName(), s.getSlicesCount()));
        if (size == null) {
            return null;
        }

        Pizza f;
        Pizza s;

        view.printHeader("1Я ПОЛОВИНА");
        
        List<Pizza> filtered = view.getFilteredCatalogPizzas(kitchen.getPizzas());
        
        if (filtered == null) {
            view.printError("Выбрать пиццу не удалось");
            view.awaitContinue();
            return null;
        }

        view.println("Выберите первую половину");
        f =  view.selectFromList("Каталог", filtered, pizza -> pizza.getFullPizzaCompositionStringForCatalog());

        if (f == null) {
            return null;
        }

        view.printHeader("2Я ПОЛОВИНА");
        
        filtered = view.getFilteredCatalogPizzas(kitchen.getPizzas());
        
        if (filtered == null) {
            view.printError("Выбрать пиццу не удалось");
            view.awaitContinue();
            return null;
        }

        view.println("Выберите вторую половину");
        s =  view.selectFromList("Каталог", filtered, pizza -> pizza.getFullPizzaCompositionStringForCatalog());

        if (s == null) {
            return null;
        }

        Pizza combi = Pizza.createHalfCombined(base, size, f, s);

        Side side;

        if (view.readBoolean("Добавить/изменить бортик?") ) {
            side = view.selectFromList("Каталог", kitchen.getSides(), sid -> sid.toString());
            if (side != null) {
                combi.setSide(side);
            }
        }
        
        return combi;
    }

    public Pizza createCustom() {
        view.printHeader("КАСТОМ");
        
        String name = view.readLine("Название твоей пиццы");

        view.println("Основы");
        Base base = view.selectFromList("Каталог", view.getFilteredProductCatalog(kitchen.getBases()), bas -> bas.toString());
        if (base == null) {
            return null;
        }

        Size size = view.selectFromList("Выберите размер", 
            List.of(Size.values()), 
            s -> String.format("%s (%d кусков)", s.getName(), s.getSlicesCount()));
        if (size == null) {
            return null;
        }

        Pizza pizza = new Pizza(name, base, size);

        // List<String> options = List.of(
        //     "Цельная",    
        //     "Покусочно"
        // );
        
        // view.printOptions(options);
        // String choice = view.readLine();

        // boolean is_uniform;
        

        boolean choosing_ingrs = true;
        while ( choosing_ingrs) {
            List<String> options = List.of(
                "Добавить ингредиент",
                "Показать что выбрано",
                "Завершить выбор ингредиентов"
            );

            view.printOptions(options);
            String choice = view.readLine();

            switch (choice) {
                case "0":
                    chooseAndAddIngredient(pizza);
                    break;
                case "1":
                    view.println(pizza.getFullPizzaCompositionString());
                case "2" :
                    choosing_ingrs = false;
                    break;
                case ":e":
                    return null;
                default:
                    break;
            }
        }

        if (view.readBoolean("Добавить бортик?")) {
            Side side = view.selectFromList("Каталог", kitchen.getSides(), sid -> sid.toString());
            if (side != null) {
                pizza.setSide(side);
            }
        }

        return pizza;
    }
    
    private boolean chooseAndAddIngredient(Pizza pizza) {
        
        view.println("Ингредиенты");
        Ingredient ingredient = view.selectFromList("Каталог", view.getFilteredProductCatalog(kitchen.getIngredients()), ing -> ing.toString());
        
        List<String> options = List.of(
            "Добавить как к цельной пицце",    
            "Добавить к куску/диапазону"
        );

        view.printOptions(options);
        String choice = view.readLine();

        switch (choice) {
            case "0":
                pizza.addIngredientLikeUniform(ingredient);
                break;
            case "1" :
                String response = view.readLine(String.format("Введите к какому куску/диапазону кусков применить <1 до %d>/ <1 до %d - 1 до %d>", pizza.getSize().getSlicesCount() + 1, pizza.getSize().getSlicesCount() + 1, pizza.getSize().getSlicesCount() + 1));
                if (response.contains("-")) {
                    try {
                        view.printError(response);
                        response = response.replaceAll(" ", "");
                        view.printError(response.substring(0, response.indexOf('-')));
                        view.printError(response.substring(response.indexOf('-') + 1, response.length()));
                        int a = Integer.parseInt(response.substring(0, response.indexOf('-')));
                        int b = Integer.parseInt(response.substring(response.indexOf('-') + 1, response.length()));
                        for (int i = Math.min(a, b); i < Math.max(a, b); i++) {
                            pizza.addIngredientToSlice(ingredient, i - 1);
                        }
                    } catch (Exception e) {
                        view.printError("Не получилось добавить ингредиент");
                        return false;
                    }
                }
                break;
            default:
                view.printError("Не получилось добавить ингредиент");
                return false;
        }
        return true;
    }
}
