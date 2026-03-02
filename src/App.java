import java.util.Scanner;

import service.ChiefKurban;
import view.MenuHandler;

public class App {  

    public static void main(String[] args) throws Exception {   
        ChiefKurban kitchen = new ChiefKurban();

        MenuHandler mh = new MenuHandler(kitchen, new Scanner(System.in, "cp866"));
        mh.start();
        
    }
}