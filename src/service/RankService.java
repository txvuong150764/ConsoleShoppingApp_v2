package service;

import entity.Rank;
import entity.Rank;
import entity.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RankService extends Service<Rank> {
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
}
