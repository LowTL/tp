package command;

import common.Messages;
import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import itemlist.Itemlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import promotion.Month;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeletePromotionTest {

    @Test
    public void deletePromotionTest() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Command addCommandTest1 = new AddCommand("apple iphone", 100000, "pieces",
                "electronics", 100.5F, 400.1F);
        Command promotionTest1 = new AddPromotionCommand("apple iphone", 0.30F, 1, Month.valueOf("FEB"),
                2024, 4, Month.valueOf("DEC"), 2024, 0000, 2359);
        Command deleteFail = new DeletePromotionCommand("test");
        Command deleteSuccess = new DeletePromotionCommand("apple iphone");
        try {
            addCommandTest1.execute();
            promotionTest1.execute();
            deleteFail.execute();
            deleteSuccess.execute();
            String expectedOutput1 = "added: apple iphone (Qty: 100000 pieces, Buy: $100.50, Sell: $400.10) " +
                    "to electronics" + System.lineSeparator() +
                    "The following promotion has been added" + System.lineSeparator() +
                    "apple iphone have a 30.00% discount" + System.lineSeparator() +
                    "Period: 1 FEB 2024 to 4 DEC 2024" + System.lineSeparator() +
                    "Time: 0000 to 2359" + System.lineSeparator() +
                    Messages.ITEM_NOT_ON_PROMO + System.lineSeparator() +
                    "Promotion for apple iphone has been removed" + System.lineSeparator();
            assertEquals(expectedOutput1, outputStreamCaptor.toString());
        } catch (CommandFormatException | InvalidDateException | EmptyListException e) {
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
