package view;

import java.util.List;
import java.util.Scanner;

import model.ingredients.Ingredient;

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
                
                if (input.equals(":e")) 
                    return -1;
                
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printError("Неправильное число, снова");
            }
        }
    }
    
    public double readDouble(String prompt) {
        while (true) {
            try {
                println(prompt + ":");
                
                String input = readLine();
                
                if (input.equals(":e")) 
                    return -1;
                
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
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

}
