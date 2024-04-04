package exceptions;


import common.Messages;

public class EmptyListException extends Exception {
    public EmptyListException(String error) {
        switch (error) {
        case "Item":
            System.out.println("There are no items with your search query.");
        case "Transaction":
            System.out.println("There are no transactions with your search query.");
        default:
            System.out.println(Messages.INVALID_COMMAND);
        }
    }

}