package exceptions;

import common.Messages;

import java.sql.SQLOutput;

public class InvalidDateException extends Exception {
    public InvalidDateException(String Error) {
        switch(Error) {
        case "INVALID_PERIOD":
            System.out.println(Messages.INVALID_PERIOD);
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
