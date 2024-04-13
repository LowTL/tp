package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "added: ";
    protected String itemName;
    protected int quantity;
    protected String unitOfMeasurement;
    protected String category;
    protected float buyPrice;
    protected float sellPrice;
    private final Item toAdd;

    public AddCommand(String itemName, int quantity, String unitOfMeasurement, String category, float buyPrice,
            float sellPrice) {
        this.itemName = itemName.toLowerCase();
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
        this.category = category;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.toAdd = new Item(itemName, quantity, unitOfMeasurement, category, buyPrice, sellPrice);
    }

    public String getItemName() {
        return itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }
    public float getBuyPrice() {
        return buyPrice;
    }
    public float getSellPrice() {
        return sellPrice;
    }

    @Override
    public void execute() {
        if (Itemlist.itemIsExist(itemName)) {
            updateQuantity(itemName);
        } else {
            Itemlist.addItem(toAdd);
            System.out.print(MESSAGE_SUCCESS + getItemName() + " (Qty: " + getQuantity() + " " + getUnitOfMeasurement()
                    + ", Buy: $" + String.format("%.2f", getBuyPrice()) + ", Sell: $" +
                    String.format("%.2f", getSellPrice()) + ")");
            Storage.addToFile(Itemlist.getItems());
            if (!category.equals("NA")) {
                System.out.println(" to " + getCategory());
            } else {
                System.out.println();
                assert category.equals("NA");
            }
        }
    }

    public void updateQuantity(String itemName) {
        System.out.println("Item already exists and item information has been updated");
        int indexOfItem = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                indexOfItem = Itemlist.getIndex(item);
            }
        }
        assert indexOfItem != -1;
        int currentQty = Itemlist.getItem(indexOfItem).getQuantity();
        int newQty = getQuantity() + currentQty;
        new EditCommand(getItemName(), "NA", newQty, getUnitOfMeasurement(), getCategory(), getBuyPrice(),
                getSellPrice()).execute();
    }
}
