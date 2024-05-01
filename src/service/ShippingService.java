package service;

import entity.Shipping;
import entity.Shipping;
import entity.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShippingService extends Service<Shipping> {
    @Override
    public ArrayList<Shipping> getAllByShop(Shop shop) {
        try {
            File myFile = new File("resource/Shop_" + shop.getId() + "/shipping_methods.txt");
            if(!myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                ArrayList<Shipping> shippingList = new ArrayList<>();
                while(sc.hasNextLine()) {
                    String ShippingInfo = sc.nextLine();
                    if(!ShippingInfo.isEmpty()) {
                        String[] shippingInfoSplited = ShippingInfo.split(",");
                        shippingList.add(new Shipping(Integer.parseInt(shippingInfoSplited[0]), shippingInfoSplited[1], Integer.parseInt(shippingInfoSplited[2]), Float.parseFloat(shippingInfoSplited[3])));
                    }
                }
                return shippingList;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
