import dto.Menu;
import dto.VoucherList;
import entity.Customer;
import entity.Shipping;
import entity.Shop;
import entity.Voucher;
import service.*;

import java.util.HashMap;

public class Main {
    public static final int ADD_TO_CART = 1;
    public static final int CHECK_OUT = 1;
    public static final int BACK_TO_MAIN_MENU = 2;
    public static CustomerService customerService = new CustomerService();
    public static ShopService shopService = new ShopService();
    public static MenuService menuService = new MenuService();
    public static CartService cartService = new CartService();
    public static ShippingService shippingService = new ShippingService();
    public static RankService rankService = new RankService();
    public static VoucherService voucherService = new VoucherService();
    public static VoucherListService voucherListService = new VoucherListService();

    public static void main(String[] args) {
        Customer loggedInCustomer = customerService.login(customerService.getAll());
        Shop loggedInShop = shopService.getById(loggedInCustomer.getShopId());
        Menu menu = new Menu(loggedInShop);

        while(true) {
            VoucherList voucherList = voucherListService.getAllVoucherByShop(loggedInShop, rankService.classifyRank(loggedInCustomer));

            int mainMenuOption = menuService.getMainMenuCustomerInput(menu, loggedInCustomer.getName()) - 1;
            if(mainMenuOption == menu.getMainMenuOptions().indexOf("View Cart")) {
                cartService.viewCart(loggedInCustomer.getCart());
                if(loggedInCustomer.getCart().getItems().isEmpty()) {
                    System.out.println("Your cart is empty. Please add more item using option 3.");
                    continue;
                }

                boolean backToMainMenu = false;
                while(!backToMainMenu) {
                    if(loggedInShop.isHasShippingMethod()) {
                        System.out.println("Please select shipping method: ");
                    }

                    int viewCartMenuInput = menuService.getShippingInput(menu) - 1;
                    if(viewCartMenuInput == menu.getCheckoutMenuOptions().indexOf("Back to Main Menu")) {
                        backToMainMenu = true;
                        continue;
                    }

                    Shipping shipping = null;
                    if(loggedInShop.isHasShippingMethod()) {
                        shipping = shippingService.getById(viewCartMenuInput + 1, loggedInShop);
                    }

                    Voucher bestItemVoucher = voucherService.getBestItemVoucher(voucherList.getItemVoucher(), loggedInCustomer);
                    Voucher bestShippingVoucher = voucherService.getBestShippingVoucher(voucherList.getShippingVoucher());
                    HashMap<String, Voucher> bestShopVoucher = voucherService.getBestShopVoucher(voucherList.getShopVoucher(), loggedInCustomer.getCart());
                    cartService.viewCartToCheckout(loggedInCustomer, shipping, bestItemVoucher, bestShippingVoucher, bestShopVoucher);

                    int checkOutInput = menuService.getCheckoutMenu();
                    if(checkOutInput == CHECK_OUT) {
                        cartService.checkOut(loggedInCustomer, shipping, voucherList);
                    }
                    backToMainMenu = true;
                }
            }
            else if(mainMenuOption == menu.getMainMenuOptions().indexOf("View Rank")) {
                rankService.viewRank(loggedInCustomer);
            }
            else if(mainMenuOption == menu.getMainMenuOptions().indexOf("View Item")) {
                boolean backToMainMenu = false;
                while(!backToMainMenu) {
                    shopService.viewItems(loggedInShop);
                    int buyMenuInput = menuService.getBuyMenuCustomerInput();
                    switch (buyMenuInput) {
                        case ADD_TO_CART -> customerService.buyItem(loggedInCustomer);
                        case BACK_TO_MAIN_MENU -> backToMainMenu = true;
                    }
                }
            }
            else if(mainMenuOption == menu.getMainMenuOptions().indexOf("Exit")) {
                System.out.println("Thanks for using our app.");
                System.exit(0);
            }
        }
    }
}