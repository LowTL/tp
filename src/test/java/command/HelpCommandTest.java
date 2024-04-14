package command;

import common.HelpMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testExecuteWithInvalidCommand() {
        String command = "invalid_command";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.INVALID_HELP_COMMAND + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithNoCommand() {
        String command = "NA";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithBlank() {
        String command = " ";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.INVALID_HELP_COMMAND + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithListItemsCommand() {
        String command = "list items";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_LIST_ITEMS + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithAddCommand() {
        String command = "add";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_ADD + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithSellCommand() {
        String command = "sell";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_SELL + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithEditCommand() {
        String command = "edit";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_EDIT + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithFindCommand() {
        String command = "find";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_FIND + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithMarkCommand() {
        String command = "mark";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_MARK + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithUnmarkCommand() {
        String command = "unmark";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_UNMARK+ System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithDeleteCommand() {
        String command = "delete";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_DEL + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithBestSellerCommand() {
        String command = "bestseller";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_BESTSELLER + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithTotalProfitCommand() {
        String command = "total profit";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_TOTAL_PROFIT + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithTotalRevenueCommand() {
        String command = "total revenue";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_TOTAL_REVENUE + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithPromotionCommand() {
        String command = "promotion";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_PROMOTION + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithDeletePromotionCommand() {
        String command = "delete promotion";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_DEL_PROMO + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithListPromotionCommand() {
        String command = "list promotion";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_LIST_PROMO + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithLowStockCommand() {
        String command = "low stock";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_LOW_STOCK + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithListTransactionsCommand() {
        String command = "list transactions";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_LIST_TRANSACTIONS + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithExitCommand() {
        String command = "exit";
        HelpCommand helpCommand = new HelpCommand(command);
        helpCommand.execute();
        assertEquals(HelpMessages.HELP_EXIT + System.lineSeparator(), outputStreamCaptor.toString());
    }

}

