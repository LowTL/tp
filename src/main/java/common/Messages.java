package common;

public class Messages {
    public static final String INVALID_COMMAND = "Invalid command detected. Type 'help' for list of valid commands";

    public static final String INVALID_HELP_FORMAT = "Invalid command format. Please use format: 'help' or " +
            "'help c/COMMAND'";
    public static final String INVALID_ADD_FORMAT = "Invalid command format. Please use format: " + "\n" +
            "'add ITEM_NAME qty/QUANTITY_OF_ITEM /UNIT_OF_MEASUREMENT [cat/CATEGORY] " +
            "buy/BUY_PRICE sell/SELL_PRICE'";
    public static final String INVALID_DELETE_FORMAT = "Invalid command format. Please use format: 'del [ITEM_NAME]'";
    public static final String INVALID_EDIT_FORMAT = "Invalid edit command format. Please use format: " +
            "'edit ITEM_NAME [name/NEW_NAME] [qty/NEW_QUANTITY] [uom/NEW_UOM] [cat/NEW_CATEGORY] " +
            "[buy/NEW_BUY_PRICE] [sell/NEW_SELL_PRICE]'\n" + "You can edit at least 1 parameter up to all available" +
            " parameters. For example, if you only wish to update buy and sell price, you can input:\n" +
            "'edit ITEM_NAME buy/NEW_BUY_PRICE sell/NEW_SELL_PRICE'";
    public static final String INVALID_SELL_FORMAT = "Invalid command format. Please use format: " +
            "'sell ITEM_NAME qty/SELL_QUANTITY'";
    public static final String ITEM_NOT_FOUND = "Item cannot be found/unavailable";

    public static final String ITEM_NOT_ON_PROMO = "Promotion does not exists for this item and hence " +
            "cannot be deleted";
    public static final String INVALID_FIND_FORMAT = "Invalid command format. Please use format: " + "\n" +
            "1. 'find KEYWORD' to search the entire Item List" + "\n" +
            "2. 'find /filter1/filter2 KEYWORD' to search under the filters";

    public static final String UNABLE_TO_DELETE = "There is a promotion that exists for this item. Please remove the " +
            "promotion before deleting the item.";
    public static final String EMPTY_ITEM_LIST = "There are no items at the moment.";
    public static final String EMPTY_TRANSACTION_LIST = "There are no transactions at the moment.";
    public static final String EMPTY_FILTERED_ITEM_LIST = "There are no items with your search query.";
    public static final String EMPTY_FILTERED_TRANSACTION_LIST = "There are no transactions with your search query.";
    public static final String EMPTY_LIST = "There is nothing here! Time to spend some money and stock em up!";
    public static final String WELCOME_MESSAGE = "Welcome to StockMaster, where you can master the knowledge on your " +
            "Stock!";
    public static final String GOODBYE_MESSAGE = "Thank you for using StockMaster, hope we have helped your lazy ass!";

    public static final String INVALID_PERIOD = "Invalid Period Format. Please ensure that range is valid" +
            "\n" + "e.g. period /from 1 Jan 2024 /to 10 Jan 2024";

    public static final String INVALID_TIME = "Invalid Time Format. Please ensure that the time is in 24 hours " +
            "format and have a valid range:" + "\n" + "e.g. time /from 0000 /to 2359";

    public static final String INVALID_FILTER = "Invalid Filter is used. Filters can only be " +
            "[item, qty, cat, uom, buy, sell] and must be in the following format /filter1/filter2 etc.";
    public static final String INVALID_DATE = "Invalid Date has been entered. Please ensure that the date exists.";
    public static final String INVALID_MONTH = "Invalid Month has been entered. Please use format: MMM (e.g. JAN, DEC)";

    public static final String INVALID_DISCOUNT = "Invalid Discount has been entered. " +
            "Please ensure it falls within the " + "range of 0 to 100";

    public static final String INVALID_MARK_FORMAT = "Invalid command format. Please use format: " +
            "'mark ITEM_NAME'";

    public static final String INVALID_UNMARK_FORMAT = "Invalid command format. Please use format: " +
            "'unmark ITEM_NAME'";

    public static final String INVALID_LIST_FORMAT = "Invalid Command Format. Please use format:\n" +
            "1. 'list_items' to list all items in the inventory, \n" +
            "2. 'list_items cat/CATEGORY' to list all items in that category, or \n" +
            "3. 'list_items marked' to list all marked items, or \n" +
            "4. 'list_items marked cat/CATEGORY' to list all marked items in that category.\n" +
            "Please note that marked must be in front of cat/CATEGORY.";

    public static final String INVALID_DEL_PROMO_FORMAT = "Invalid Command Format. Please use format: " +
            "del_promo [ITEM_NAME]";
    public static final String INVALID_PROMOLIST_FORMAT = "Invalid Command Format. Please use format: " +
            "list_promotions";


    public static final String ITEM_IS_PROMO = "Item already has a promotion. Please remove the current promotion to " +
            "add a new one.";

    public static final String INVALID_PROMOTION_FORMAT = "Invalid Command Format. Please use format:\n" +
            "promotion ITEM_NAME discount/DISCOUNT period /from START_DATE /to END_DATE time /from START_TIME"
            + " /to END_TIME";

    public static final String INVALID_LOW_STOCK_FORMAT = "Invalid Command Format. Please use format: " +
            "low_stock /AMOUNT";

    public static final String INVALID_ITEM_NAME = "Invalid item name. Please input an item name.";

    public static final String INVALID_CATEGORY = "Blank category detected. Please input a category.";

    public static final String INVALID_UNITS = "Invalid units of measurement.";

    public static final String QTY_TOO_LARGE = "Quantity is too large. Please input a smaller quantity.";

    public static final String BUY_TOO_LARGE = "Buy price is too large. Please input a smaller buy price.";

    public static final String SELL_TOO_LARGE = "Sell price is too large. Please input a smaller sell price.";

    public static final String INVALID_TRANSACTION_FORMAT = "Invalid format. Please input the format:" +
            "list_transactions [item/ITEM_NAME]";
    public static final String INVALID_LOW_STOCK_AMOUNT = "Please input a valid amount.";

    public static final String NEGATIVE_LOW_STOCK_AMOUNT = "Amount should be greater than 0.";

    public static final String INVALID_VALUE = "Please input a valid amount.";

    public static final String NO_BESTSELLER = "There are no transactions. Please add a transaction " +
            "before retrying the command.";

    public static final String EMPTY_PROMOTION_LIST = "There are no promotions at the moment.";
}
