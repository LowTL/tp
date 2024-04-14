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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    Command addCommandTest1 = new AddCommand("testItem", 1, "EA",
            "NA", 1, 10);
    Command deleteCommand = new DeleteCommand("testItem");

    @Test
    public void delCommandTest_success() {
        try {
            addCommandTest1.execute();
            deleteCommand.execute();
            assertFalse(Itemlist.itemIsExist("testItem"));
            deleteCommand.execute();
        } catch (CommandFormatException e) {
            fail("Unable to delete.");
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
