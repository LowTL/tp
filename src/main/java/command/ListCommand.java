package command;

import item.Item;
import item.Transaction;
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
        if (category.equals("NA") && !isListMarked) {
            TextUi.showList(arrayList);
        } else if (arrayList.getClass().getSimpleName().equals("Item")) {
            TextUi.showCustomizedList((ArrayList<Item>) arrayList, category, isListMarked);
        } else if (arrayList.getClass().getSimpleName().equals("Transaction")) {
            TextUi.showTransactionList((ArrayList<Transaction>) arrayList);
        }
    }


}

