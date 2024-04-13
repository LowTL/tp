package exceptions;


import common.Messages;

public class EmptyListException extends Exception {
    public EmptyListException(String error) {
        switch (error) {
        case "Item":
            System.out.println(Messages.EMPTY_ITEM_LIST);
            break;
        case "Transaction":
            System.out.println(Messages.EMPTY_TRANSACTION_LIST);
            break;
        case "Empty List":
            System.out.println(Messages.EMPTY_LIST);
            break;
        default:
            System.out.println(error);
        }
    }

}
