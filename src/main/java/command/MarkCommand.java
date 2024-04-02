package command;

import exceptions.CommandFormatException;
import itemlist.Itemlist;
import item.Item;


public class MarkCommand extends Command {

    protected String itemName;

    public MarkCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute() throws CommandFormatException {
        Item item = Itemlist.getItem(itemName);
        if (item == null) {
            ui.TextUi.replyToUser("Item of " + itemName + " not found! Please try again.");
        } else if (item.isMark) {
            ui.TextUi.replyToUser("Item is already marked!");
        } else {
            item.mark();
            ui.TextUi.replyToUser("Successfully marked " + item + "!");
        }
    }
}
