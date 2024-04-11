package command;

import exceptions.EmptyListException;
import common.Messages;
import item.Item;
import item.Transaction;
import itemlist.Cashier;
import promotion.Promotion;
import ui.TextUi;

import java.util.ArrayList;

/**
 * Prints out the various <code>ArrayLists</code>, full or partial, based on modifiers.
 * @param <T> is the Type of <code>ArrayList</code> ie <code>Item</code>,
 *         <code>Transaction</code> or <code>Promotion</code>
 */
public class ListCommand<T> extends Command{

    protected ArrayList<Item> itemList =  new ArrayList<>();
    protected ArrayList<Transaction> transactionList = new ArrayList<>();
    protected ArrayList<Promotion> promotionList = new ArrayList<>();
    protected String category = "NA";
    protected boolean isListMarked = false;

    /**
    * Instantiates a <code>ListCommand</code> with the <code>ArrayList arrayList</code>
     * and any other modifiers available to that type of <code>ArrayList</code>.
     */
    public ListCommand(ArrayList<Item> arrayList, String category, boolean isListMarked) {
        this.itemList= arrayList;
        this.category = category;
        this.isListMarked = isListMarked;
    }

    public ListCommand(ArrayList<Transaction> arrayList, String itemName) {
        if (itemName.equals("NA")) {
            this.transactionList = arrayList;
        } else {
            this.transactionList = Cashier.getTransactions(itemName);
        }
    }

    public ListCommand(ArrayList<Promotion> arrayList) {
        this.promotionList = arrayList;
    }

    public String getCategory() {
        return category;
    }

    //@@author Fureimi
    /*
    * Runs the list command with 3 cases
    * Depending on which ArrayList is not empty, it prints that list.
    * @throws EmptyListException if all Lists are empty.
    * */
    public void execute() throws EmptyListException {

        if (containsTransactions(transactionList)) {
            showTransactionList();
        } else if (containsPromotions(promotionList)) {
            showPromotionList();
        } else if (category.equals("NA") && !isListMarked) {
            TextUi.showList(itemList);
        } else if (containsItems(itemList)) {
            showCustomizedItemList();
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

