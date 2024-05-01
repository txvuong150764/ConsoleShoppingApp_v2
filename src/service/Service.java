package service;

import entity.Shop;

import java.util.ArrayList;

public abstract class Service<T>{
    public ArrayList<T> getAll() {
        return null;
    }

    public T getById(int id) {
        return null;
    }

    public ArrayList<T> getAllByShop(Shop shop) {
        return null;
    }
}
