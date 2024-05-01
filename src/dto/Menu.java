package dto;

import entity.Shipping;
import entity.Shop;
import service.ShippingService;

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
        ShippingService shippingService = new ShippingService();

        mainMenuOptions.add("View Cart");
        if(shop.isHasUserRank()) {
            mainMenuOptions.add("View Rank");
        }
        mainMenuOptions.add("View Item");
        mainMenuOptions.add("Exit");

        if(shop.isHasShippingMethod()) {
            ArrayList<Shipping> shippings = shippingService.getAllByShop(shop);
            for(Shipping shipping : shippings) {
                checkoutMenuOptions.add(shipping.getName());
            }
        }
        else {
            checkoutMenuOptions.add("Go to summarization");
        }
        checkoutMenuOptions.add("Back to Main Menu");
    }
}
