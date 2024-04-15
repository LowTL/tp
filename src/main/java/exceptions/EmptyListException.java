package exceptions;


import common.Messages;

public class EmptyListException extends Exception {
    public EmptyListException(String error) {
        switch (error) {
        case "Filter Item":
            System.out.println(Messages.EMPTY_FILTERED_ITEM_LIST);
            break;
        case "Item":
            System.out.println(Messages.EMPTY_ITEM_LIST);
            break;
        case "Filter Transaction":
            System.out.println(Messages.EMPTY_FILTERED_TRANSACTION_LIST);
            break;
        case "Transaction":
            System.out.println(Messages.EMPTY_TRANSACTION_LIST);
            break;
        case "Promotion":
            System.out.println(Messages.EMPTY_PROMOTION_LIST);
            break;
        case "Empty List":
            System.out.println("No results found.");
            break;
        case "Bestseller":
            System.out.println(Messages.NO_BESTSELLER);
            break;
        default:
            System.out.println(error);
        }
    }

}
