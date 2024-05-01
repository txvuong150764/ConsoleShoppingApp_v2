package dto;

import entity.Voucher;

import java.util.ArrayList;

public class VoucherList {
    public ArrayList<Voucher> getItemVoucher() {
        return itemVoucher;
    }

    public ArrayList<Voucher> getShippingVoucher() {
        return shippingVoucher;
    }

    public ArrayList<Voucher> getShopVoucher() {
        return shopVoucher;
    }

    ArrayList<Voucher> itemVoucher;


    ArrayList<Voucher> shippingVoucher;
    ArrayList<Voucher> shopVoucher;

    public VoucherList() {
        itemVoucher = new ArrayList<>();
        shippingVoucher = new ArrayList<>();
        shopVoucher = new ArrayList<>();
    }
    public VoucherList(ArrayList<Voucher> itemVoucher, ArrayList<Voucher> shippingVoucher, ArrayList<Voucher> shopVoucher) {
        this.itemVoucher = itemVoucher;
        this.shippingVoucher = shippingVoucher;
        this.shopVoucher = shopVoucher;
    }
}
