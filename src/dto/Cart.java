package dto;

import entity.Item;

import java.util.ArrayList;

public class Cart {
    ArrayList<Item> items;
    float total;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(ArrayList<Item> items) {
        this.total = 0;
        this.items = items;
        for(Item item : items) {
            this.total += item.getPrice();
        }
    }
}
