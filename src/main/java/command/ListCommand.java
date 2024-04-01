package command;

import item.Item;
import ui.TextUi;

import java.util.ArrayList;

public class ListCommand<T> extends Command{

    protected ArrayList<Item> arrayList;
    protected String category;

    public ListCommand(ArrayList<Item> arrayList, String category) {
        this.arrayList = arrayList;
        this.category = category;
    }

    //@@author Fureimi
    public void execute() {
        if (!category.equals("NA")) {
            TextUi.showCategoryList(arrayList, category);
        } else {
            TextUi.showInventoryList(arrayList);
        }
    }
}

