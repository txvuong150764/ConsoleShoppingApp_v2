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
        System.out.println("1. Buy");
        System.out.println("2. Return to Main Menu");
        System.out.print("Please enter your option: ");
        return sc.nextInt();
    }
}
