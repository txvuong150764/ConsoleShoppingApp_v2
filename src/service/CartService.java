package service;

import dto.Cart;
import dto.VoucherList;
import entity.Customer;
import entity.Item;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.FormatUtils;
import entity.Shipping;
import entity.Voucher;

public class CartService {
    public VoucherService voucherService = new VoucherService();
    public ItemService itemService = new ItemService();
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
        System.out.println("Total items price: " + cart.getTotal());
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

    public float totalPrice(Cart cart, Shipping shipping) {
        return shipping != null ? (cart.getTotal() + shipping.getPrice()) : cart.getTotal();
    }

    public void viewCartToCheckout(Customer customer, Shipping shipping, Voucher bestItemVoucher, Voucher bestShippingVoucher, HashMap<String, Voucher> bestShopVouchers) {
        System.out.println("Cart summarization: ");
        viewCart(customer.getCart());

        if(shipping != null) {
            System.out.println("Shipping method: ");
            FormatUtils.printShippingHeader();
            FormatUtils.printShipping(shipping.getName(), shipping.getDuration(), shipping.getPrice());
            FormatUtils.printShippingEnd();
        }

        voucherService.showVoucher(bestItemVoucher);
        voucherService.showVoucher(bestShippingVoucher);
        voucherService.showShopVoucher(bestShopVouchers);

        float totalPrice = totalPriceAfterDiscount(customer.getCart(), shipping, bestItemVoucher, bestShippingVoucher, bestShopVouchers);
        System.out.println("Total: " + totalPrice);
    }
    public float totalPriceAfterDiscount(Cart cart, Shipping shipping, Voucher bestItemVoucher, Voucher bestShippingVoucher, HashMap<String, Voucher> bestShopVouchers) {
        float totalPriceAfterDiscount = cart.getTotal();

        for(String key : bestShopVouchers.keySet()) {
            Item i = itemService.getItemInCartByName(key, cart);
            totalPriceAfterDiscount -= bestShopVouchers.get(key).getDiscountRate() * i.getPrice() * i.getAmount();
        }

        totalPriceAfterDiscount *= bestItemVoucher == null ? 1 : (1 - bestItemVoucher.getDiscountRate());

        float shippingPriceAfterDiscount = shipping == null ? 0 : (bestShippingVoucher != null ? shipping.getPrice() * (1 - bestShippingVoucher.getDiscountRate()) : shipping.getPrice());

        return totalPriceAfterDiscount + shippingPriceAfterDiscount;
    }
    public void checkOut(Customer customer, Shipping shipping, VoucherList voucherList) {
        float loyalPointsGained = customer.getCart().getTotal() * 0.1F;
        Voucher bestItemVoucher = voucherService.getBestItemVoucher(voucherList.getItemVoucher(), customer);
        Voucher bestShippingVoucher = voucherService.getBestShippingVoucher(voucherList.getShippingVoucher());
        HashMap<String, Voucher> bestShopVouchers = voucherService.getBestShopVoucher(voucherList.getShopVoucher(), customer.getCart());

        voucherService.useVoucher(voucherList.getItemVoucher(), bestItemVoucher);
        voucherService.useVoucher(voucherList.getShippingVoucher(), bestShippingVoucher);
        voucherService.useShopVoucher(voucherList.getShopVoucher(), bestShopVouchers);

        customer.setLoyaltyPoint(customer.getLoyaltyPoint() + loyalPointsGained);

        System.out.println("You paid " + totalPriceAfterDiscount(customer.getCart(), shipping, bestItemVoucher, bestShippingVoucher, bestShopVouchers));
        System.out.println("You received " + loyalPointsGained + " points.");

        customer.setCart(new Cart());
    }
}
