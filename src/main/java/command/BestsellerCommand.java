package command;

import item.Item;
import itemlist.Cashier;
import ui.TextUi;

public class BestsellerCommand extends Command {


    @Override
    public void execute() {
        Item bs = Cashier.getBestseller();
        TextUi.replyToUser("The current best-selling item is " +
                bs.getItemName());
    }
}
