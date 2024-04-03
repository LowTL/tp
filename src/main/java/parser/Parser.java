package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.PromotionCommand;
import command.SellCommand;
import common.Messages;
import exceptions.CommandFormatException;
import itemlist.Itemlist;
import promotion.Month;
import promotion.Promotionlist;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern ADD_COMMAND_FORMAT =
            Pattern.compile("add (?<itemName>[^/]+) qty/(?<quantity>\\d+) /(?<uom>[^/]+)" +
                    "(?: cat/(?<category>[^/]+))? buy/(?<buyPrice>\\d*\\.?\\d+) sell/(?<sellPrice>\\d*\\.?\\d+)");


    public static final Pattern DELETE_COMMAND_FORMAT =
            Pattern.compile("del (?<itemName>[^/]+)");

    public static final Pattern EDIT_COMMAND_FORMAT =
        Pattern.compile("edit (?<itemName>[^/]+)" +
                "(?:\\s+(name/(?<newItemName>[^/]+)|qty/(?<newQuantity>\\d+)|uom/(?<newUom>[^/]+)|" +
                "cat/(?<newCategory>[^/]+)|buy/(?<newBuyPrice>\\d*\\.?\\d+)|sell/(?<newSellPrice>\\d*\\.?\\d+)))+");

    public static final Pattern SELL_COMMAND_FORMAT =
            Pattern.compile("sell (?<itemName>[^/]+) qty/(?<sellQuantity>\\d+)");

    public static final Pattern FIND_COMMAND_FORMAT =
            Pattern.compile("find(?: /(?<itemInfo>[^/]+))* (?<keyword>[^/]+)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern LIST_COMMAND_FORMAT =
            Pattern.compile("list(?: (?<category>[^/]+))?");

    final Pattern PROMOTION_COMMAND_FORMAT = Pattern.compile(
            "promotion (?<itemName>[^\\s]+) discount/(?<discount>\\d+(\\.\\d{1,2})?) " +
                    "period /from (?<startDate>\\d+) (?<startMonth>\\w+) (?<startYear>\\d+) " +
                    "/to (?<endDate>\\d+) (?<endMonth>\\w+) (?<endYear>\\d+) " +
                    "time /from (?<startTime>\\d+) /to (?<endTime>\\d+)");

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
        case LIST:
            try {
                return prepareList(userInput);
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
            } catch (CommandFormatException e) {
                break;
            }
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
                matcher.group("uom"),
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
        boolean sellPriceIsPresent = matcher.group("sellPrice") != null;
        int inputPrice = (sellPriceIsPresent) ? Integer.parseInt(matcher.group("sellPrice")): 0;
        if (sellPriceIsPresent && inputPrice < 0) {
            throw new CommandFormatException("SELL_PRICE");
        }
        if (Promotionlist.isOnPromo(matcher.group("itemName"))) {
            return new SellCommand(
                    matcher.group("itemName"),
                    sellQuantity,
                    Promotionlist.getPromotion("itemName").getDiscount()
            );
        } else {
            return new SellCommand(
                    matcher.group("itemName"),
                    sellQuantity,
                    -1
            );
        }

        /*int sellPrice = sellPriceIsPresent ? inputPrice : -1;
        return new SellCommand(
                matcher.group("itemName"),
                sellQuantity,
                sellPrice
        );*/
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
        String newItemName = matcher.group("newItemName") != null ? matcher.group("newItemName") : "NA";
        int newQuantity = matcher.group("newQuantity") != null ?
                Integer.parseInt(matcher.group("newQuantity")) : -1;
        String newUom = matcher.group("newUom") != null ? matcher.group("newUom") : "NA";
        String newCategory = matcher.group("newCategory") != null ? matcher.group("newCategory") : "NA";
        float newBuyPrice = matcher.group("newBuyPrice") != null ?
                Float.parseFloat(matcher.group("newBuyPrice")) : -1;
        float newSellPrice = matcher.group("newSellPrice") != null ?
                Float.parseFloat(matcher.group("newSellPrice")) : -1;
        return new EditCommand(
                itemName,
                newItemName,
                newQuantity,
                newUom,
                newCategory,
                newBuyPrice,
                newSellPrice
        );
    }

    private Command preparePromotion(String args) throws CommandFormatException {
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
        System.out.println(Month.valueOf(startMonth.toUpperCase()));
        return new PromotionCommand(
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
    }

    private Command prepareList(String args) throws CommandFormatException {
        final Matcher matcher = LIST_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.LIST);
        }
        String category = matcher.group("category") != null ? matcher.group("category") : "NA";
        return new ListCommand<>(Itemlist.getItems(), category);
    }
}



