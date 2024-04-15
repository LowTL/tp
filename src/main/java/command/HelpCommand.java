package command;

import common.HelpMessages;

public class HelpCommand extends Command{

    protected String command;

    public HelpCommand(String command){
        this.command = command;
        LOGGER.info("Command successfully created");
    }

    /**
     * Prints the help message for the relevant command
     */
    @Override
    public void execute() {
        switch (command) {
        case "NA":
            System.out.println(HelpMessages.HELP);
            break;
        case "list items":
            System.out.println(HelpMessages.HELP_LIST_ITEMS);
            break;
        case "add":
            System.out.println(HelpMessages.HELP_ADD);
            break;
        case "sell":
            System.out.println(HelpMessages.HELP_SELL);
            break;
        case "edit":
            System.out.println(HelpMessages.HELP_EDIT);
            break;
        case "find":
            System.out.println(HelpMessages.HELP_FIND);
            break;
        case "mark":
            System.out.println(HelpMessages.HELP_MARK);
            break;
        case "unmark":
            System.out.println(HelpMessages.HELP_UNMARK);
            break;
        case "delete":
            System.out.println(HelpMessages.HELP_DEL);
            break;
        case "bestseller":
            System.out.println(HelpMessages.HELP_BESTSELLER);
            break;
        case "total profit":
            System.out.println(HelpMessages.HELP_TOTAL_PROFIT);
            break;
        case "total revenue":
            System.out.println(HelpMessages.HELP_TOTAL_REVENUE);
            break;
        case "promotion":
            System.out.println(HelpMessages.HELP_PROMOTION);
            break;
        case "delete promotion":
            System.out.println(HelpMessages.HELP_DEL_PROMO);
            break;
        case "list promotion":
            System.out.println(HelpMessages.HELP_LIST_PROMO);
            break;
        case "low stock":
            System.out.println(HelpMessages.HELP_LOW_STOCK);
            break;
        case "exit":
            System.out.println(HelpMessages.HELP_EXIT);
            break;
        case "list transactions":
            System.out.println(HelpMessages.HELP_LIST_TRANSACTIONS);
            break;
        default:
            System.out.println(HelpMessages.INVALID_HELP_COMMAND);
            LOGGER.warning("Invalid help command received.");
            break;
        }



    }
}
