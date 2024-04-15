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
        LOGGER.info("List items command generated.");
        try {
            if (arrayList == null || arrayList.isEmpty()) {
                if (category.equals("NA") && !isListMarked) {
                    throw new EmptyListException("Item");
                } else {
                    throw new EmptyListException("Filter Item");
                }
            }
        } catch (EmptyListException e) {
            LOGGER.warning("Empty item list.");
        }
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
        try {
            if (transactionList == null || transactionList.isEmpty()) {
                if (itemName.equals("NA")) {
                    throw new EmptyListException("Transaction");
                } else {
                    throw new EmptyListException("Filter Transaction");
                }
            }
        } catch (EmptyListException e) {
            LOGGER.warning("Empty list detected.");
        }
    }

    /**
     * Instantiates a <code>ListCommand</code> with the <code>ArrayList arrayList</code>
     * and any other modifiers available to that type of <code>ArrayList</code>.
     */
    public ListCommand(ArrayList<Promotion> arrayList) {
        try {
            if (arrayList.isEmpty()) {
                throw new EmptyListException("Promotion");
            }
        } catch (EmptyListException e) {
            LOGGER.warning("Empty list detected.");
        }
        this.promotionList = arrayList;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Runs the list command with 3 cases
     * Depending on which ArrayList is not empty, it prints that list.
     * */
    //@@author Fureimi
    public void execute() {

        if (containsTransactions(transactionList)) {
            showTransactionList();
            LOGGER.info("Transactions listed.");
        } else if (containsPromotions(promotionList)) {
            showPromotionList();
            LOGGER.info("Promotions listed.");
        } else if (containsItems(itemList) && (!category.equals("NA") || isListMarked)) {
            showCustomizedItemList();
            LOGGER.info("Customised item listed.");
        } else if (containsItems(itemList) && category.equals("NA") && !isListMarked) {
            TextUi.showList(itemList);
            LOGGER.info("All item listed.");
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

