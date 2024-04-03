package command;

import exceptions.CommandFormatException;
import itemlist.Itemlist;
import item.Item;
import storage.Storage;


public class UnmarkCommand extends Command {

    protected String itemName;

    public UnmarkCommand(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void execute() throws CommandFormatException {
        Item item = Itemlist.getItem(itemName);
        if (item == null) {
            ui.TextUi.replyToUser("Item of item name '" + itemName + "' not found! Please try again.");
        } else if (!item.isMark) {
            ui.TextUi.replyToUser("Item is already unmarked!");
        } else {
            item.unmark();
            ui.TextUi.replyToUser("Successfully unmarked " + item + "!");
        }
        Storage.overwriteFile(Itemlist.getItems());
    }

}
