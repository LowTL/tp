package command;

import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import itemlist.Itemlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SellCommandTest {

    @Test
    public void testExecuteItemNotFound() {
        SellCommand sellCommand = new SellCommand("Nonexistent Item", 5, 0.1f);
        try {
            sellCommand.execute();
        } catch (CommandFormatException e) {
            assertEquals("Item not found!", e.getMessage());
        }
    }

    @Test
    public void testExecuteInsufficientStock() {
        SellCommand sellCommand = new SellCommand("Low Stock Item", 10, 0.1f);
        try {
            sellCommand.execute();
        } catch (CommandFormatException e) {
            assertEquals("There is insufficient stock!", e.getMessage());
        }
    }

    @Test
    public void testExecuteSuccessfulSale() {
        SellCommand sellCommand = new SellCommand("Available Item", 3, 0.1f);
        try {
            sellCommand.execute();
        } catch (CommandFormatException e) {
            fail("No exception should be thrown for a successful sale.");
        }
    }


    @Test
    public void sellItemTest() {
        try {
            Command sellCommandTest1 = new SellCommand("testItem", 1, 3);
            Command sellCommandTest2 = new SellCommand("testItem", 7, 14);
            sellCommandTest1.execute();
            sellCommandTest2.execute();
        } catch (CommandFormatException e) {
            fail("Unable to sell item.");
        } catch (InvalidDateException | EmptyListException e) {
            throw new RuntimeException(e);
        }
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

}
