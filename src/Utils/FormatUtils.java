package Utils;

public class FormatUtils {
    public static void printItemHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.format("| %-15s | %-15s | %-15s |", "Item", "Price", "Amount");
        System.out.println("\n-------------------------------------------------------");
    }
    public static void printItem(String name, float price, int amount) {
        System.out.format("| %-15s | %-15s | %-15s |\n", name, price, amount);
    }
    public static void printItemEnd() {
        System.out.println("-------------------------------------------------------");
    }

    public static void printShippingHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.format("| %-15s | %-15s | %-15s |", "Type", "Duration", "Price");
        System.out.println("\n-------------------------------------------------------");
    }
    public static void printShipping(String type, int duration, float price) {
        System.out.format("| %-15s | %-15s | %-15s |\n", type, duration, price);
    }
    public static void printShippingEnd() {
        System.out.println("-------------------------------------------------------");
    }
    public static void printShopVoucherHeader() {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.format("| %-25s | %-15s | %-15s | %-15s |", "Voucher Type", "Discount Rate", "Minimum Spend", "Amount");
        System.out.println("\n-----------------------------------------------------------------------------------");
    }
    public static void printShopVoucher(String type, float discountRate, int minimumBuy, int amount) {
        System.out.format("| %-25s | %-15s | %-15s | %-15s |\n", type, discountRate, minimumBuy, amount);
    }
    public static void printShopVoucherEnd() {
        System.out.println("-----------------------------------------------------------------------------------");
    }
}
