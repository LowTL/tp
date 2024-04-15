package command;

import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import item.Item;
import item.Transaction;
import itemlist.Cashier;
import itemlist.Itemlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import promotion.Month;
import promotion.Promotion;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

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
        Storage.updateFile("", false);
        PromotionStorage.updateFile("", false);
        TransactionLogs.updateFile("", false);
    }

    //happy case: has item
    @Test
    public void listCommandTest_itemList_correct() {
        Item test = new Item("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        Itemlist.addItem(test);
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "NA", false);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "List: " + System.lineSeparator() + "1. " + test + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }


    //happy case: has item, filtered correctly
    @Test
    public void listCommandTest_itemList_correct2() {
        Item test = new Item("testItem", 1, "ea", "test", 1.00F, 2.00F);
        Itemlist.addItem(test);
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "test", false);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "1. Item Index: 1. " + test + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    //happy case: has item, filtered wrongly
    @Test
    public void listCommandTest_itemList_correct3() {
        Item test = new Item("testItem", 1, "ea", "noCat", 1.00F, 2.00F);
        Itemlist.addItem(test);
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "test", false);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "No items were found within the category test." + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    //happy case: has item, search for marked and is marked
    @Test
    public void listCommandTest_itemList_correct4() {
        Item test = new Item("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        Itemlist.addItem(test);
        Objects.requireNonNull(Itemlist.getItem(0)).mark();
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "NA", true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "1. Item Index: 1. " + test + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    //Error: Filter for marked, but no items are marked
    @Test
    public void listCommandTest_itemList_correct5() {
        Item test = new Item("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        Itemlist.addItem(test);
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "NA", true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "There are no marked items in your inventory list!" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

    }

    //Error: Filter for marked + cat, have result
    @Test
    public void listCommandTest_itemList_correct6() {
        Item test = new Item("testItem", 1, "ea", "test", 1.00F, 2.00F);
        Itemlist.addItem(test);
        Objects.requireNonNull(Itemlist.getItem(0)).mark();
        ListCommand listCommand1 = new ListCommand(Itemlist.getItems(), "test", true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "1. Item Index: 1. " + test + System.lineSeparator();
        assertEquals(expected, outputStream.toString());

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
            String expected = "";
            assertEquals(expected, outputStream.toString());
        } catch (CommandFormatException e) {
            Assertions.fail("Unexpected CommandFormatException thrown");
            throw new RuntimeException(e);
        }

    }

    @Test
    public void listCommandTest_promotionList_correct() throws InvalidDateException, CommandFormatException {
        AddCommand addCommand = new AddCommand("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        addCommand.execute();
        Command testPromo = new AddPromotionCommand("testItem", 0.30F, 2, Month.valueOf("APR"),
                2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            testPromo.execute();
            String expected = "The following promotion has been added" + System.lineSeparator() +
                    "testItem have a 30.00% discount" + System.lineSeparator() +
                    "Period: 2 APR 2024 to 4 APR 2024" + System.lineSeparator() +
                    "Time: 0000 to 1100" + System.lineSeparator();
            assertEquals(expected, outputStream.toString());
        } catch (EmptyListException e) {
            Assertions.fail("Unexpected EmptyListException thrown.");
            throw new RuntimeException(e);
        } catch (CommandFormatException | InvalidDateException e) {
            Assertions.fail("Unexpected exception thrown");
            throw new RuntimeException(e);
        }

    }
    @Test
    public void listCommandTest_itemList_empty() {
        ListCommand listCommand1 = new ListCommand(new ArrayList<Item>(), "NA", false);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "";
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void listCommandTest_transactionList_empty() {
        ListCommand listCommand1 = new ListCommand(new ArrayList<Transaction>(), "NA");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "";
        assertEquals(expected, outputStream.toString());

    }

    @Test
    public void listCommandTest_promotionList_empty() {
        ListCommand listCommand1 = new ListCommand(new ArrayList<Promotion>());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        listCommand1.execute();
        String expected = "";
        assertEquals(expected, outputStream.toString());

    }
}
