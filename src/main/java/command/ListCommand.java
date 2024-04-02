package command;

import item.Item;
import ui.TextUi;

import java.util.ArrayList;

public class ListCommand<T> extends Command{

    protected ArrayList<Item> arrayList;
    protected String category;
    protected boolean isListMarked;

    public ListCommand(ArrayList<Item> arrayList, String category, boolean isListMarked) {
        this.arrayList = arrayList;
        this.category = category;
        this.isListMarked = isListMarked;
    }

    //@@author Fureimi
    public void execute() {
        if (category.equals("NA") && !isListMarked) {
            TextUi.showInventoryList(arrayList);
        } else {
            TextUi.showCustomizedList(arrayList, category, isListMarked);
        }
    }
}

