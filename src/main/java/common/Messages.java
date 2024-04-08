package common;

public class Messages {
    public static final String DIVIDER = "----------------";
    public static final String INVALID_COMMAND = "Invalid command detected. Type 'help' for list of valid commands";
    public static final String HELP =
            " _________________________________________________________________________________________\n" +
            "|                                       STOCKMASTER                                       |\n" +
            "|_________________________________________________________________________________________|\n" +
            "| Commands | Format                                                                       |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "|list items| list_items                                                                       |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "| add      | add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] |\n" +
            "|          |     buy/[BUY_PRICE] sell/[SELL_PRICE]                                        |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "| sell     | sell [ITEM_NAME] qty/[SELL_QUANTITY]                                         |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "| edit     | edit [ITEM_NAME] name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM]            |\n" +
            "|          |      cat/[NEW_CATEGORY]                                                      |\n" +
            "|          |      (use AT LEAST 1 of: qty/, uom/, cat/)                                   |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "| delete   | del [ITEM_NAME]                                                              |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "| find     | 1. find [KEYWORD]  - to search the entire Item List                          |\n" +
            "|          | 2. find /filter1/filter2 [KEYWORD]  - to search under the filters*           |\n" +
            "|          |    * (filters: item, qty, uom, cat, buy, sell)                               |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "|promotion | promotion [ITEM_NAME] discount/[DISCOUNT] period /from [DATE] [MONTH] [YEAR] |\n" +
            "|          | to [DATE] [MONTH] [YEAR] time /from [TIME] /to [TIME]                        |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "|delete    | del_promo [ITEM_NAME]                                                        |\n" +
            "|promotion |                                                                              |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "|list      | list_promotions                                                              |\n" +
            "|promotions|                                                                              |\n" +
            "|----------|------------------------------------------------------------------------------|\n" +
            "| exit     | exit                                                                         |\n" +
            "|__________|______________________________________________________________________________|\n";
    public static final String INVALID_ADD_FORMAT = "Invalid command format. Please use format: " + "\n" +
            "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
            "buy/[BUY_PRICE] sell/[SELL_PRICE]'";
    public static final String INVALID_DELETE_FORMAT = "Invalid command format. Please use format: 'del [ITEM_NAME]'";
    public static final String INVALID_EDIT_FORMAT = "Invalid edit command format. Please use format: " +
            "'edit [ITEM_NAME] name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM] cat/[NEW_CATEGORY] " +
            "buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]'\n" + "You can edit at least 1 parameter up to all available" +
            " parameters. For example, if you only wish to update buy and sell price, you can input:\n" +
            "'edit [ITEM_NAME] buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]'";
    public static final String INVALID_SELL_FORMAT = "Invalid command format. Please use format: " +
            "'sell [ITEM_NAME] qty/[SELL_QUANTITY]'";
    public static final String ITEM_NOT_FOUND = "Item is not available";

    public static final String ITEM_NOT_ON_PROMO = "Promotion does not exits for this item and hence cannot be deleted";
    public static final String INVALID_FIND_FORMAT = "Invalid command format. Please use format: " + "\n" +
            "1. 'find [KEYWORD]' to search the entire Item List" + "\n" +
            "2. 'find /filter1/filter2 [KEYWORD]' to search under the filters";

    public static final String EMPTY_LIST = "There is nothing here! Time to spend some money and stock em up!";
    public static final String WELCOME_MESSAGE = "Welcome to StockMaster, where you can master the knowledge on your " +
            "Stock!";
    public static final String GOODBYE_MESSAGE = "Thank you for using StockMaster, hope we have helped your lazy ass!";

    public static final String INVALID_PERIOD = "Invalid Period Format. Please ensure that range is valid" +
            "\n" + "e.g. period /from 1 Jan 2024 /to 10 Jan 2024";

    public static final String INVALID_TIME = "Invalid Time Format. Please ensure that the time is in 24 hours " +
            "format and have a valid range:" + "\n" + "e.g. time /from 0000 /to 2359";

    public static final String INVALID_MONTH = "Invalid Month has been entered. Please use format: MMM (e.g. JAN, DEC)";

    public static final String INVALID_DISCOUNT = "Invalid Discount has been entered. " +
            "Please ensure it falls within the " + "range of 0 to 100";

    public static final String INVALID_MARK_FORMAT = "Invalid Command Format. Please use format : " +
            "mark [ITEM_NAME]";

    public static final String INVALID_UNMARK_FORMAT = "Invalid Command Format. Please use format : " +
            "unmark [ITEM_NAME]";

    public static final String INVALID_LIST_FORMAT = "Invalid Command Format. Please use format:\n" +
            "1. 'list' to list all items in the inventory, \n" +
            "2. 'list cat/[CATEGORY]' to list all items in that category, or \n" +
            "3. 'list marked' to list all marked items, or \n" +
            "4. 'list marked cat/[CATEGORY]' to list all marked items in that category.\n" +
            "Please note that marked must be in front of cat/[CATEGORY].";

    public static final String INVALID_DEL_PROMO_FORMAT = "Invalid Command Format. Please use format:\n" +
            "del_promo [ITEM_NAME]";
    public static final String INVALID_PROMOLIST_FORMAT = "Invalid Command Format.";

    public static final String ITEM_IS_PROMO = "Item already has a promotion. Please remove the current promotion to " +
            "add a new one.";

    public static final String INVALID_PROMOTION_FORMAT = "Invalid Command Format. Please use format:\n" +
            "promotion [ITEM_NAME] discount/[DISCOUNT] period /from [START_DAY] /to [END_DAY] time /from [START_TIME]" +
            " /to [END_TIME]";
}
