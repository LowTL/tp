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

import static org.junit.jupiter.api.Assertions.fail;

public class SellCommandTest {

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
