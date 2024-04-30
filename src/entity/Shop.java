package entity;

import java.util.ArrayList;

public class Shop {
    int id;
    String name;
    boolean hasUserRank;
    boolean hasShippingMethod;

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Shop(int id, String name, boolean hasUserRank, boolean hasShippingMethod) {
        this.id = id;
        this.name = name;
        this.hasUserRank = hasUserRank;
        this.hasShippingMethod = hasShippingMethod;
    }

    public boolean isHasUserRank() {
        return hasUserRank;
    }

    public void setHasUserRank(boolean hasUserRank) {
        this.hasUserRank = hasUserRank;
    }

    public boolean isHasShippingMethod() {
        return hasShippingMethod;
    }

    public void setHasShippingMethod(boolean hasShippingMethod) {
        this.hasShippingMethod = hasShippingMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
