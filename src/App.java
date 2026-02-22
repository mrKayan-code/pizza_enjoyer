import java.util.ArrayList;
// import java.util.HashMap;
import java.util.Scanner;



import model.base.Base;
import model.ingredients.Ingredient;
import model.order.Order;
import model.pizza.Pizza;

public class App {  

    public static void main(String[] args) throws Exception {

        
    }

    // public static void main(String[] args) throws Exception {
    //     ChiefKurban kitchen = new ChiefKurban();
    //     Scanner scanner = new Scanner(System.in, "cp866");

    //     String user_input;


    //     String[] commands = new String[] {"купить пиццу", "редактировать кухню"};
    //     CommandsHandler handler = new CommandsHandler(commands);        

    //     boolean running = true;
    //     while (running) {
    //         System.out.println("0");
    //         System.out.println(":e -> выход");
    //         handler.showCommands();

    //         user_input = scanner.next();

    //         switch (user_input) {
    //             case ":e":
    //                 running = false;
    //                 break;
    //             case "0":
    //                 System.out.print("\033[H\033[2J");
    //                 kitchen.buyPizza(scanner);
    //                 break;
    //             case "1":
    //                 System.out.print("\033[H\033[2J");
    //                 kitchen.editProducts(scanner);
    //                 break;
    //             default:
    //                 System.out.print("\033[H\033[2J");
    //                 break;
    //         }            
    //     }
    //     scanner.close();
    // }      
}

// class ChiefKurban {
//     ArrayList<Base> bases = new ArrayList<>();
//     ArrayList<Ingredient> ingredients = new ArrayList<>();

//     ArrayList<Order> orders = new ArrayList<>();


//     ChiefKurban() {
//         this.addIngredient(new Ingredient("Конина", 100));
//         this.addIngredient(new Ingredient("Помидоры", 150));
//         this.addIngredient(new Ingredient("Перец халапеньо", 50));

//         this.addBase(new Base("Классическая", 100, 100));
//         this.addBase(new Base("Черная", 110, 100));
//     }

//     private boolean addIngredient(Ingredient ingredient) {
//         ingredients.add(ingredient);
//         return true;
//     }

//     private boolean addBase(Base base) {
//         bases.add(base);
//         return true;
//     }
    
//     public void showIngredientsList() {
//         for (int i = 0; i < ingredients.size(); i++) {
//             System.out.printf("%d. %s : %.2f$\n", i, ingredients.get(i).name, ingredients.get(i).cost);
//         }
//     }

//     public void showBasesList() {
//         for (int i = 0; i < bases.size(); i++) {
//             System.out.printf("%d. %s : %.2f$\n", i, bases.get(i).name, bases.get(i).cost);
//         }
//     }

//     public void editProducts(Scanner scanner) {
//         String user_input;
//         String[] commands = new String[] {"вывести список ингредиентов", "вывести список баз", "создать новые ингредиенты", "создать новые базы"};
//         CommandsHandler handler = new CommandsHandler(commands);

//         boolean running = true;
//         while (running) {
//             System.out.println("Редактировать продукты");
//             System.out.println(":e -> выход");
//             handler.showCommands();

//             user_input = scanner.next();

//             switch (user_input) {
//                 case ":e":
//                     running = false;
//                     System.out.print("\033[H\033[2J");
//                     break;
//                 case "0":
//                     System.out.print("\033[H\033[2J");
//                     this.showIngredientsList();
//                     break;
//                 case "1":
//                     System.out.print("\033[H\033[2J");
//                     this.showBasesList();
//                     break;
//                 case "2":
//                     System.out.print("\033[H\033[2J");
//                     this.makeIngredients(scanner);
//                     break;
//                 case "3":
//                     System.out.print("\033[H\033[2J");
//                     this.makeBases(scanner);
//                     break;
//                 default:
//                     System.out.print("\033[H\033[2J");
//                     break;
//             }            
//         }
//     }

//     public void makeIngredients(Scanner scanner) {
//         String user_input;

//         boolean running = true;
//         while (running) {
//             System.out.println("Ингредиенты в системе:");
//             this.showIngredientsList();

//             System.out.println("Создать новый ингредиент");
//             System.out.println(":e -> выход");

//             System.out.println("введите название");            
//             user_input = scanner.next();

//             if (user_input.equals(":e")) {
//                 running = false;
//                 System.out.print("\033[H\033[2J");
//                 break;
//             }

//             String name = user_input;
//             // проверка на уже сущ

//             System.out.println("введите стоимость");            
//             user_input = scanner.next();
            
//             if (user_input.equals(":e")) {
//                 running = false;
//                 System.out.print("\033[H\033[2J");
//                 break;
//             }

//             double cost;
//             try {
//                 cost = Double.parseDouble(user_input);
//             } catch (Exception e) {
//                 System.out.print("\033[H\033[2J");
//                 System.out.println("Ошибка при вводе числа");
//                 continue;
//             }

//             Ingredient ingredient = new Ingredient(name, cost);
//             this.addIngredient(ingredient);
//             System.out.print("\033[H\033[2J");
//             System.out.printf("успешно создан игредиент %s : %.2f\n", name, cost);
//         }


//     }

//     public void makeBases(Scanner scanner) {
//         String user_input;

//         boolean running = true;
//         while (running) {
//             System.out.println("Базы в системе:");
//             this.showBasesList();

//             System.out.println("Создать новую базу");
//             System.out.println(":e -> выход");

//             System.out.println("введите название");            
//             user_input = scanner.next();

//             if (user_input.equals(":e")) {
//                 running = false;
//                 System.out.print("\033[H\033[2J");
//                 break;
//             }

//             String name = user_input;
//             // проверка на уже сущ

//             System.out.println("введите стоимость");            
//             user_input = scanner.next();
            
//             if (user_input.equals(":e")) {
//                 running = false;
//                 System.out.print("\033[H\033[2J");
//                 break;
//             }

//             double cost;
//             try {
//                 cost = Double.parseDouble(user_input);
//             } catch (Exception e) {
//                 System.out.print("\033[H\033[2J");
//                 System.out.println("Ошибка при вводе числа");
//                 continue;
//             }

//             try {
//                 Base base = new Base(name, cost, this.bases.getFirst().cost);
//                 this.addBase(base);
//                 System.out.print("\033[H\033[2J");
//                 System.out.printf("успешно создана база %s : %.2f\n", name, cost);
//             } catch (Exception e) {
//                 System.out.print("\033[H\033[2J");
//                 System.out.println(e.getMessage());
//             }
//         }
//     }
    
//     public void buyPizza(Scanner scanner) {
//         Order order = makeOrder(scanner);

//         if (order == null) {
//             return;
//         } else {
//             payOrder(scanner, order);
//         }
//     }

//     public Order makeOrder(Scanner scanner) {
//         String user_input = "";
        
//         boolean is_base_choosed = false;
//         boolean is_ingredients_choosed = false;
//         boolean running = true;

//         Order order = null;
//         Pizza pizza = null;
//         // Base base = null;
//         // ArrayList<Ingredient> ingredients = new ArrayList<>();
//         while ( running) {
//             System.out.println("Заказ");
//             System.out.println(":e -> выход");
//             System.out.println(":b -> поменять базу");
//             System.out.println(":i -> поменять ингредиенты");

//             if(pizza != null && !(is_base_choosed && is_ingredients_choosed)) {
//                 System.out.printf("Выбранная база: %s : %.2f$\n", pizza.base.name, pizza.base.cost);

//                 if (pizza.ingredients.size() > 0) {
//                     for (Ingredient ingredient : pizza.ingredients) {
//                         System.out.printf("Выбранный ингредиент: %s : %.2f$\n", ingredient.name, ingredient.cost);
//                     }
//                 }

//                 double total_cost = pizza.calculateCost(0);
//                 System.out.printf("стоимость: %.2f$\n", total_cost);
//             }

//             if(!is_base_choosed || user_input.equals(":b")) {
//                 this.showBasesList();
//                 System.out.println("Выберите базу");

//                 user_input = scanner.next();
                
//                 if (user_input.equals(":e")) {
//                     running = false;
//                     System.out.print("\033[H\033[2J");
//                     break;
//                 } else if (user_input.charAt(0) == ':') {
//                     System.out.print("\033[H\033[2J");
//                     continue;
//                 }

//                 int user_input_num;
//                 try {
//                     user_input_num = Integer.parseInt(user_input);
//                 } catch (Exception e) {
//                     System.out.print("\033[H\033[2J");
//                     System.out.println("Ошибка при вводе числа");
//                     continue;
//                 }
                
//                 try {
//                     Base base = this.bases.get(user_input_num);

//                     if(pizza == null) {
//                         pizza = new Pizza(base);
//                     } else {
//                         pizza.base = base; // потом чутка допилить
//                     } 
                    
//                     is_base_choosed = true;
//                     System.out.print("\033[H\033[2J");
//                     System.out.printf("Добавлена %s : %.2f$\n", base.name, base.cost);
//                     continue;

//                 } catch (Exception e) {
//                     System.out.print("\033[H\033[2J");
//                     System.out.println("Не удалось получить такой элемент");
//                     continue;
//                 }
//             }

//             if (!is_ingredients_choosed || user_input.equals(":i")) {
//                 if (user_input.equals( ":y")) {
//                     if (pizza.ingredients.size() == 0) {
//                         System.out.print("\033[H\033[2J");
//                         System.out.println("Выбрано 0 ингредиентов");
//                         continue;
//                     } else {
//                         is_ingredients_choosed = true;
//                         System.out.print("\033[H\033[2J");
//                         System.out.println("Успешно");
//                         continue;
//                     }
//                 }
                
//                 System.out.println(":y -> завершить выбор");
//                 this.showIngredientsList();
//                 System.out.println("Выберите ингредиенты");

//                 user_input = scanner.next();

//                 if (user_input.equals(":e")) {
//                     running = false;
//                     System.out.print("\033[H\033[2J");
//                     break;
//                 } else if (user_input.charAt(0) == ':') {
//                     System.out.print("\033[H\033[2J");
//                     continue;
//                 }               

//                 int user_input_num;
//                 try {
//                     user_input_num = Integer.parseInt(user_input);
//                 } catch (Exception e) {
//                     System.out.print("\033[H\033[2J");
//                     System.out.println("Ошибка при вводе числа");
//                     continue;
//                 }

//                 try {
//                     System.out.print("\033[H\033[2J");
//                     Ingredient ingredient = this.ingredients.get(user_input_num);
//                     pizza.addIngredient(ingredient);
//                     System.out.printf("Добавлен %s : %.2f$\n", ingredient.name, ingredient.cost);
//                 } catch (Exception e) {
//                     System.out.print("\033[H\033[2J");
//                     System.out.println("Не удалось получить такой элемент");
//                     continue;
//                 }

//             }

//             if (is_base_choosed && is_ingredients_choosed) {
//                 System.out.printf("база: %s : %.2f$\n", pizza.base.name, pizza.base.cost);

//                 System.out.println("Ингредиенты:");
//                 if (pizza.ingredients.size() > 0) {
//                     for (Ingredient ingredient : pizza.ingredients) {
//                         System.out.printf("%s: %.2f$\n", ingredient.name, ingredient.cost);
//                     }
//                 }

//                 double total_cost = pizza.calculateCost(0);
//                 System.out.printf("стоимость: %.2f$\n", total_cost);

//                 System.out.println(":y -> заказать");
                
//                 user_input = scanner.next();

//                 switch (user_input) {
//                     case ":e":
//                         running = false;
//                         System.out.print("\033[H\033[2J");
//                         break;
//                     case ":y":
//                         order = new Order(pizza);
//                         orders.add(order);
//                         running = false;
//                         System.out.print("\033[H\033[2J");
//                         break;
//                     case ":b":
//                         System.out.print("\033[H\033[2J");
//                         break;
//                     case ":i":
//                         System.out.print("\033[H\033[2J");
//                         break;
//                     default:
//                         System.out.print("\033[H\033[2J");
//                         break;
//                 }
//             }            
//         }
//         return order;
//     }

//     public void payOrder(Scanner scanner, Order order) {
//         String user_input = "";
//         boolean running = true;

//         while (running) {
//             order.printCheck();
//             System.out.println("+7-923-435-11-12, Тбанк, Михаил Н.");
//             System.out.println(":e -> выход");
//             System.out.println(":y -> подтвердить оплату");

//             user_input = scanner.next();

//             switch (user_input) {
//                 case ":e":
//                     running = false;
//                     System.out.print("\033[H\033[2J");                
//                     break;
//                 case ":y":
//                     running = false;
//                     System.out.print("\033[H\033[2J");     
//                     int order_idx = orders.indexOf(order);
//                     orders.get(order_idx).pay(); 
//                     System.out.println("Оплачено");          
//                     break;
//                 default:
//                     break;
//             }

//         }
        
//     }

//     // private static Pizza makePizza(Base base, ArrayList<Ingredient> ingredients) {
//     //     Pizza pizza = new Pizza(base);

//     //     for (Ingredient ingredient : ingredients) {
//     //         pizza.addIngredient(ingredient);
//     //     }

//     //     return pizza;
//     // }
    
// }

// class CommandsHandler {
//     String[] commands;
    
//     CommandsHandler(String[] commands) {
//         this.commands = commands;
//     }

//     public void showCommands() {
//         for (int i = 0; i < commands.length; i++) {
//             System.out.printf("%d -> %s\n", i, commands[i]);
//         }
//     }
// }
