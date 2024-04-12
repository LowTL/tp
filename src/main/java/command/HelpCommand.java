package command;

import common.HelpMessages;
import common.Messages;

public class HelpCommand extends Command{

    protected String command;

    public HelpCommand(String command){
        this.command = command;
        LOGGER.info("Command successfully created");
    }
    @Override
    public void execute() {
        switch (command) {
        case "NA":
            System.out.println(Messages.HELP);
            break;
        case "list_items":
            System.out.println(Messages.HELP_LIST_ITEMS);
            break;
        case "add":
            System.out.println(Messages.HELP_ADD);
            break;
        case "sell":
            System.out.println(Messages.HELP_SELL);
            break;
        case "edit":
            System.out.println(Messages.HELP_EDIT);
            break;
        case "mark":
            System.out.println(Messages.HELP_MARK);
            break;
        case "unmark":
            System.out.println(Messages.HELP_UNMARK);
            break;
        case "delete":
            System.out.println(Messages.HELP_DEL);
            break;
        case "bestseller":
            System.out.println(Messages.HELP_BESTSELLER);
            break;
        case "total_profit":
            System.out.println(Messages.HELP_TOTAL_PROFIT);
            break;
        case "total_revenue":
            System.out.println(Messages.HELP_TOTAL_REVENUE);
            break;
        case "promotion":
            System.out.println(Messages.HELP_PROMOTION);
            break;
        case "delete promotion":
            System.out.println(Messages.HELP_DEL_PROMO);
            break;
        case "list promotion":
            System.out.println(Messages.HELP_LIST_PROMO);
            break;
        case "low stock":
            System.out.println(Messages.HELP_LOW_STOCK);
            break;
        case "exit":
            System.out.println(Messages.HELP_EXIT);
            break;
        case "list_transactions":
            System.out.println(HelpMessages.HELP_LIST_TRANSACTIONS);
        default:
            System.out.println(Messages.INVALID_HELP_COMMAND);
            LOGGER.warning("Invalid help command received.");
            break;
        }



    }
}
