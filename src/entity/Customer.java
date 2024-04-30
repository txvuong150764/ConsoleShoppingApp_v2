package entity;

import dto.Cart;

public class Customer {
    int id;
    String name;
    String password;
    int shopId;
    float loyaltyPoint;
    Cart cart;
    public Customer(){}

    public float getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(float loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer(int id, String name, String password, int shopId, float loyaltyPoint, Cart cart) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.shopId = shopId;
        this.loyaltyPoint = loyaltyPoint;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
