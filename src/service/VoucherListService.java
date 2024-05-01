package service;

import Utils.FormatUtils;
import dto.Cart;
import dto.VoucherList;
import entity.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VoucherListService {
    public ArrayList<ArrayList<Voucher>> getItemAndShippingVoucher(Shop shop, Rank rank) {
        ArrayList<Voucher> itemVoucher = new ArrayList<>();
        ArrayList<Voucher> shippingVoucher = new ArrayList<>();
        ArrayList<ArrayList<Voucher>> itemAndShippingVoucher = new ArrayList<>();
        try {
            File myFile = new File("resource/Shop_" + shop.getId() + "/" + rank.getName().toLowerCase() +"_vouchers.txt");
            if(myFile.exists() && !myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                while(sc.hasNextLine()) {
                    String voucherInfo = sc.nextLine();
                    if(!voucherInfo.isEmpty()) {
                        String[] voucherInfoSplited = voucherInfo.split(",");
                        Voucher voucher = new Voucher(voucherInfoSplited[0], Float.parseFloat(voucherInfoSplited[1]), Integer.parseInt(voucherInfoSplited[2]), Integer.parseInt(voucherInfoSplited[3]));
                        if(voucher.getType().equals("Shipping")) {
                            shippingVoucher.add(voucher);
                        }
                        else {
                            itemVoucher.add(voucher);
                        }
                    }
                }
                sc.close();
            }
            itemAndShippingVoucher.add(itemVoucher);
            itemAndShippingVoucher.add(shippingVoucher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemAndShippingVoucher;
    }
    public ArrayList<Voucher> getShopVoucher(Shop shop) {
        ArrayList<Voucher> shopVoucher = new ArrayList<>();
        ArrayList<ArrayList<Voucher>> itemAndShippingVoucher = new ArrayList<>();
        try {
            File myFile = new File("resource/Shop_" + shop.getId() + "/shop_vouchers.txt");
            if(myFile.exists() && !myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                while(sc.hasNextLine()) {
                    String voucherInfo = sc.nextLine();
                    if(!voucherInfo.isEmpty()) {
                        String[] voucherInfoSplited = voucherInfo.split(",");
                        Voucher voucher = new Voucher(voucherInfoSplited[0], Float.parseFloat(voucherInfoSplited[1]), Integer.parseInt(voucherInfoSplited[2]), Integer.parseInt(voucherInfoSplited[3]));
                        shopVoucher.add(voucher);
                    }
                }
                sc.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shopVoucher;
    }
    public VoucherList getAllVoucherByShop(Shop shop, Rank rank) {
        ArrayList<ArrayList<Voucher>> itemAndShippingVouchers = getItemAndShippingVoucher(shop, rank);
        ArrayList<Voucher> itemVoucher = itemAndShippingVouchers.get(0);
        ArrayList<Voucher> shippingVoucher = itemAndShippingVouchers.get(1);
        ArrayList<Voucher> shopVoucher = getShopVoucher(shop);

        return new VoucherList(itemVoucher, shippingVoucher, shopVoucher);
    }

    public void showAllVoucher(VoucherList voucherList) {
        FormatUtils.printShopVoucherHeader();

        for(Voucher voucher : voucherList.getShippingVoucher()) {
            FormatUtils.printShopVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
        }

        for(Voucher voucher : voucherList.getItemVoucher()) {
            FormatUtils.printShopVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
        }

        for(Voucher voucher : voucherList.getShopVoucher()) {
            FormatUtils.printShopVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
        }

        FormatUtils.printShopVoucherEnd();
    }
    public void writeItemAndShippingVoucher(ArrayList<Voucher> vouchers, Shop shop, Rank rank) {
        try {
            FileWriter writer = new FileWriter("resource/Shop_" + shop.getId() + "/" + rank.getName().toLowerCase() +"_vouchers.txt", true);
            for(Voucher voucher : vouchers) {
                String voucherInfo = voucher.getType() + "," + voucher.getDiscountRate() + "," + voucher.getMinimumSpend() + "," + voucher.getAmount() + "\n";
                writer.write(voucherInfo);
            }
            writer.close();;
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    public void writeShopVoucher(ArrayList<Voucher> vouchers, Shop shop) {
        try {
            FileWriter writer = new FileWriter("resource/Shop_" + shop.getId() + "/shop_vouchers.txt", true);
            for(Voucher voucher : vouchers) {
                String voucherInfo = voucher.getType() + "," + voucher.getDiscountRate() + "," + voucher.getMinimumSpend() + "," + voucher.getAmount() + "\n";
                writer.write(voucherInfo);
            }
            writer.close();;
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    public void write(VoucherList voucherList, Shop shop, Rank rank) {
        clearVoucherFile("resource/Shop_" + shop.getId() + "/shop_vouchers.txt");
        clearVoucherFile("resource/Shop_" + shop.getId() + "/" + rank.getName().toLowerCase() +"_vouchers.txt");
        writeItemAndShippingVoucher(voucherList.getItemVoucher(), shop, rank);
        writeItemAndShippingVoucher(voucherList.getShippingVoucher(), shop, rank);
        writeShopVoucher(voucherList.getShopVoucher(), shop);
    }
    public void clearVoucherFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
