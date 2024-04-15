package command;

import common.Messages;
import exceptions.CommandFormatException;
import itemlist.Cashier;
import itemlist.Itemlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.CommandType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalProfitCommandTest {

    @BeforeEach
    public void reset() {
        //clears the transactions list
        Cashier.transactions.clear();

        while (Itemlist.getItem(0) != null) {
            Itemlist.deleteItem(0);
        }
    }

    @Test
    public void testProfitWithNoItems() {
        TotalProfitCommand totalProfitCommand = new TotalProfitCommand(CommandType.TOTAL_PROFIT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        totalProfitCommand.execute();
        String expected = Messages.NO_BESTSELLER + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void testProfitWithItems() throws CommandFormatException {
        //set up an item to test bestseller
        AddCommand testAdd = new AddCommand("testItem", 10, "ea","NA", 10.0F, 20.0F);
        testAdd.execute();
        SellCommand testSell = new SellCommand("testItem", 10, 0);
        testSell.execute();
        TotalProfitCommand totalProfitCommand = new TotalProfitCommand(CommandType.TOTAL_PROFIT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        totalProfitCommand.execute();
        String expected = "You have earned 100.0 in profits so far." + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void testRevenueWithNoItems() {
        TotalProfitCommand totalProfitCommand = new TotalProfitCommand(CommandType.TOTAL_REVENUE);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        totalProfitCommand.execute();
        String expected = Messages.NO_BESTSELLER + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void testRevenueWithItems() throws CommandFormatException {
        //set up an item to test bestseller
        AddCommand testAdd = new AddCommand("testItem", 10, "ea","NA", 10.0F, 20.0F);
        testAdd.execute();
        SellCommand testSell = new SellCommand("testItem", 10, 0);
        testSell.execute();
        TotalProfitCommand totalProfitCommand = new TotalProfitCommand(CommandType.TOTAL_REVENUE);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        totalProfitCommand.execute();
        String expected = "You have earned 200.0 in revenue so far." + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }
}
