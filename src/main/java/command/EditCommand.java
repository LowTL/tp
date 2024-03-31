package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;

public class EditCommand extends Command{

    protected String itemName;
    protected String newItemName;
    protected int newQuantity;
    protected String newUom;
    protected String newCategory;
    protected float newBuyPrice;
    protected float newSellPrice;

    public  EditCommand(String itemName, String newItemName, int newQuantity, String newUom, String newCategory,
                        float newBuyPrice, float newSellPrice) {
        this.itemName = itemName;
        this.newItemName = newItemName;
        this.newQuantity = newQuantity;
        this.newUom = newUom;
        this.newCategory = newCategory;
        this.newBuyPrice = newBuyPrice;
        this.newSellPrice = newSellPrice;
    }

    @Override
    public void execute() {
        int index = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().equals(itemName) || item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                break;
            }
        }
        if (index == -1) {
            //throw exception;
            ui.TextUi.replyToUser("item not found!");
        } else {
            Item item = Itemlist.getItem(index);
            String itemName = item.getItemName();
            ui.TextUi.replyToUser("\n" +
                    "Changed: ");
            if (!newItemName.equals("NA")) {
                ui.TextUi.showEditMessage(itemName, "newItemName", itemName, newItemName);
                item.setItemName(newItemName);
            }
            if (newQuantity != -1) {
                ui.TextUi.showEditMessage(itemName, "newQuantity", String.valueOf(item.getQuantity()),
                        String.valueOf(newQuantity));
                item.setQuantity(newQuantity);
            }
            if (!newUom.equals("NA")) {
                ui.TextUi.showEditMessage(itemName, "newUom", item.getUom(), newUom);
                item.setUom(newUom);
            }
            if (!newCategory.equals("NA")) {
                ui.TextUi.showEditMessage(itemName, "newCategory", String.valueOf(item.getCategory()),
                        String.valueOf(newCategory));
                item.setCategory(newCategory);
            }
            if (newBuyPrice != -1) {
                ui.TextUi.showEditMessage(itemName, "newBuyPrice", String.valueOf(item.getBuyPrice()),
                        String.valueOf(newBuyPrice));
                item.setBuyPrice(newBuyPrice);
            }
            if (newSellPrice != -1) {
                ui.TextUi.showEditMessage(itemName, "newSellPrice", String.valueOf(item.getSellPrice()),
                        String.valueOf(newSellPrice));
                item.setSellPrice(newSellPrice);
            }
        }
        ui.TextUi.replyToUser("");
        Storage.overwriteFile(Itemlist.getItems());
    }
}
