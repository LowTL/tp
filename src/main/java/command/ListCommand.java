package command;

import common.Messages;
import item.Item;
import item.Transaction;
import promotion.Promotion;
import ui.TextUi;

import java.util.ArrayList;

public class ListCommand<T> extends Command{

    protected ArrayList<T> arrayList;
    protected String category;
    protected boolean isListMarked;

    public ListCommand(ArrayList<T> arrayList, String category, boolean isListMarked) {
        this.arrayList = arrayList;
        this.category = category;
        this.isListMarked = isListMarked;
    }
    public ListCommand(ArrayList<T> transactions, boolean isVoided) {
        this.arrayList = transactions;
        this.isListMarked = isVoided;
    }

    public String getCategory() {
        return category;
    }

    //@@author Fureimi
    public void execute() {
        if (containsItems(arrayList)) {
            if (category.equals("NA") && !isListMarked) {
                TextUi.showList(arrayList);
            } else {
                showCustomizedItemList();
            }
        } else if (containsPromotions(arrayList)) {
            TextUi.showList(arrayList);
        } else if (containsTransactions(arrayList)) {
            showTransactionList();
        } else {
            TextUi.replyToUser(Messages.EMPTY_LIST);
        }
    }

    private void showTransactionList() {
        @SuppressWarnings("unchecked")
        ArrayList<Transaction> transactionList = (ArrayList<Transaction>) arrayList;
        TextUi.showTransactionList(transactionList);
    }

    private void showCustomizedItemList() {
        @SuppressWarnings("unchecked")
        ArrayList<Item> itemList = (ArrayList<Item>) arrayList;
        TextUi.showCustomizedList(itemList, category, isListMarked);
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

