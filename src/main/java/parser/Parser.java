package parser;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.DeletePromotionCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.AddPromotionCommand;
import command.MarkCommand;
import command.SellCommand;
import command.UnmarkCommand;
import command.BestsellerCommand;
import command.TotalProfitCommand;
import common.Messages;
import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import itemlist.Cashier;
import itemlist.Itemlist;
import promotion.Month;
import promotion.Promotionlist;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
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
            Pattern.compile("list_txn+\\s?(?:void/[NY])*");

    public Command parseInput(String userInput){
        final CommandType userCommand;
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            System.out.println(Messages.INVALID_COMMAND);
            System.out.println(Messages.HELP);
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
            return new HelpCommand();
        case LIST_ITEMS:
            try {
                return prepareItemList(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case LIST_PROMOTIONS:
            return preparePromotionList();
        case LIST_TRANSACTIONS:
            try {
                return prepareTransactionList(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case DEL_PROMO:
            try {
                return prepareDeletePromo(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case ADD:
            try {
                return prepareAdd(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case DEL:
            try {
                return prepareDelete(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case EDIT:
            try {
                return prepareEdit(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case FIND:
            try {
                return prepareFind(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case SELL:
            try {
                return prepareSell(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case PROMOTION:
            try {
                return preparePromotion(userInput);
            } catch (CommandFormatException | InvalidDateException e) {
                break;
            }
        case MARK:
            try {
                return prepareMark(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case UNMARK:
            try {
                return prepareUnmark(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case TOTAL_PROFIT:
            //fallthrough
        case TOTAL_REVENUE:
            return new TotalProfitCommand(userCommand);
        case BESTSELLER:
            return new BestsellerCommand();
        default:
            System.out.println(Messages.INVALID_COMMAND);
            return new IncorrectCommand();
        }
        return new IncorrectCommand();
    }


    private Command prepareAdd(String args) throws CommandFormatException{
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.ADD);
        }
        String category = matcher.group("category") != null ? matcher.group("category") : "NA";
        int quantity = Integer.parseInt(matcher.group("quantity"));
        float buyPrice = Float.parseFloat(matcher.group("buyPrice"));
        float sellPrice = Float.parseFloat(matcher.group("sellPrice"));
        assert quantity >= 0 : "Quantity should not be negative.";
        return new AddCommand(
                matcher.group("itemName"),
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
        return new DeleteCommand(matcher.group("itemName"));
    }


    private Command prepareSell(String args) throws CommandFormatException{
        final Matcher matcher = SELL_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.SELL);
        }
        int sellQuantity = Integer.parseInt(matcher.group("sellQuantity").trim());
        if (Promotionlist.isOnPromo(matcher.group("itemName"))) {
            float getDiscount = (Promotionlist.getPromotion(matcher.group("itemName"))).getDiscount();
            return new SellCommand(
                    matcher.group("itemName"),
                    sellQuantity,
                    getDiscount
            );
        } else {
            return new SellCommand(
                    matcher.group("itemName"),
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
        String itemInfo = matcher.group("itemInfo") != null ? matcher.group("itemInfo") : "NA";
        return new FindCommand(
                itemInfo,
                matcher.group("keyword"));
    }

    //@@author Fureimi
    private Command prepareEdit(String args) throws CommandFormatException{
        final Matcher matcher = EDIT_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.EDIT);
        }
        String itemName = matcher.group("itemName");
        // check if itemName was edited. If no, newItemName will be NA
        String newItemName = matcher.group("newItemName") != null ? matcher.group("newItemName") : "NA";
        // check if quantity was edited. If no, newQuantity will be -1
        int newQuantity = matcher.group("newQuantity") != null ?
                Integer.parseInt(matcher.group("newQuantity")) : -1;
        // check if unitOfMeasurement was edited. If no, newUnitOfMeasurement will be NA
        String newUnitOfMeasurement = matcher.group("newUnitOfMeasurement") != null ?
                matcher.group("newUnitOfMeasurement") : "NA";
        // check if category was edited. If no, newCategory will be NA
        String newCategory = matcher.group("newCategory") != null ? matcher.group("newCategory") : "NA";
        // check if BuyPrice was edited. If no, newBuyPrice will be -1
        float newBuyPrice = matcher.group("newBuyPrice") != null ?
                Float.parseFloat(matcher.group("newBuyPrice")) : -1;
        // check if sellPrice was edited. If no, newSellPrice will be -1
        float newSellPrice = matcher.group("newSellPrice") != null ?
                Float.parseFloat(matcher.group("newSellPrice")) : -1;
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
        String itemName = matcher.group("itemName");
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
        return new DeletePromotionCommand(matcher.group("itemName"));
    }
    private Command prepareItemList(String args) throws CommandFormatException {
        final Matcher matcher = LIST_ITEM_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.LIST_ITEMS);
        }
        String category = matcher.group("category") != null ? matcher.group("category") : "NA";
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
        String itemName = matcher.group("itemName");
        return new MarkCommand(itemName);
    }

    private Command prepareUnmark(String args) throws CommandFormatException {
        final Matcher matcher = UNMARK_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.UNMARK);
        }
        String itemName = matcher.group("itemName");
        return new UnmarkCommand(itemName);
    }

    private Command prepareTransactionList(String args) throws CommandFormatException {
        final Matcher matcher = LIST_TRANSACTION_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(Messages.INVALID_COMMAND);
        }
        //is true if void/Y
        boolean isVoided = matcher.group("isVoid").equals("Y");

        return new ListCommand<>(Cashier.getTransactions(), isVoided);
    }
}



