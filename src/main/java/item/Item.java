//@@author Fureimi
package item;

import itemlist.Itemlist;

public class Item {
    public boolean isMark;
    private String itemName;
    private int quantity;
    private String unitOfMeasurement;
    private String category;
    private float buyPrice;
    private float sellPrice;
    private boolean isOOS;

    public Item(String name, int quantity, String unitOfMeasurement, String category, float buyPrice, float sellPrice) {
        this.itemName = name;
        this.quantity = quantity;
        assert quantity>= 0 : "Quantity should not be negative.";
        this.unitOfMeasurement = unitOfMeasurement;
        if (category.isEmpty()) {
            this.category = "NA";
        } else {
            this.category = category;
        }
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.isOOS = quantity == 0;
        Itemlist.noOfItems++;

    }

    public String getCategory() {
        if (this.category.equals("NA")) {
            return null;
        } else {
            return this.category;
        }
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String newName) {
        this.itemName = newName;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String newUnitOfMeasurement) {
        this.unitOfMeasurement = newUnitOfMeasurement;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float newBuyPrice) {
        this.buyPrice = newBuyPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float newSellPrice) {
        this.sellPrice = newSellPrice;
    }

    public boolean getIsOOS() {
        return isOOS;
    }

    public void markOOS() {
        this.isOOS = true;
    }

    public void unmarkOOS() {
        this.isOOS = false;
    }

    public void mark() {
        this.isMark = true;
    }

    public void unmark() {
        this.isMark = false;
    }

    public boolean getMarkStatus() {
        return this.isMark;
    }

    @Override
    public String toString() {
        String categoryString = (getCategory() != null) ? ", Category: " + getCategory() : "";
        String markString = (this.isMark) ? "[X] " : "[ ] ";
        return (markString + getItemName() + " (Qty: " + getQuantity() + " " + getUnitOfMeasurement() +
                ", Buy: $" + getBuyPrice() + ", Sell: $" + getSellPrice() + categoryString + ")");
    }
}
