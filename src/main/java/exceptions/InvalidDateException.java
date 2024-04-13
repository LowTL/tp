//@@author HengShuHong
package exceptions;

import common.Messages;

public class InvalidDateException extends Exception {
    public InvalidDateException(String error) {
        switch(error) {
        case "INVALID_PERIOD":
            System.out.println(Messages.INVALID_PERIOD);
            break;
        case "INVALID_DATE":
            System.out.println(Messages.INVALID_DATE);
            break;
        case "INVALID_TIME":
            System.out.println(Messages.INVALID_TIME);
            break;
        case "ITEM_IS_PROMO":
            System.out.println(Messages.ITEM_IS_PROMO);
            break;
        case "INVALID_MONTH":
            System.out.println(Messages.INVALID_MONTH);
            break;
        default:
            System.out.println("Error Detected");
            break;
        }
    }
}
