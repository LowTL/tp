package command;

import exceptions.EmptyListException;
import item.Item;
import item.Transaction;
import itemlist.Cashier;
import promotion.Promotion;
import ui.TextUi;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Prints out the various <code>ArrayLists</code>, full or partial, based on modifiers.
 *  It prints differently based on the <code>Type</code> of ArrayList used.
 *  ie <code>Item</code>, <code>Transaction</code> or <code>Promotion</code>
 */
public class ListCommand extends Command{

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

    /**
     * Instantiates a <code>ListCommand</code> with the <code>ArrayList arrayList</code>
     * and any other modifiers available to that type of <code>ArrayList</code>.
     */
    public ListCommand(ArrayList<Transaction> arrayList, String itemName) {
        if (itemName.equals("NA")) {
            this.transactionList = arrayList;
        } else {
            this.transactionList = Cashier.getTransactions(itemName);
        }
    }

    /**
     * Instantiates a <code>ListCommand</code> with the <code>ArrayList arrayList</code>
     * and any other modifiers available to that type of <code>ArrayList</code>.
     */
    public ListCommand(ArrayList<Promotion> arrayList) {
        this.promotionList = arrayList;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Runs the list command with 3 cases
     * Depending on which ArrayList is not empty, it prints that list.
     * @throws EmptyListException if all Lists are empty.
     * */
    //@@author Fureimi
    public void execute() throws EmptyListException {

        if (containsTransactions(transactionList)) {
            showTransactionList();
            LOGGER.info("Transactions listed.");
        } else if (containsPromotions(promotionList)) {
            showPromotionList();
            LOGGER.info("Promotions listed.");
        } else if (category.equals("NA") && !isListMarked) {
            TextUi.showList(itemList);
            LOGGER.info("All items listed.");
        } else if (containsItems(itemList)) {
            showCustomizedItemList();
            LOGGER.info("Items listed.");
        } else {
            LOGGER.warning("No results found.");
            throw new EmptyListException("Empty List");
        }
    }

    private void showTransactionList() {
        try {
            if (transactionList.isEmpty()) {
                throw new EmptyListException("Transaction");
            }
        } catch (EmptyListException e) {
            LOGGER.warning("Empty list detected.");
            return;
        }
        TextUi.showTransactionList(transactionList);
    }

    private void showCustomizedItemList() {
        TextUi.showCustomizedList(itemList, category, isListMarked);
    }

    private void showPromotionList() {
        TextUi.showPromotionList(promotionList);
    }

    private static boolean containsItems(ArrayList<?> arrayList) {
        try {
            for (Object obj : arrayList) {
                if (obj instanceof Item) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "NullPointerException occurred.", e);
            return false;
        }
        return false;
    }

    private static boolean containsTransactions(ArrayList<?> arrayList) {
        try {
            for (Object obj : arrayList) {
                if (obj instanceof Transaction) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "NullPointerException occurred.", e);
            return false;
        }
        return false;
    }

    private static boolean containsPromotions(ArrayList<?> arrayList) {
        try {
            for (Object obj : arrayList) {
                if (obj instanceof Promotion) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, "NullPointerException occurred.", e);
            return false;
        }
        return false;
    }

}

