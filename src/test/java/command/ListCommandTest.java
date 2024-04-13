package command;

import common.Messages;
import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import item.Item;
import item.Transaction;
import itemlist.Cashier;
import itemlist.Itemlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import promotion.Promotion;
import promotion.Promotionlist;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest extends Cashier {

    //clears all the lists
    @BeforeEach
    public void reset() {
        while (!Promotionlist.getAllPromotion().isEmpty()) {
            Promotionlist.deletePromotion(0);
        }
        while (Itemlist.getItem(0) != null) {
            Itemlist.deleteItem(0);
        }
        while (Cashier.getTransaction(0) != null) {
            transactions = new ArrayList<>();
        }
    }

    //happy case: has item
    @Test
    public void listCommandTest_itemList_correct() {
        Item test = new Item("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        Itemlist.addItem(test);
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "NA", false);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = "List: " + System.lineSeparator() + "1. " + test + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            Assertions.fail("Unexpected EmptyListException thrown.");
            throw new RuntimeException(e);
        }

    }

    //Happy case: has 1 transaction
    @Test
    public void listCommandTest_transactionList_correct() {
        AddCommand addCommand = new AddCommand("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        addCommand.execute();
        SellCommand sellCommand = new SellCommand("testItem", 1, 0);
        ListCommand listCommand1 = new ListCommand(transactions, "NA");
        try {
            sellCommand.execute();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = "1. " + transactions.get(0) + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            Assertions.fail("Unexpected EmptyListException thrown.");
            throw new RuntimeException(e);
        } catch (CommandFormatException e) {
            Assertions.fail("Unexpected CommandFormatException thrown");
            throw new RuntimeException(e);
        }

    }

    //happy case: filtered item successfully
    @Test
    public void listCommandTest_transactionList_correct2() {
        AddCommand addCommand = new AddCommand("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        addCommand.execute();
        SellCommand sellCommand = new SellCommand("testItem", 1, 0);
        try {
            sellCommand.execute();
            ListCommand listCommand1 = new ListCommand(transactions, "testItem");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = "1. " + transactions.get(0) + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            Assertions.fail("Unexpected EmptyListException thrown.");
            throw new RuntimeException(e);
        } catch (CommandFormatException e) {
            Assertions.fail("Unexpected CommandFormatException thrown");
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listCommandTest_transactionList_correct3() {
        AddCommand addCommand = new AddCommand("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        addCommand.execute();
        SellCommand sellCommand = new SellCommand("testItem", 1, 0);
        ListCommand listCommand1 = new ListCommand(transactions, "failTest");
        try {
            sellCommand.execute();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = Messages.EMPTY_LIST + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            Assertions.fail("Unexpected EmptyListException thrown.");
            throw new RuntimeException(e);
        } catch (CommandFormatException e) {
            Assertions.fail("Unexpected CommandFormatException thrown");
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listCommandTest_itemList_empty() {
        ListCommand listCommand1 = new ListCommand(new ArrayList<Item>(), "NA", false);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = Messages.EMPTY_LIST + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listCommandTest_transactionList_empty() {
        ListCommand listCommand1 = new ListCommand(new ArrayList<Transaction>(), "NA");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = Messages.EMPTY_LIST + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listCommandTest_promotionList_empty() {
        ListCommand listCommand1 = new ListCommand(new ArrayList<Promotion>());
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            listCommand1.execute();
            String expected = Messages.EMPTY_LIST + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }

    }
}
