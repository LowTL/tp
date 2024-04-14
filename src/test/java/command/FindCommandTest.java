package command;

import common.Messages;
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {

    @Test
    public void findCommandTest() throws CommandFormatException, InvalidDateException {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Command addCommandTest1 = new AddCommand("testItem", 1, "EA",
                "NA", 1, 10);
        Command findCommand = new FindCommand("item","testItem");
        Command findCommand2 = new FindCommand("NA","failFindCommand");
        Command findCommand3 = new FindCommand("qty/buy/sell/uom/cat","1");
        Command findCommand4 = new FindCommand("buy/sell/uom/cat","1");
        try {
            addCommandTest1.execute();
            findCommand.execute();
            findCommand2.execute();
            findCommand3.execute();
            findCommand4.execute();
            String expectedOutput1 = "added: testitem (Qty: 1 EA, Buy: $1.00, Sell: $10.00)"
                    + System.lineSeparator() + "List: " + System.lineSeparator() +
                    "1. [ ] testItem (Qty: 1 EA, Buy: $1.00, Sell: $10.00)" + System.lineSeparator() +
                    Messages.EMPTY_ITEM_LIST + System.lineSeparator() +
                    "List: " + System.lineSeparator() +
                    "1. [ ] testItem (Qty: 1 EA, Buy: $1.00, Sell: $10.00)" + System.lineSeparator() +
                    "List: " + System.lineSeparator() +
                    "1. [ ] testItem (Qty: 1 EA, Buy: $1.00, Sell: $10.00)" + System.lineSeparator();
            assertEquals(expectedOutput1, outputStreamCaptor.toString());
        } catch (EmptyListException e) {
            return;
        }
        assertFalse(Itemlist.itemIsExist("failFindCommand"));
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
