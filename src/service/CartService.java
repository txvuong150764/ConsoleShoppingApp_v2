package service;

import dto.Cart;
import entity.Item;

import java.util.ArrayList;
import Utils.FormatUtils;
public class CartService {
    public ArrayList<Item> readShoppingCart(String itemList) {
        ArrayList<Item> shoppingCart = new ArrayList<>();

        if(itemList.trim().isEmpty()) {
            return shoppingCart;
        }

        String[] items = itemList.split("\\|");
        for (String item : items) {
            String itemName = item.split(":")[0].trim();
            float price = Float.parseFloat(item.split(":")[1].trim());
            int amount = Integer.parseInt(item.split(":")[2].trim());
            Item i = new Item(itemName, price, amount);
            shoppingCart.add(i);
        }

        return shoppingCart;
    }

    public void viewCart(Cart cart) {
        FormatUtils.printItemHeader();
        for(Item item : cart.getItems()) {
            FormatUtils.printItem(item.getName(), item.getPrice(), item.getAmount());
        }
        FormatUtils.printItemEnd();
        System.out.println("Total: " + cart.getTotal());
    }

    public void updateCart(Cart cart, Item item, int amount) {
        cart.setTotal(cart.getTotal() + item.getPrice() * amount);
        for(Item i : cart.getItems()) {
            if(i.getName().equalsIgnoreCase(item.getName())) {
                item.setAmount(i.getAmount() + amount);
                return;
            }
        }
        cart.getItems().add(new Item(item.getName(), item.getPrice(), amount));
    }
}
