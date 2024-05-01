package service;

import Utils.FormatUtils;
import dto.Cart;
import entity.Customer;
import entity.Item;
import entity.Voucher;

import java.util.ArrayList;
import java.util.HashMap;

public class VoucherService {
    public Voucher getBestItemVoucher(ArrayList<Voucher> vouchers, Customer customer) {
        float maxDiscount = 0F;
        Voucher bestItemVoucher = null;
        for(Voucher voucher : vouchers) {
            if(voucher.getDiscountRate() > maxDiscount && customer.getCart().getTotal() >= voucher.getMinimumSpend()) {
                maxDiscount = voucher.getDiscountRate();
                bestItemVoucher = voucher;
            }
        }
        return bestItemVoucher;
    }

    public Voucher getBestShippingVoucher(ArrayList<Voucher> vouchers) {
        float maxDiscount = 0F;
        Voucher bestShippingVoucher = null;
        for(Voucher voucher : vouchers) {
            if(voucher.getDiscountRate() > maxDiscount) {
                maxDiscount = voucher.getDiscountRate();
                bestShippingVoucher = voucher;
            }
        }
        return bestShippingVoucher;
    }

    public HashMap<String, Voucher> getBestShopVoucher(ArrayList<Voucher> vouchers, Cart cart) {
        HashMap<String, Voucher> bestShopVoucher = new HashMap<>();
        for(Voucher voucher : vouchers) {
            for(Item item : cart.getItems()) {
                if(voucher.getType().equalsIgnoreCase(item.getName()) && voucher.getMinimumSpend() <= item.getAmount()) {
                    if(!bestShopVoucher.containsKey(item.getName())) {
                        bestShopVoucher.put(item.getName(), voucher);
                    }
                    else if(bestShopVoucher.get(item.getName()).getDiscountRate() <= voucher.getDiscountRate()){
                        bestShopVoucher.put(item.getName(), voucher);
                    }
                }
            }
        }
        return bestShopVoucher;
    }

    public void showVoucher(Voucher voucher) {
        if(voucher == null)
            return;
        System.out.println("Best " + voucher.getType() + " vouchers:");
        FormatUtils.printShopVoucherHeader();
        FormatUtils.printShopVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
        FormatUtils.printShopVoucherEnd();
    }

    public void showShopVoucher(HashMap<String, Voucher> bestShopVouchers) {
        System.out.println("Best shop vouchers:");
        FormatUtils.printShopVoucherHeader();
        for(String key : bestShopVouchers.keySet()) {
            Voucher voucher = bestShopVouchers.get(key);
            FormatUtils.printShopVoucher(voucher.getType(), voucher.getDiscountRate(), voucher.getMinimumSpend(), voucher.getAmount());
        }
        FormatUtils.printShopVoucherEnd();
    }
    public void useVoucher(ArrayList<Voucher> vouchers, Voucher voucher) {
        if(voucher == null)
            return;
        for(Voucher v : vouchers) {
            if(v.getType().equalsIgnoreCase(voucher.getType()) && v.getDiscountRate() == voucher.getDiscountRate() && v.getMinimumSpend() == voucher.getMinimumSpend()) {
                if(v.getAmount() > 0) {
                    v.setAmount(v.getAmount() - 1);
                }
                else {
                    vouchers.remove(v);
                }
                return;
            }
        }
    }
    public void useShopVoucher(ArrayList<Voucher> shopVoucher, HashMap<String, Voucher> vouchers) {
        for(String key : vouchers.keySet()) {
            int pos = shopVoucher.indexOf(vouchers.get(key));
            if(shopVoucher.get(pos).getAmount() > 0) {
                shopVoucher.get(pos).setAmount(shopVoucher.get(pos).getAmount() - 1);
            }
            else {
                shopVoucher.remove(vouchers.get(key));
            }
        }
    }
}
