package service;

import Utils.FormatUtils;
import entity.Item;
import entity.Shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShopService extends Service<Shop> {
    ItemService itemService = new ItemService();
    @Override
    public ArrayList<Shop> getAll() {
        ArrayList<Shop> data = null;
        try {
            data = new ArrayList<Shop>();
            File myObj = new File("resource/shops.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String d = myReader.nextLine();
                String[] splitDatas = d.split(",");
                data.add(new Shop(Integer.parseInt(splitDatas[0]), splitDatas[1], Boolean.parseBoolean(splitDatas[2]), Boolean.parseBoolean(splitDatas[3])));
            }
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Shop getById(int id) {
        ArrayList<Shop> shops = this.getAll();

        for(Shop shop : shops) {
            if (shop.getId() == id) {
                return shop;
            }
        }

        return null;
    }
    public void viewItems(Shop shop) {
        System.out.println("Welcome to " + shop.getName() + " items list");
        FormatUtils.printItemHeader();
        for(Item item : itemService.getAllByShop(shop)) {
            FormatUtils.printItem(item.getName(), item.getPrice(), item.getAmount());
        }
        FormatUtils.printItemEnd();
    }
}
