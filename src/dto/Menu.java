package dto;

import entity.Shop;

import java.util.ArrayList;

public class Menu {
    ArrayList<String> mainMenuOptions = new ArrayList<>();
    ArrayList<String> checkoutMenuOptions = new ArrayList<>();

    public ArrayList<String> getMainMenuOptions() {
        return mainMenuOptions;
    }

    public ArrayList<String> getCheckoutMenuOptions() {
        return checkoutMenuOptions;
    }

    public Menu(Shop shop) {
        mainMenuOptions.add("View Cart");
        if(shop.isHasUserRank()) {
            mainMenuOptions.add("View Rank");
        }
        mainMenuOptions.add("View Item");
        mainMenuOptions.add("Exit");
        if(shop.isHasShippingMethod()) {
            checkoutMenuOptions.add("Choose Shipping Method");
        }
    }
}
