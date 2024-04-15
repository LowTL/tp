package parser;

import java.util.regex.Pattern;

public class ParserFormat {

    public static final Pattern HELP_COMMAND_FORMAT =
            Pattern.compile("help(?: c/(?<command>[^/]+))?");
    public static final Pattern ADD_COMMAND_FORMAT =
            Pattern.compile("add (?<itemName>[^/]+) qty/(?<quantity>\\d+) /(?<unitOfMeasurement>[^/]+)" +
                    "(?: cat/(?<category>[^/]+))? buy/(?<buyPrice>\\d*\\.?\\d+) sell/(?<sellPrice>\\d*\\.?\\d+)");

    public static final Pattern LIST_PROMOTION_COMMAND_FORMAT =
            Pattern.compile("list_promotions\\s*$");

    public static final Pattern DELETE_COMMAND_FORMAT =
            Pattern.compile("del (?<itemName>[^/]+)");

    public static final Pattern EDIT_COMMAND_FORMAT =
            Pattern.compile("edit (?<itemName>[^/]+)" +
                    "(?:\\s+(name/(?<newItemName>[^/]+)|qty/(?<newQuantity>\\d+)|uom/(?<newUnitOfMeasurement>[^/]+)|" +
                    "cat/(?<newCategory>[^/]+)|buy/(?<newBuyPrice>\\d*\\.?\\d+)|sell/(?<newSellPrice>\\d*\\.?\\d+)))+");

    public static final Pattern SELL_COMMAND_FORMAT =
            Pattern.compile("sell (?<itemName>[^/]+) qty/(?<sellQuantity>\\d+)");

    public static final Pattern FIND_COMMAND_FORMAT =
            Pattern.compile("find(?:\\s/(?<itemInfo>[^/]+(?:/[^/]+)*))?\\s(?<keyword>[^/]+)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern LIST_ITEM_COMMAND_FORMAT =
            Pattern.compile("list_items(?:\\s+(?<isMark>marked))?(?:\\s+cat/(?<category>[^/]+))?");

    public static final Pattern MARK_COMMAND_FORMAT =
            Pattern.compile("mark (?<itemName>[^/]+)");
    public static final Pattern UNMARK_COMMAND_FORMAT =
            Pattern.compile("unmark (?<itemName>[^/]+)");

    public static final Pattern PROMOTION_COMMAND_FORMAT =
            Pattern.compile("promotion (?<itemName>[^/]+) discount/(?<discount>\\d+(\\.\\d{1,2})?) " +
                    "period /from (?<startDate>\\d+) (?<startMonth>\\w+) (?<startYear>\\d+) " +
                    "/to (?<endDate>\\d+) (?<endMonth>\\w+) (?<endYear>\\d+) " +
                    "time /from (?<startTime>\\d{4}) /to (?<endTime>\\d{4})");
    public static final Pattern DELETE_PROMO_COMMAND_FORMAT =
            Pattern.compile("del_promo (?<itemName>[^/]+)");

    public static final Pattern LIST_TRANSACTION_COMMAND_FORMAT =
            Pattern.compile("^list_transactions(?:\\s+item/(?<itemName>\\w+))?$");


    public static final Pattern LOW_STOCK_COMMAND_FORMAT =
            Pattern.compile("low_stock /(?<amount>[^/]+)");

}
