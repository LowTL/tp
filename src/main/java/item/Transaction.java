package item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String dateTime;
    private float totalPrice;
    private float profit;
    private String itemName;
    private int quantity;
    private float buyPrice;
    private float sellPrice;

    public Transaction(String name, int inputQty, float inputBuy, float inputSell) {
        setDateTime();
        itemName = name;
        quantity = inputQty;
        buyPrice = inputBuy;
        sellPrice = inputSell;
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
    }

    public Transaction(String name, int inputQty, int inputBuy, int inputSell, String storedTime) {
        dateTime = storedTime;
        itemName = name;
        quantity = inputQty;
        buyPrice = inputBuy;
        sellPrice = inputSell;
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public float getSellPrice() {
        return this.sellPrice;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public float getProfit() {
        return this.profit;
    }

    public void setDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = currentTime.format(formatter);
    }
}
