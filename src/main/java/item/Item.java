package item;
import constants.UOM;

public class Item {
    public static int numberOfItems;
    protected String itemName;
    protected int quantity;
    protected String uom;
    protected String category;
    protected boolean isOOS;



    public Item(String name, int quantity, String uom, String category) {
        this.itemName = name;
        this.quantity = quantity;
        this.uom = uom;
        if (category.isEmpty()) {
            this.category = "NA";
        } else {
            this.category = category;
        }
        if (quantity == 0) {
            this.isOOS = true;
        } else {
            this.isOOS = false;
        }
        numberOfItems++;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    public void markOOS() {
        this.isOOS = true;
    }

    public void unmarkOOS() {
        this.isOOS = false;
    }
}
