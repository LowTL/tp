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
import command.IncorrectCommand;
import command.ListCommand;
import command.LowStockCommand;
import command.MarkCommand;
import command.SellCommand;
import command.TotalProfitCommand;
import command.UnmarkCommand;
import common.HelpMessages;
import common.Messages;
import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import itemlist.Itemlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import promotion.Month;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @AfterEach
    void tearDown() {
        // This will be run after each test, cleaning up
        Itemlist.getItems().clear(); // clear the list for next test
        Promotionlist.getAllPromotion().clear();
        Storage.updateFile("", false);
        PromotionStorage.updateFile("", false);
        TransactionLogs.updateFile("", false);
    }

    @Test
    public void testParseCommandWithBlankText() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = " ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_COMMAND  + System.lineSeparator() +
                HelpMessages.HELP + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }
    @Test
    public void testParseAddCommandWithBlankText() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_ADD_FORMAT + System.lineSeparator();
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
        String expectedMessage = Messages.INVALID_ADD_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithInvalidQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/a /pc cat/test buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_ADD_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithLargeQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/9999999999999999999 /pc cat/test buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = Messages.QTY_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithBlankCategory() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/ buy/1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_ADD_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithLargeBuy() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/999999999999999999999999999 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = Messages.BUY_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithNegativeBuy() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/-1 sell/2";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_ADD_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithLargeSell() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/1 sell/99999999999999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = Messages.SELL_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseAddCommandWithNegativeSell() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "add itemname qty/1 /pc cat/test buy/1 sell/-2";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_ADD_FORMAT + System.lineSeparator();
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
        String expectedMessage = Messages.INVALID_HELP_FORMAT + System.lineSeparator();
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
    public void testParseHelpCommandWithValidCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "help c/add";
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
        String expectedMessage = Messages.INVALID_DELETE_FORMAT + System.lineSeparator();
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
    public void testParseDeleteCommandWithValidItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "del validItemName";
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
        String expectedMessage = Messages.INVALID_SELL_FORMAT + System.lineSeparator();
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
    public void testParseSellCommandWithValidItemNameAndInvalidQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "sell validItemName qty/a";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_SELL_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseSellCommandWithValidItemNameAndLargeQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "sell validItemName qty/9999999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = Messages.QTY_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit  ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_EDIT_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithValidItemNameAndValidQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName qty/10";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithNegativeQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName qty/-1";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_EDIT_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithLargeQuantity() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName qty/99999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = Messages.QTY_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithValidUOM() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName uom/kg";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithInvalidUOM() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName uom/1";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithBlankCat() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName cat/ ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_EDIT_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithInvalidCat() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName cat/1";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithNegativeBuy() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName buy/-1";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_EDIT_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithLargeBuy() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName buy/99999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = Messages.BUY_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithNegativeSell() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName sell/-1";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_EDIT_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseEditCommandWithLargeSell() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "edit validItemName sell/99999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = Messages.SELL_TOO_LARGE + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseFindCommandWithBlankInfo() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "find ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_FIND_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseFindCommandWithBlankKeyword() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "find /info ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_FIND_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParsePromotionCommandWithInvalidDiscount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "promotion apple discount/invalid period /from 2 Apr 2024 /to " +
                "3 Apr 2024 time /from 0000 /to 2359";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_PROMOTION_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParsePromotionCommandWithInvalidPeriodFormat() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "promotion apple discount/10 period /invalidformat";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_PROMOTION_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseMarkCommandWithValidItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "mark validItemName";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseMarkCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "mark  ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_MARK_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseMarkCommandWithInvalidItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "mark invalidItemName";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseUnmarkCommandWithValidItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "unmark validItemName";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseUnmarkCommandWithBlankItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "unmark  ";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_UNMARK_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseUnmarkCommandWithInvalidItemName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "unmark invalidItemName";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseListItemsCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "list_items";
        parser.parseInput(userInput);
        String expectedMessage = Messages.EMPTY_ITEM_LIST + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseListPromotionsCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "list_promotions";
        parser.parseInput(userInput);
        String expectedMessage = Messages.EMPTY_PROMOTION_LIST + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseTotalProfitCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "total_profit";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseTotalRevenueCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "total_revenue";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseBestsellerCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "bestseller";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseLowStockCommandWithValidAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /10";
        parser.parseInput(userInput);
        String expectedMessage = "";
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }
    @Test
    public void testParseLowStockCommandWithBlankAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_LOW_STOCK_FORMAT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseLowStockCommandWithLargeAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /99999999999999999999";
        parser.parseInput(userInput);
        String expectedMessage = Messages.INVALID_LOW_STOCK_AMOUNT + System.lineSeparator();
        assertEquals(expectedMessage, outputStreamCaptor.toString());
    }

    @Test
    public void testParseLowStockCommandWithNegativeAmount() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String userInput = "low_stock /-1";
        parser.parseInput(userInput);
        String expectedMessage = Messages.NEGATIVE_LOW_STOCK_AMOUNT + System.lineSeparator();
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
        String userInput = "find /item Keyword";
        Command command = parser.parseInput(userInput);
        assertTrue(command instanceof FindCommand);
        FindCommand findCommand = (FindCommand) command;
        assertEquals("item", findCommand.getItemInfo());
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
        Command delCommand1 = new DeleteCommand("lemon");
        Command delCommand2 = new DeleteCommand(" ");
        try {
            addCommand.execute();
            delCommand1.execute();
            delCommand2.execute();
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
    public void testParseTotalProfitCommand2() {
        String userInput = "total_profit";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(TotalProfitCommand.class, command);
    }

    @Test
    public void testParseBestsellerCommand2() {
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


