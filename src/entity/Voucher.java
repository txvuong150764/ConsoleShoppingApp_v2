package entity;

public class Voucher {
    String type;
    float discountRate;
    int minimumSpend;
    int amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    public int getMinimumSpend() {
        return minimumSpend;
    }

    public void setMinimumSpend(int minimumSpend) {
        this.minimumSpend = minimumSpend;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Voucher(String type, float discountRate, int minimumSpend, int amount) {
        this.type = type;
        this.discountRate = discountRate;
        this.minimumSpend = minimumSpend;
        this.amount = amount;
    }
}
