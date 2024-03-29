package command;

import exceptions.CommandFormatException;
import item.Item;
import itemlist.Itemlist;
import parser.CommandType;
import storage.Storage;

public class DeleteCommand extends Command {

    protected String itemName;

    public DeleteCommand(String itemName) {
        this.itemName = itemName.toLowerCase(); //for checking later
    }

    public void execute() {
        int index = -1;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().equals(itemName)) {
                index = Itemlist.getItems().indexOf(item);
                break;
            }
        }
        if (index == -1) {
            //throw exception;
            System.out.println("Item does not exist.");
        } else {
            Itemlist.deleteItem(index);
            System.out.println(itemName + " has been successfully deleted.");
            Storage.overwriteFile(Itemlist.getItems());
            assert(!Itemlist.getItem(index).getItemName().equals(itemName));

        }
    }
}
