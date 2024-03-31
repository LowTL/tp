package item;

import itemlist.Itemlist;

public class Item {
    private String itemName;
    private int quantity;
    private String uom;
    private String category;
    private float buyPrice;
    private float sellPrice;
    private boolean isOOS;

    public Item(String name, int quantity, String uom, String category, float buyPrice, float sellPrice) {
        this.itemName = name;
        this.quantity = quantity;
        assert quantity>= 0 : "Quantity should not be negative.";
        this.uom = uom;
        if (category.isEmpty()) {
            this.category = "NA";
        } else {
            this.category = category;
        }
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        if (quantity == 0) {
            this.isOOS = true;
        } else {
            this.isOOS = false;
        }
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

    public String getUom() {
        return uom;
    }

    public void setUom(String newUom) {
        this.uom = newUom;
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

    public void markOOS() {
        this.isOOS = true;
    }

    public void unmarkOOS() {
        this.isOOS = false;
    }

    public String toString() {
        String categoryString = (getCategory() != null) ? ", Category: " + getCategory() : "";
        return (getItemName() + " (Qty: " + getQuantity() + getUom() +
                ", Buy: $" + getBuyPrice() + ", Sell: $" + getSellPrice() + categoryString + ")");
    }
}
