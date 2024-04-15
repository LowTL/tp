package command;

import exceptions.EmptyListException;
import itemlist.Cashier;
import ui.TextUi;

public class BestsellerCommand extends Command {


    //@author LowTL
    @Override
    public void execute() throws EmptyListException {
        String bs = Cashier.getBestseller();
        try {
            if (bs == null) {
                throw new EmptyListException("Bestseller");
            }
        } catch (EmptyListException e) {
            LOGGER.warning("No transaction found.");
            return;
        }
        TextUi.replyToUser("The current best-selling item is " +
                bs);
    }
}
