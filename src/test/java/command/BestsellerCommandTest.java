package command;

import common.Messages;
import exceptions.CommandFormatException;
import itemlist.Cashier;
import itemlist.Itemlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.EmptyListException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BestsellerCommandTest {

    private final BestsellerCommand bestsellerCommand = new BestsellerCommand();

    @BeforeEach
    public void reset() {
        //clears the transactions list
        Cashier.transactions.clear();

        while (Itemlist.getItem(0) != null) {
            Itemlist.deleteItem(0);
        }
    }

    @Test
    public void testExecuteWithEmptyBestseller() throws EmptyListException {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            bestsellerCommand.execute();
            String expected = Messages.NO_BESTSELLER + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testExecuteWithBestseller() throws EmptyListException, CommandFormatException {
        //set up an item to test bestseller
        AddCommand testAdd = new AddCommand("testItem", 10, "ea","NA", 10.0F, 10.0F);
        testAdd.execute();
        SellCommand testSell = new SellCommand("testItem", 10, 0);
        testSell.execute();
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            bestsellerCommand.execute();
            String expectedOutput = "The current best-selling item is testItem" + System.lineSeparator();
            assertEquals(expectedOutput, outputStream.toString());
        } catch (EmptyListException e) {
            // It's not supposed to throw EmptyListException in this case
            fail("Unexpected EmptyListException thrown");
        }

    }

}
