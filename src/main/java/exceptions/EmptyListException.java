package exceptions;


public class EmptyListException extends Exception {
    public EmptyListException(String error) {
        switch (error) {
        case "Item":
            System.out.println("There are no items with your search query.");
            break;
        case "Transaction":
            System.out.println("There are no transactions with your search query.");
            break;
        default:
            System.out.println(error);
        }
    }

}
