package command;

import exceptions.EmptyListException;
import item.Item;
import itemlist.Cashier;
import ui.TextUi;

public class BestsellerCommand extends Command {


    //@author LowTL
    @Override
    public void execute() throws EmptyListException {
        Item bs = Cashier.getBestseller();
        if (bs != null) {
            TextUi.replyToUser("The current best-selling item is " +
                    bs.getItemName());
        }
    }
}
