package command;

import common.Messages;
import exceptions.CommandFormatException;

public class HelpCommand extends Command{

    protected String command;

    public HelpCommand(String command){
        this.command = command;
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
        default:
            System.out.println(Messages.INVALID_HELP_COMMAND);
            break;
        }



    }
}
