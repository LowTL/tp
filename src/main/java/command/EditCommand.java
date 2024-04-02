//@@author Fureimi
package command;

import item.Item;
import itemlist.Itemlist;
import storage.Storage;

public class EditCommand extends Command{

    protected String itemName;
    protected String newItemName;
    protected int newQuantity;
    protected String newUnitOfMeasurement;
    protected String newCategory;
    protected float newBuyPrice;
    protected float newSellPrice;

    public  EditCommand(String itemName, String newItemName, int newQuantity, String newUnitOfMeasurement,
                        String newCategory,
                        float newBuyPrice, float newSellPrice) {
        this.itemName = itemName;
        this.newItemName = newItemName;
        this.newQuantity = newQuantity;
        this.newUnitOfMeasurement = newUnitOfMeasurement;
        this.newCategory = newCategory;
        this.newBuyPrice = newBuyPrice;
        this.newSellPrice = newSellPrice;
    }

    @Override
    public void execute() {
        int index = -1; // flag to check if item exists in Itemlist
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().equals(itemName) || item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                break;
            }
        }
        if (index == -1) { // if item is not found
            //throw exception;
            ui.TextUi.replyToUser("item not found!");
        } else {
            Item item = Itemlist.getItem(index);
            String itemName = item.getItemName();
            ui.TextUi.replyToUser("\n" + "Edited: ");
            if (!newItemName.equals("NA")) { // check if itemName was edited
                ui.TextUi.showEditMessage(itemName, "newItemName", itemName, newItemName);
                item.setItemName(newItemName);
            }
            if (newQuantity != -1) { // check if quantity was edited
                ui.TextUi.showEditMessage(itemName, "newQuantity", String.valueOf(item.getQuantity()),
                        String.valueOf(newQuantity));
                item.setQuantity(newQuantity);
            }
            if (!newUnitOfMeasurement.equals("NA")) { // check if unitOfMeasurement was edited
                ui.TextUi.showEditMessage(itemName, "newUnitOfMeasurement", item.getUnitOfMeasurement(),
                        newUnitOfMeasurement);
                item.setUnitOfMeasurement(newUnitOfMeasurement);
            }
            if (!newCategory.equals("NA")) { // check if category was edited
                ui.TextUi.showEditMessage(itemName, "newCategory", String.valueOf(item.getCategory()),
                        String.valueOf(newCategory));
                item.setCategory(newCategory);
            }
            if (newBuyPrice != -1) { // check if buyPrice was edited
                ui.TextUi.showEditMessage(itemName, "newBuyPrice", String.valueOf(item.getBuyPrice()),
                        String.valueOf(newBuyPrice));
                item.setBuyPrice(newBuyPrice);
            }
            if (newSellPrice != -1) { // check if sellPrice was edited
                ui.TextUi.showEditMessage(itemName, "newSellPrice", String.valueOf(item.getSellPrice()),
                        String.valueOf(newSellPrice));
                item.setSellPrice(newSellPrice);
            }
        }
        ui.TextUi.replyToUser("End of Edits");
        ui.TextUi.replyToUser("");
        Storage.overwriteFile(Itemlist.getItems());
    }
}
