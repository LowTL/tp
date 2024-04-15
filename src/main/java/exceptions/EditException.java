package exceptions;

import ui.TextUi;

public class EditException extends Exception {
    public EditException(String error) {
        switch (error) {
        case "ITEM_ALREADY_EXISTS":
            TextUi.replyToUser("New item name cannot be the same as an existing item!");
            break;
        case "ITEM_NAME":
            TextUi.replyToUser("New item name cannot be blank!");
            break;
        case "QUANTITY":
            TextUi.replyToUser("Quantity cannot be negative!");
            break;
        case "UNIT_OF_MEASUREMENT":
            TextUi.replyToUser("New unit of measurement should not be empty!");
            break;
        case "CATEGORY":
            TextUi.replyToUser("New category should not be empty!");
            break;
        case "BUY_PRICE":
            TextUi.replyToUser("New buy price should be larger than 0!");
            break;
        case "SELL_PRICE":
            TextUi.replyToUser("New sell price should be larger than 0!");
            break;
        default:
            TextUi.replyToUser("Error with edits!");
            break;
        }

    }

}
