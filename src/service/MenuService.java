package service;

import dto.Menu;

import java.util.Scanner;

public class MenuService {
    public int getMainMenuCustomerInput(Menu menu, String customerName) {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome " + customerName + " to Main Menu");

        for(String option : menu.getMainMenuOptions()) {
            System.out.println(i + ". " + option);
            i++;
        }

        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
    public int getBuyMenuCustomerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add more item to cart");
        System.out.println("2. Return to Main Menu");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }

    public int getShippingInput(Menu menu) {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        for(String shipping : menu.getCheckoutMenuOptions()) {
            System.out.println(i + ". " + shipping);
            i++;
        }
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
}
