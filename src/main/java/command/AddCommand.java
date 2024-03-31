package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "added: ";
    protected String itemName;
    protected int quantity;
    protected String uom;
    protected String category = "NA";
    protected float buyPrice;
    protected float sellPrice;
    private final Item toAdd;

    public AddCommand(String itemName, int quantity, String uom, String category, float buyPrice, float sellPrice) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.uom = uom;
        this.category = category;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.toAdd = new Item(itemName, quantity, uom, category, buyPrice, sellPrice);
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
    public String getUom() {
        return uom;
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
            System.out.print(MESSAGE_SUCCESS + getItemName() + " (Qty: " + getQuantity() + " " + getUom() +
                    ", Buy: $" + getBuyPrice() + ", Sell: $" + getSellPrice() + ")");
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
        System.out.println("Item already exists and quantity has been changed");
        int indexOfItem = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().equals(itemName)) {
                indexOfItem = Itemlist.getIndex(item);
            }
        }
        assert indexOfItem != -1;
        int currentQty = Itemlist.getItem(indexOfItem).getQuantity();
        int newQty = getQuantity() + currentQty;
        new EditCommand(getItemName(), "NA", newQty, "NA", "NA", -1,
                -1).execute();
    }
}
