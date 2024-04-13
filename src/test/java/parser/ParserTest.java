package parser;

import command.AddPromotionCommand;
import command.Command;
import command.AddCommand;
import command.EditCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SellCommand;
import command.UnmarkCommand;
import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import promotion.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
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
    public void testParseHelpCommand() {
        String userInput = "help";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    public void testParsePromotionCommand() {
        String userInput = "promotion apple discount/10 period /from 2 Apr 2024 /to " +
                "3 Apr 2024 time /from 0000 /to 2359";
        Command command = parser.parseInput(userInput);
        assertInstanceOf(AddPromotionCommand.class, command);
    }
}


