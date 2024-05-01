package service;

import dto.Cart;
import entity.Customer;
import entity.Item;
import entity.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService extends Service<Customer> {
    public ShopService shopService = new ShopService();
    public CartService cartService = new CartService();
    public ItemService itemService = new ItemService();
    @Override
    public ArrayList<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            File myFile = new File("resource/customers.txt");
            if(myFile.exists() && !myFile.isDirectory()) {
                Scanner sc = new Scanner(myFile);
                while(sc.hasNextLine()) {
                    String customerInfo = sc.nextLine();
                    if(!customerInfo.isEmpty()) {
                        String[] customerInfoSplited = customerInfo.split(",", 6);
                        Shop shop = shopService.getById(Integer.parseInt(customerInfoSplited[3].trim()));
                        ArrayList<Item> shoppingCart = cartService.readShoppingCart(customerInfoSplited[5]);

                        Customer customer = new Customer(Integer.parseInt(customerInfoSplited[0].trim()), customerInfoSplited[1].trim(), customerInfoSplited[2].trim(), shop.getId(), Float.parseFloat(customerInfoSplited[4]), new Cart(shoppingCart));
                        customers.add(customer);
                    }
                }
                sc.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        ArrayList<Customer> customers = this.getAll();

        for(Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }
    public Customer getCustomerLogin(ArrayList<Customer> customers, String name, String password) {
        for(Customer customer : customers) {
            if(customer.getName().equals(name) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }
    public Customer login(ArrayList<Customer> customers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---------------------------- Login Screen ----------------------------");
        System.out.print("                      Name: ");
        String name = sc.nextLine();
        System.out.print("                      Password: ");
        String password = sc.nextLine();

        Customer loginUser = this.getCustomerLogin(customers, name, password);
        if(loginUser != null) {
            System.out.println("\n                        Login Successfully!!!!");
        }
        else {
            System.out.println("\nName or Password is incorrect. Please try again!!!!!!!!");
            loginUser = login(customers);
        }
        return loginUser;
    }

    public int getCustomerInputAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount you want to buy: ");
        return sc.nextInt();
    }
    public void buyItem(Customer customer) {
        Shop shop = shopService.getById(customer.getShopId());
        ArrayList<Item> items = itemService.getAllByShop(shop);

        itemService.clearItemFile(shop);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter item you want to add: ");
        String itemName = sc.nextLine();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                int amount = getCustomerInputAmount();
                while (amount < 0 || amount > item.getAmount()) {
                    amount = getCustomerInputAmount();
                    System.out.println("Invalid amount. Please re-enter.");
                }
                item.setAmount(item.getAmount() - amount);
                cartService.updateCart(customer.getCart(), item, amount);
                System.out.println("Added " + amount + " " + item.getName() + " to cart");
            }
            itemService.write(item, shop);
        }
        System.out.println("Invalid item. Please re-enter.");
    }
}
