package service;

import entity.Item;
import entity.Shop;

import java.io.File;
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
}
