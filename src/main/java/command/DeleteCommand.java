package command;

import exceptions.EmptyListException;
import item.Item;
import itemlist.Itemlist;
import storage.Storage;

import java.util.logging.Level;

public class DeleteCommand extends Command {

    protected String itemName;

    public DeleteCommand(String itemName) {
        this.itemName = itemName.toLowerCase(); //for checking later
    }

    public void execute() throws EmptyListException {
        int index = -1;
        try {
            for (Item item : Itemlist.getItems()) {
                if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                    index = Itemlist.getItems().indexOf(item);
                    break;
                }
            }
            if (index == -1) {
                //throw exception;
                throw new EmptyListException("Item");
            } else {
                Itemlist.deleteItem(index);
                System.out.println(itemName + " has been successfully deleted.");
                Storage.overwriteFile(Itemlist.getItems());
                if (index == Itemlist.getItems().size()) {
                    assert (Itemlist.getItem(index) == null);
                } else {
                    assert (!Itemlist.getItem(index).getItemName().equals(itemName));
                }
                LOGGER.info("Item successfully deleted.");
            }
        } catch (IndexOutOfBoundsException | EmptyListException e) {
            LOGGER.log(Level.WARNING, "Item not deleted.", e);
            System.out.println("Itemlist is empty.");
        }
    }
}
