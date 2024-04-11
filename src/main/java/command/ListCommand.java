package command;

import exceptions.EmptyListException;
import common.Messages;
import item.Item;
import item.Transaction;
import itemlist.Cashier;
import promotion.Promotion;
import ui.TextUi;

import java.util.ArrayList;

public class ListCommand<T> extends Command{

    protected ArrayList<Item> itemList;
    protected ArrayList<Transaction> transactionList;
    protected ArrayList<Promotion> promotionList;
    protected String category;
    protected boolean isListMarked;

    public ListCommand(ArrayList<Item> arrayList, String category, boolean isListMarked) {
        this.itemList= arrayList;
        this.category = category;
        this.isListMarked = isListMarked;
    }

    //Overloaded command to get a filtered transactionList
    public ListCommand(ArrayList<Transaction> transactions, String itemName) {
        if (itemName.isEmpty()) {
            this.transactionList = transactions;
        } else {
            this.transactionList = Cashier.getTransactions(itemName);
        }
    }

    public ListCommand(ArrayList<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public String getCategory() {
        return category;
    }

    //@@author Fureimi
    public void execute() throws EmptyListException {
        try {
            if (itemList.isEmpty() && transactionList.isEmpty() && promotionList.isEmpty()) {
                throw new EmptyListException("There are no results.");
            }
        } catch (EmptyListException e) {
            return;
        }
        if (category.equals("NA") && !isListMarked) {
            TextUi.showList(itemList);
        } else if (containsTransactions(transactionList)) {
            showTransactionList();
        } else if (containsItems(itemList)) {
            showCustomizedItemList();
        } else if (containsPromotions(promotionList)) {
            showPromotionList();
        } else {
            TextUi.replyToUser(Messages.EMPTY_LIST);
        }
    }


    private void showTransactionList() {
        TextUi.showTransactionList(transactionList);
    }

    private void showCustomizedItemList() {
        TextUi.showCustomizedList(itemList, category, isListMarked);
    }

    private void showPromotionList() {
        TextUi.showPromotionList(promotionList);
    }

    private static boolean containsItems(ArrayList<?> arrayList) {
        for (Object obj : arrayList) {
            if (obj instanceof Item) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsTransactions(ArrayList<?> arrayList) {
        for (Object obj : arrayList) {
            if (obj instanceof Transaction) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsPromotions(ArrayList<?> arrayList) {
        for (Object obj : arrayList) {
            if (obj instanceof Promotion) {
                return true;
            }
        }
        return false;
    }

}

