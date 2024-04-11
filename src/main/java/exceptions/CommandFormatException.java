package exceptions;

import parser.CommandType;
import common.Messages;

public class CommandFormatException extends Exception{
    public CommandFormatException(CommandType command){

        switch (command) {

        case ADD:
            System.out.println(Messages.INVALID_ADD_FORMAT);
            break;
        case DEL:
            System.out.println(Messages.INVALID_DELETE_FORMAT);
            break;
        case EDIT:
            System.out.println(Messages.INVALID_EDIT_FORMAT);
            break;
        case SELL:
            System.out.println(Messages.INVALID_SELL_FORMAT);
            break;
        case FIND:
            System.out.println(Messages.INVALID_FIND_FORMAT);
            break;
        case HELP:
            System.out.println(Messages.INVALID_HELP_FORMAT);
            break;
        case LIST_ITEMS:
            System.out.println(Messages.INVALID_LIST_FORMAT);
            break;
        case LIST_PROMOTIONS:
            System.out.println(Messages.INVALID_PROMOLIST_FORMAT);
            break;
        case DEL_PROMO:
            System.out.println(Messages.INVALID_DEL_PROMO_FORMAT);
            break;
        case PROMOTION:
            System.out.println(Messages.INVALID_PROMOTION_FORMAT);
            break;
        case MARK:
            System.out.println(Messages.INVALID_MARK_FORMAT);
            break;
        case UNMARK:
            System.out.println(Messages.INVALID_UNMARK_FORMAT);
            break;
        default:
            System.out.println(Messages.INVALID_COMMAND);
            break;
        }
    }

    public CommandFormatException(String error) {
        switch (error) {

        case "ITEM_NOT_FOUND":
            System.out.println(Messages.ITEM_NOT_FOUND);
            break;
        case "INVALID_DISCOUNT":
            System.out.println(Messages.INVALID_DISCOUNT);
            break;
        case "INVALID_ITEM_NAME":
            System.out.println(Messages.INVALID_ITEM_NAME);
            break;
        case "INVALID_CATEGORY":
            System.out.println(Messages.INVALID_CATEGORY);
            break;
        case "INVALID_COMMAND":
            System.out.println(Messages.INVALID_HELP_COMMAND);
            break;
        case "QTY_TOO_LARGE":
            System.out.println(Messages.QTY_TOO_LARGE);
            break;
        case "BUY_TOO_LARGE":
            System.out.println(Messages.BUY_TOO_LARGE);
            break;
        case "SELL_TOO_LARGE":
            System.out.println(Messages.SELL_TOO_LARGE);
            break;
        default:
            System.out.println(error);
        }

    }
}
