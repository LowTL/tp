package command;

import item.Item;
import item.Transaction;
import itemlist.Cashier;
import itemlist.Itemlist;
import storage.Storage;
import storage.TransactionLogs;

public class SellCommand extends Command {

    protected String itemName;
    protected int sellQuantity;
    protected float sellPrice;

    public SellCommand (String itemName, int quantity, float price) {
        this.itemName = itemName;
        this.sellQuantity = quantity;
        this.sellPrice = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    @Override
    public void execute() {
        int index = -1;
        Item toSell = Itemlist.getItems().get(0);
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().equals(itemName) || item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                toSell = item;
                break;
            }
        }
        if (index == -1) {
            //throw exception;
            System.out.println("Item not found!");
            return;
        }
        int remainingQuantity = Itemlist.getItem(index).getQuantity() - sellQuantity;
        float sellPrice = (this.sellPrice >= 0) ? this.sellPrice : Itemlist.getItem(index).getSellPrice();
        if (remainingQuantity < 0) {
            System.out.println("There is insufficient stock!");
            return;
        } else {
            ui.TextUi.showSellMessage(itemName, sellQuantity, remainingQuantity, sellPrice);
            Itemlist.editQuantity(index, remainingQuantity);
        }
        Storage.overwriteFile(Itemlist.getItems());
        Transaction newTransaction = new Transaction(itemName, sellQuantity, toSell.getBuyPrice(), sellPrice);
        Cashier.addItem(newTransaction);
        TransactionLogs.addToLog(Cashier.getTransactions());
    }

}
