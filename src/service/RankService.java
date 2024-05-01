package service;

import dto.VoucherList;
import entity.Customer;
import entity.Rank;
import entity.Rank;
import entity.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RankService extends Service<Rank> {
    public ShopService shopService = new ShopService();
    public VoucherListService voucherListService = new VoucherListService();
    @Override
    public ArrayList<Rank> getAllByShop(Shop shop) {
        try {
            File myFile = new File("resource/Shop_" + shop.getId() + "/rank.txt");
            if(!myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                ArrayList<Rank> rankList = new ArrayList<>();
                while(sc.hasNextLine()) {
                    String rankInfo = sc.nextLine();
                    if(!rankInfo.isEmpty()) {
                        String[] rankInfoSplited = rankInfo.split(",");
                        rankList.add(new Rank(rankInfoSplited[0], Float.parseFloat(rankInfoSplited[1])));
                    }
                }
                return rankList;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Rank classifyRank(Customer customer) {
        Shop shop = shopService.getById(customer.getShopId());
        ArrayList<Rank> rankList = this.getAllByShop(shop);

        Rank r = null;

        for(Rank rank : rankList) {
            if(customer.getLoyaltyPoint() >= rank.getRequiredPoint()) {
                r = rank;
            }
        }

        return r;
    }
    public void viewRank(Customer customer) {
        VoucherList voucherList = voucherListService.getAllVoucherByShop(shopService.getById(customer.getShopId()), classifyRank(customer));

        Rank r = classifyRank(customer);
        System.out.println("\nYour rank is " + r.getName() + " with " + customer.getLoyaltyPoint() + " loyalty points.");
        System.out.println("Benefit for " + r.getName() + " customer are listed below: ");

        voucherListService.showAllVoucher(voucherList);
    }
}
