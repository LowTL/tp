package command;

import exceptions.EmptyListException;
import item.Item;
import itemlist.Cashier;
import ui.TextUi;

public class BestsellerCommand extends Command {


    //@author LowTL
    @Override
    public void execute() {
        Item bs = Cashier.getBestseller();
        try {
            if (bs == null) {
                throw new EmptyListException("Transaction");
            }
        } catch (EmptyListException e) {
            return;
        }
        TextUi.replyToUser("The current best-selling item is " +
                bs.getItemName());
    }
}
