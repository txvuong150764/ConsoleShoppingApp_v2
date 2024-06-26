package service;

import dto.Cart;
import entity.Item;
import entity.Shop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemService extends Service<Item> {
    @Override
    public ArrayList<Item> getAllByShop(Shop shop) {
        try {
            File myFile = new File("resource/Shop_" + shop.getId() + "/items.txt");
            if(!myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                ArrayList<Item> itemList = new ArrayList<>();
                while(sc.hasNextLine()) {
                    String itemInfo = sc.nextLine();
                    if(!itemInfo.isEmpty()) {
                        String[] itemInfoSplited = itemInfo.split(",", 3);
                        itemList.add(new Item(itemInfoSplited[0], Float.parseFloat(itemInfoSplited[1]), Integer.parseInt(itemInfoSplited[2])));
                    }
                }
                return itemList;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Item getItemInCartByName(String name, Cart cart) {
        for(Item i : cart.getItems()) {
            if(i.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return null;
    }

    public void write(Item item, Shop shop) {
        try {
            FileWriter writer = new FileWriter("resource/Shop_" + shop.getId() + "/items.txt", true);
            String itemInfo = item.getName() + "," + item.getPrice() + "," + item.getAmount() + "\n";
            writer.write(itemInfo);
            writer.close();;
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    public void clearItemFile(Shop shop){
        try {
            FileWriter writer = new FileWriter("resource/Shop_" + shop.getId() + "/items.txt");
            writer.write("");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
