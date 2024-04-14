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
import exceptions.CommandFormatException;
import exceptions.EditException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import promotion.Month;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParseCommandWithBlankText() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = " ";
        parser.parseInput(userInput);
        String expectedMessage =
                "Invalid command detected. Type 'help' for list of valid commands"  + System.lineSeparator() +
                " ___________________________________________________________________________________________\n" +
                "|                                        STOCKMASTER                                        |\n" +
                "|___________________________________________________________________________________________|\n" +
                "| Commands   | Format                                                                       |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| list items | list_items                                                                   |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| add        | add ITEM_NAME qty/QUANTITY_OF_ITEM /UNIT_OF_MEASUREMENT cat/[CATEGORY]       |\n" +
                "|            |     buy/BUY_PRICE sell/SELL_PRICE                                            |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| sell       | sell ITEM_NAME qty/SELL_QUANTITY                                             |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| edit       | edit ITEM_NAME name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM]              |\n" +
                "|            |      cat/[NEW_CATEGORY] buy/[NEW_BUY_PRICE] SELL/[NEW_SELL_PRICE]            |\n" +
                "|            |      (use AT LEAST 1 of: name/ qty/, uom/, cat/, buy/, sell/)                |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| mark       | mark ITEM_NAME                                                               |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| unmark     | unmark ITEM_NAME                                                             |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| delete     | del ITEM_NAME                                                                |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| find       | 1. find KEYWORD  - to search the entire Item List                            |\n" +
                "|            | 2. find /filter1/filter2 KEYWORD  - to search under the filters*             |\n" +
                "|            |    * (filters: item, qty, uom, cat, buy, sell)                               |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| bestseller | bestseller                                                                   |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| total      | total_profit                                                                 |\n" +
                "| profit     |                                                                              |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| total      | total_revenue                                                                |\n" +
                "| revenue    |                                                                              |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| promotion  | promotion ITEM_NAME discount/DISCOUNT period /from DD MMM YYYY               |\n" +
                "|            | to DD MMM YYYY time /from TIME /to TIME                                      |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| delete     | del_promo ITEM_NAME                                                          |\n" +
                "| promotion  |                                                                              |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| list       | list_promotions                                                              |\n" +
                "| promotions |                                                                              |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| low stock  | low_stock /AMOUNT                                                            |\n" +
                "|------------|------------------------------------------------------------------------------|\n" +
                "| exit       | exit                                                                         |\n" +
                "|____________|______________________________________________________________________________|\n" +
                "* type help c/COMMAND for more detailed explanations\n" +
                "  (use the command names on the left column)" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }
    @Test
    public void testParseAddCommandWithBlankText() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithValidItem() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add  ";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithInvalidQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/a /pc cat/test buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithLargeQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/9999999999999999999 /pc cat/test buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = "Quantity is too large. Please input a smaller quantity." + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithBlankCategory() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/ buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithLargeBuy() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/999999999999999999999999999 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = "Buy price is too large. Please input a smaller buy price." + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithNegativeBuy() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/-1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithLargeSell() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/1 sell/99999999999999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = "Sell price is too large. Please input a smaller sell price." + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithNegativeSell() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/1 sell/-2";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseHelpCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "help";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseHelpCommandWithBlankCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "help c/ ";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: 'help' or " +
                "'help c/[COMMAND]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseHelpCommandWithInvalidCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "help c/invalid ";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseDeleteCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "del ";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: 'del [ITEM_NAME]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseDeleteCommandWithItemThatDoesNotExist() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "del non existent item";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseSellCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "sell ";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " +
                "'sell [ITEM_NAME] qty/[SELL_QUANTITY]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseSellCommandWithItemThatDoesNotExist() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "sell non existent item qty/1";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit  ";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid edit command format. Please use format: " +
                "'edit [ITEM_NAME] name/[NEW_NAME] qty/[NEW_QUANTITY] uom/[NEW_UOM] cat/[NEW_CATEGORY] " +
                "buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]'\n" + "You can edit at least 1 parameter up to all available" +
                " parameters. For example, if you only wish to update buy and sell price, you can input:\n" +
                "'edit [ITEM_NAME] buy/[NEW_BUY_PRICE] sell/[NEW_SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseLowStockCommandWithBlankAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid Command Format. Please use format: low_stock /AMOUNT" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseLowStockCommandWithLargeAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /99999999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = "Please input a valid amount." + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseLowStockCommandWithNegativeAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /-1";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommand() {
        String userInput = "add ItemName qty/10 /unitOfMeasurement cat/Category buy/10.5 sell/15.0";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("itemname", addCommand.getItemName());
        assertEquals(10, addCommand.getQuantity());
        assertEquals("unitOfMeasurement", addCommand.getUnitOfMeasurement());
        assertEquals("Category", addCommand.getCategory());
        assertEquals(10.5f, addCommand.getBuyPrice(), 0.01);
        assertEquals(15.0f, addCommand.getSellPrice(), 0.01);
    }

    @Test
    public void testParseEditCommand() {
        String userInput = "edit ItemName name/NewName qty/20 uom/NewUOM cat/NewCategory buy/15.0 sell/20.0";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(EditCommand.class, command);
        EditCommand editCommand = (EditCommand) command;
        assertEquals("itemname", editCommand.getItemName());
        assertEquals("newname", editCommand.getNewItemName());
        assertEquals(20, editCommand.getNewQuantity());
        assertEquals("NewUOM", editCommand.getNewUnitOfMeasurement());
        assertEquals("NewCategory", editCommand.getNewCategory());
        assertEquals(15.0f, editCommand.getNewBuyPrice(), 0.01);
        assertEquals(20.0f, editCommand.getNewSellPrice(), 0.01);
    }

    @Test
    public void testParseSellCommand1() {
        String userInput = "sell ItemName qty/5";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(SellCommand.class, command);
        SellCommand sellCommand = (SellCommand) command;
        assertEquals("itemname", sellCommand.getItemName());
        assertEquals(5, sellCommand.getSellQuantity());
    }

    @Test
    public void testParseSellCommand2() {
        Command addCommandTest1 = new AddCommand("lemon", 5, "fruits",
                "NA", 1, 1);
        Command promotionTest1 = new AddPromotionCommand("lemon", 0.3F, 2, Month.valueOf("FEB"),
                2024, 4, Month.valueOf("APR"), 2025, 0000, 2359);
        try {
            promotionTest1.execute();
            addCommandTest1.execute();
        } catch (CommandFormatException | InvalidDateException | EmptyListException e) {
            throw new RuntimeException(e);
        }
        String userInput1 = "sell lemon qty/1";
        Command command1 = parser.parseInput(userInput1);
        assertInstanceOf(SellCommand.class, command1);
        SellCommand sellCommand = (SellCommand) command1;
        assertEquals("lemon", sellCommand.getItemName());
        assertEquals(1, sellCommand.getSellQuantity());
    }

    @Test
    public void testParseFindCommand() {
        String userInput = "find /info Keyword";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(FindCommand.class, command);
        FindCommand findCommand = (FindCommand) command;
        assertEquals("info", findCommand.getItemInfo());
        assertEquals("keyword", findCommand.getKeyword());
    }

    @Test
    public void testPrepareFindCommand() {
        // Test valid find command
        String userInput = "find /itemInfo Keyword";
        Command command = parser.parseInput(userInput);
        assertTrue(command instanceof FindCommand);
        FindCommand findCommand = (FindCommand) command;
        assertEquals("iteminfo", findCommand.getItemInfo());
        assertEquals("keyword", findCommand.getKeyword());

        // Test find command without specifying item info
        userInput = "find Keyword";
        command = parser.parseInput(userInput);
        assertTrue(command instanceof FindCommand);
        findCommand = (FindCommand) command;
        assertEquals("NA", findCommand.getItemInfo());
        assertEquals("keyword", findCommand.getKeyword());
    }

    @Test
    public void testParseListCommand() {
        String userInput = "list_items cat/Category";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(ListCommand.class, command);
        ListCommand listCommand = (ListCommand) command;
        assertEquals("category", listCommand.getCategory());
    }

    @Test
    public void testParseMarkCommand() {
        String userInput = "mark ItemName";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals("itemname", markCommand.getItemName());
    }

    @Test
    public void testParseUnmarkCommand() {
        String userInput = "unmark ItemName";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(UnmarkCommand.class, command);
        UnmarkCommand unmarkCommand = (UnmarkCommand) command;
        assertEquals("itemname", unmarkCommand.getItemName());
    }

    @Test
    public void testParsePromotionCommand() {
        String userInput = "promotion apple discount/10 period /from 2 Apr 2024 /to " +
                "3 Apr 2024 time /from 0000 /to 2359";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(AddPromotionCommand.class, command);
    }


    @Test
    public void testPrepareDeleteCommand() {
        Command addCommand = new AddCommand("lemon", 5, "fruits",
                "NA", 1, 1);
        Command DelCommand1 = new DeleteCommand("lemon");
        Command DelCommand2 = new DeleteCommand(" ");
        try {
            addCommand.execute();
            DelCommand1.execute();
            DelCommand2.execute();
        } catch (CommandFormatException | InvalidDateException | EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLowStockCommand() {
        // Test valid low stock command
        String userInput1 = "low_stock /10";
        Command command1 = parser.parseInput(userInput1);
        assertTrue(command1 instanceof LowStockCommand);
        LowStockCommand lowStockCommand1 = (LowStockCommand) command1;
        assertEquals(10, lowStockCommand1.getAmount());
    }

    @Test
    public void testParseExitCommand() {
        String userInput = "exit";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void testParseIncorrectCommand() {
        String userInput = "incorrect";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(IncorrectCommand.class, command);
    }

    @Test
    public void testParseTotalProfitCommand() {
        String userInput = "total_profit";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(TotalProfitCommand.class, command);
    }

    @Test
    public void testParseBestsellerCommand() {
        String userInput = "bestseller";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(BestsellerCommand.class, command);
    }

    @Test
    public void testParseDeletePromotionCommand() {
        String userInput = "del_promo ItemName";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(DeletePromotionCommand.class, command);
    }

    @Test
    public void testPrepareAddWithInvalidFormat() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemName";
        parser.parseInput(userInput);
        String expectedMessage = "Invalid command format. Please use format: " + "\n" +
                "'add [ITEM_NAME] qty/[QUANTITY_OF_ITEM] /[UNIT_OF_MEASUREMENT] cat/[CATEGORY] " +
                "buy/[BUY_PRICE] sell/[SELL_PRICE]'" + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseUnrecognizedCommand() {
        String userInput = "unknown command";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(IncorrectCommand.class, command);
    }

    @Test
    public void testPrepareDeletePromo() {
        String userInput = "del_promo ItemName";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(DeletePromotionCommand.class, command);
        DeletePromotionCommand deletePromotionCommand = (DeletePromotionCommand) command;
        assertEquals("itemname", deletePromotionCommand.getItemName());
    }

}


