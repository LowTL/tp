package command;

import itemlist.Cashier;
import parser.CommandType;
import ui.TextUi;

public class TotalProfitCommand extends Command {
    CommandType command;
    public TotalProfitCommand(CommandType command) {
        this.command = command;
    }
    @Override
    public void execute() {
        float totalProfit = command.equals(CommandType.TOTAL_PROFIT) ?
                Cashier.getTotalProfit() : Cashier.getTotalRevenue();
        if (totalProfit == 0) {
            return;
        }
        TextUi.replyToUser("You have earned " + totalProfit +
                " in "+ (command.equals(CommandType.TOTAL_PROFIT) ? "profits" : "revenue") + " so far.");
    }
}
