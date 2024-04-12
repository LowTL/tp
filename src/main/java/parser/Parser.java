package parser;

import command.AddCommand;
import command.AddPromotionCommand;
import command.BestsellerCommand;
import command.Command;
import command.DeleteCommand;
import command.DeletePromotionCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.LowStockCommand;
import command.MarkCommand;
import command.SellCommand;
import command.TotalProfitCommand;
import command.UnmarkCommand;
import common.Messages;
import exceptions.CommandFormatException;
import exceptions.EditException;
import exceptions.InvalidDateException;
import itemlist.Cashier;
import itemlist.Itemlist;
import promotion.Month;
import promotion.Promotionlist;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public static final Pattern HELP_COMMAND_FORMAT =
            Pattern.compile("help(?: c/(?<command>[^/]+))?");
    public static final Pattern ADD_COMMAND_FORMAT =
            Pattern.compile("add (?<itemName>[^/]+) qty/(?<quantity>\\d+) /(?<unitOfMeasurement>[^/]+)" +
                    "(?: cat/(?<category>[^/]+))? buy/(?<buyPrice>\\d*\\.?\\d+) sell/(?<sellPrice>\\d*\\.?\\d+)");


    public static final Pattern DELETE_COMMAND_FORMAT =
            Pattern.compile("del (?<itemName>[^/]+)");

    public static final Pattern EDIT_COMMAND_FORMAT =
        Pattern.compile("edit (?<itemName>[^/]+)" +
                "(?:\\s+(name/(?<newItemName>[^/]+)|qty/(?<newQuantity>\\d+)|uom/(?<newUnitOfMeasurement>[^/]+)|" +
                "cat/(?<newCategory>[^/]+)|buy/(?<newBuyPrice>\\d*\\.?\\d+)|sell/(?<newSellPrice>\\d*\\.?\\d+)))+");

    public static final Pattern SELL_COMMAND_FORMAT =
            Pattern.compile("sell (?<itemName>[^/]+) qty/(?<sellQuantity>\\d+)");

    public static final Pattern FIND_COMMAND_FORMAT =
            Pattern.compile("find(?: /(?<itemInfo>[^/]+))* (?<keyword>[^/]+)");

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

    public Command parseInput(String userInput){
        final CommandType userCommand;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            System.out.println(Messages.INVALID_COMMAND);
            System.out.println(Messages.HELP);
            logger.log(Level.FINE, "Invalid command received");
            return new IncorrectCommand();
        }
        String commandWord = matcher.group("commandWord").toUpperCase();
        try {
            userCommand = CommandType.valueOf(commandWord);
        } catch (IllegalArgumentException e){
            System.out.println(Messages.INVALID_COMMAND);
            return new IncorrectCommand();
        }
        switch (userCommand) {
        case EXIT:
            return new ExitCommand(true);
        case HELP:
            try {
                return prepareHelp(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case LIST_ITEMS:
            try {
                return prepareItemList(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case LIST_PROMOTIONS:
            return preparePromotionList();
        case LIST_TRANSACTIONS:
            try {
                return prepareTransactionList(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case DEL_PROMO:
            try {
                return prepareDeletePromo(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case ADD:
            try {
                return prepareAdd(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case DEL:
            try {
                return prepareDelete(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case EDIT:
            try {
                return prepareEdit(userInput);
            } catch (CommandFormatException | EditException e) {
                logger.log(Level.WARNING, "Invalid input detected.", e);
                break;
            }
        case FIND:
            try {
                return prepareFind(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case SELL:
            try {
                return prepareSell(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case PROMOTION:
            try {
                return preparePromotion(userInput);
            } catch (CommandFormatException | InvalidDateException e) {
                logger.log(Level.WARNING, "Invalid input detected.", e);
                break;
            }
        case MARK:
            try {
                return prepareMark(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case UNMARK:
            try {
                return prepareUnmark(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        case TOTAL_PROFIT:
            //fallthrough
        case TOTAL_REVENUE:
            return new TotalProfitCommand(userCommand);
        case BESTSELLER:
            return new BestsellerCommand();
        case LOW_STOCK:
            try {
                return prepareLowStock(userInput);
            } catch (CommandFormatException e) {
                logger.log(Level.WARNING, "Invalid input detected.");
                break;
            }
        default:
            System.out.println(Messages.INVALID_COMMAND);
            return new IncorrectCommand();
        }
        return new IncorrectCommand();
    }

    private Command prepareHelp(String args) throws CommandFormatException{
        final Matcher matcher = HELP_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.HELP);
        }

        String command = matcher.group("command") != null ?
                matcher.group("command").toLowerCase().trim() : "NA";
        if (command.isEmpty()) {
            throw new CommandFormatException("INVALID_HELP_COMMAND");
        }

        return new HelpCommand(command);
    }

    private Command prepareAdd(String args) throws CommandFormatException{
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.ADD);
        }

        String itemName = matcher.group("itemName").toLowerCase().trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }

        String category = matcher.group("category") != null ? matcher.group("category").trim() : "NA";
        if (category.isEmpty()) {
            throw new CommandFormatException("INVALID_CATEGORY");
        }

        int quantity;
        try {
            quantity = Integer.parseInt(matcher.group("quantity"));
        } catch (NumberFormatException e) {
            throw new CommandFormatException("QTY_TOO_LARGE");
        }

        float buyPrice;
        try {
            buyPrice = Float.parseFloat(matcher.group("buyPrice"));
        } catch (NumberFormatException e) {
            throw new CommandFormatException("BUY_TOO_LARGE");
        }

        float sellPrice;
        try {
            sellPrice = Float.parseFloat(matcher.group("sellPrice"));
        } catch (NumberFormatException e) {
            throw new CommandFormatException("SELL_TOO_LARGE");
        }

        assert quantity >= 0 : "Quantity should not be negative.";
        return new AddCommand(
                itemName,
                quantity,
                matcher.group("unitOfMeasurement"),
                category,
                buyPrice,
                sellPrice
        );
    }

    private Command prepareDelete(String args) throws CommandFormatException{
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.DEL);
        }
        String itemName = matcher.group("itemName").trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }
        return new DeleteCommand(itemName);
    }


    private Command prepareSell(String args) throws CommandFormatException{
        final Matcher matcher = SELL_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.SELL);
        }

        String itemName = matcher.group("itemName").toLowerCase().trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }

        int sellQuantity;
        try {
            sellQuantity = Integer.parseInt(matcher.group("sellQuantity").trim());
        } catch (NumberFormatException e) {
            throw new CommandFormatException("QTY_TOO_LARGE");
        }

        if (Promotionlist.isPromoExistNow(matcher.group("itemName"))) {
            float getDiscount = (Promotionlist.getPromotion(matcher.group("itemName"))).getDiscount();
            return new SellCommand(
                    matcher.group("itemName"),
                    sellQuantity,
                    getDiscount
            );
        } else {
            return new SellCommand(
                    itemName,
                    sellQuantity,
                    -1
            );
        }
    }

    private Command prepareFind(String args) throws CommandFormatException{
        final Matcher matcher = FIND_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.FIND);
        }
        String itemInfo = matcher.group("itemInfo") != null ? matcher.group("itemInfo").toLowerCase() : "NA";
        return new FindCommand(
                itemInfo,
                matcher.group("keyword"));
    }

    //@@author Fureimi
    private Command prepareEdit(String args) throws CommandFormatException, EditException {
        final Matcher matcher = EDIT_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.EDIT);
        }
        String itemName = matcher.group("itemName").toLowerCase().trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }

        // check if itemName was edited. If no, newItemName will be NA
        String newItemName = matcher.group("newItemName") != null ?
                matcher.group("newItemName").toLowerCase().trim() : "NA";
        if (newItemName.isBlank() || newItemName.isEmpty()) {
            throw new EditException("ITEM_NAME");
        }
        // check if quantity was edited. If no, newQuantity will be -1
        int newQuantity;
        try {
            newQuantity = matcher.group("newQuantity") != null ?
                    Integer.parseInt(matcher.group("newQuantity")) : -1;
            if (matcher.group("newQuantity") != null && newQuantity < 0) {
                throw new EditException("QUANTITY");
            }
        } catch (NumberFormatException e) {
            throw new CommandFormatException("QTY_TOO_LARGE");
        }

        // check if unitOfMeasurement was edited. If no, newUnitOfMeasurement will be NA
        String newUnitOfMeasurement = matcher.group("newUnitOfMeasurement") != null ?
                matcher.group("newUnitOfMeasurement") : "NA";
        if (newUnitOfMeasurement.isEmpty() || newUnitOfMeasurement.isBlank()) {
            throw new EditException("UNIT_OF_MEASUREMENT");
        }

        // check if category was edited. If no, newCategory will be NA
        String newCategory = matcher.group("newCategory") != null ? matcher.group("newCategory") : "NA";
        if (newCategory.isBlank() || newCategory.isEmpty()) {
            throw new EditException("CATEGORY");
        }
        // check if BuyPrice was edited. If no, newBuyPrice will be -1

        float newBuyPrice;
        try {
            newBuyPrice = matcher.group("newBuyPrice") != null ?
                    Float.parseFloat(matcher.group("newBuyPrice")) : -1;
            if (matcher.group("newBuyPrice") != null && newBuyPrice < 0) {
                throw new EditException("BUY_PRICE");
            }
        } catch (NumberFormatException e) {
            throw new CommandFormatException("BUY_TOO_LARGE");
        }

        // check if sellPrice was edited. If no, newSellPrice will be -1
        float newSellPrice;
        try {
            newSellPrice = matcher.group("newSellPrice") != null ?
                    Float.parseFloat(matcher.group("newSellPrice")) : -1;
            if (matcher.group("newSellPrice") != null && newSellPrice < 0) {
                throw new EditException("SELL_PRICE");
            }
        } catch (NumberFormatException e) {
            throw new CommandFormatException("SELL_TOO_LARGE");
        }
        return new EditCommand(
                itemName,
                newItemName,
                newQuantity,
                newUnitOfMeasurement,
                newCategory,
                newBuyPrice,
                newSellPrice
        );
    }

    private Command preparePromotion(String args) throws CommandFormatException, InvalidDateException {
        final Matcher matcher = PROMOTION_COMMAND_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.PROMOTION);
        }
        String itemName = matcher.group("itemName").toLowerCase().trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }
        float discount = Float.parseFloat(matcher.group("discount")) / 100;
        int startDate = Integer.parseInt(matcher.group("startDate"));
        String startMonth = matcher.group("startMonth");
        int startYear = Integer.parseInt(matcher.group("startYear"));
        int endDate = Integer.parseInt(matcher.group("endDate"));
        String endMonth = matcher.group("endMonth");
        int endYear = Integer.parseInt(matcher.group("endYear"));
        int startTime = Integer.parseInt(matcher.group("startTime"));
        int endTime = Integer.parseInt(matcher.group("endTime"));
        try {
            Month startMonthEnum = Month.valueOf(startMonth.toUpperCase());
            Month endMonthEnum = Month.valueOf(endMonth.toUpperCase());
            return new AddPromotionCommand(
                    itemName,
                    discount,
                    startDate,
                    Month.valueOf(startMonth.toUpperCase()),
                    startYear,
                    endDate,
                    Month.valueOf(endMonth.toUpperCase()),
                    endYear,
                    startTime,
                    endTime
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidDateException("INVALID_MONTH");
        }
    }

    private Command prepareDeletePromo(String args) throws CommandFormatException{
        final Matcher matcher = DELETE_PROMO_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.DEL_PROMO);
        }
        return new DeletePromotionCommand(matcher.group("itemName").toLowerCase());
    }
    private Command prepareItemList(String args) throws CommandFormatException {
        final Matcher matcher = LIST_ITEM_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.LIST_ITEMS);
        }
        String category = matcher.group("category") != null ? matcher.group("category").toLowerCase().trim() : "NA";
        if (category.isEmpty()) {
            throw new CommandFormatException("INVALID_CATEGORY");
        }
        boolean listMarked = matcher.group("isMark") != null;
        return new ListCommand<>(Itemlist.getItems(), category, listMarked);
    }

    private Command preparePromotionList() {
        return new ListCommand<>(Promotionlist.getAllPromotion());
    }

    private Command prepareMark(String args) throws CommandFormatException {
        final Matcher matcher = MARK_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.MARK);
        }
        String itemName = matcher.group("itemName").toLowerCase().trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }
        return new MarkCommand(itemName);
    }

    private Command prepareUnmark(String args) throws CommandFormatException {
        final Matcher matcher = UNMARK_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.UNMARK);
        }
        String itemName = matcher.group("itemName").toLowerCase().trim();
        if (itemName.isEmpty()) {
            throw new CommandFormatException("INVALID_ITEM_NAME");
        }
        return new UnmarkCommand(itemName);
    }

    private Command prepareTransactionList(String args) throws CommandFormatException {
        final Matcher matcher = LIST_TRANSACTION_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.LIST_TRANSACTIONS);
        }
        if (matcher.group("itemName") == null) {
            return new ListCommand<>(Cashier.getTransactions(), "NA");
        }

        String itemName = matcher.group(1).trim();
        return new ListCommand<>(Cashier.getTransactions(), itemName);
    }

    private Command prepareLowStock(String args) throws CommandFormatException{
        final Matcher matcher = LOW_STOCK_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.LOW_STOCK);
        }

        int amount;
        try {
            amount = Integer.parseInt(matcher.group("amount"));
        } catch (NumberFormatException e) {
            throw new CommandFormatException("INVALID_LOW_STOCK_AMOUNT");
        }

        return new LowStockCommand(amount);
    }

}



