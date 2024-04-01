package common;

public class Messages {
    public static final String DIVIDER = "----------------";
    public static final String INVALID_COMMAND = "Invalid command detected. Type 'help' for list of valid commands";
    public static final String HELP = "Commands: ....";
    public static final String INVALID_ADD_FORMAT ="Invalid command format. Please use format: " + "\n" +
            "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
            "buy/[BUY_PRICE] sell/[SELL_PRICE]'";
    public static final String INVALID_DELETE_FORMAT ="Invalid command format. Please use format: 'del [ITEM_NAME]'";
    public static final String INVALID_EDIT_FORMAT ="Invalid edit command format. Please use format: " +
            "'edit [ITEM_NAME] name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM] cat/[NEW_CATEGORY] " +
            "buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]'\n" + "You can edit at least 1 parameter up to all available" +
            " parameters. For example, if you only wish to update buy and sell price, you can input:\n" +
            "'edit [ITEM_NAME] buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]'";
    public static final String INVALID_SELL_FORMAT ="Invalid command format. Please use format: " +
            "'sell [ITEM_NAME] qty/[SELL_QUANTITY] price/[SELL_PRICE]'";
    public static final String INVALID_SELL_PRICE ="Price cannot be negative!";

    public static final String INVALID_FIND_FORMAT ="Invalid command format. Please use format: " + "\n" +
            "1. 'find [KEYWORD]' to search the entire Item List" + "\n" +
            "2. 'find /filter1/filter2 [KEYWORD] to search under the filters";
    public static final String WELCOME = "Welcome to StockMaster, where you can master the knowledge on your " +
            "Stock!";

    public static final String GOODBYE = "Thank you for using StockMaster, hope we have helped your lazy ass!";
}
