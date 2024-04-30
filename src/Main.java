import dto.Menu;
import entity.Customer;
import entity.Shop;
import service.CartService;
import service.CustomerService;
import service.MenuService;
import service.ShopService;

public class Main {
    public static final int BUY_ITEM = 1;
    public static final int CHECK_OUT = 1;
    public static final int BACK_TO_MAIN_MENU = 2;
    public static CustomerService customerService = new CustomerService();
    public static ShopService shopService = new ShopService();
    public static MenuService menuService = new MenuService();
    public static CartService cartService = new CartService();

    public static void main(String[] args) {
        Customer loggedInCustomer = customerService.login(customerService.getAll());
        Shop loggedInShop = shopService.getById(loggedInCustomer.getShopId());
        Menu menu = new Menu(loggedInShop);

        while(true) {
            int mainMenuOption = menuService.getMainMenuCustomerInput(menu, loggedInCustomer.getName()) - 1;
            if(mainMenuOption == menu.getMainMenuOptions().indexOf("View Cart")) {
                cartService.viewCart(loggedInCustomer.getCart());
                boolean backToMainMenu = false;
                while(!backToMainMenu) {
                    int viewCartMenuInput = menuService.getBuyMenuCustomerInput();
                    switch (viewCartMenuInput) {
                        case CHECK_OUT -> customerService.buyItem(loggedInCustomer);
                        case BACK_TO_MAIN_MENU -> backToMainMenu = true;
                    }
                }
            }
            else if(mainMenuOption == menu.getMainMenuOptions().indexOf("View Item")) {
                shopService.viewItems(loggedInShop);
                boolean backToMainMenu = false;
                while(!backToMainMenu) {
                    int buyMenuInput = menuService.getBuyMenuCustomerInput();
                    switch (buyMenuInput) {
                        case BUY_ITEM -> customerService.buyItem(loggedInCustomer);
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