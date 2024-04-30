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
}
