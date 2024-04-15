package command;

import itemlist.Itemlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowStockCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
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

    @Test
    public void testExecuteNoItemsLowOnStock() {
        new LowStockCommand(5).execute();
        assertEquals("Out-of-stock Items:" + System.lineSeparator() +
                "No items out of stock" + System.lineSeparator() +
                "Low-on-stock Items: (less than 5)" + System.lineSeparator() +
                "No items low on stock" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithItemsLowOnStock() {
        new AddCommand("Item1", 3, "pcs", "testCat", 1, 2).execute();
        new AddCommand("Item2", 1, "pcs", "testCat", 1, 2).execute();
        new LowStockCommand(5).execute();
        assertEquals("added: item1 (Qty: 3 pcs, Buy: $1.00, Sell: $2.00) to testCat" + System.lineSeparator() +
                "added: item2 (Qty: 1 pcs, Buy: $1.00, Sell: $2.00) to testCat" + System.lineSeparator() +
                "Out-of-stock Items:" + System.lineSeparator() +
                "No items out of stock" + System.lineSeparator() +
                "Low-on-stock Items: (less than 5)" + System.lineSeparator() +
                "Item1" + System.lineSeparator() +
                "Item2" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithNoItemsOutOfStock() {
        new AddCommand("Item1", 100, "pcs", "testCat", 1, 2).execute();
        new AddCommand("Item2", 50, "pcs", "testCat", 1, 2).execute();
        new LowStockCommand(5).execute();
        assertEquals("added: item1 (Qty: 100 pcs, Buy: $1.00, Sell: $2.00) to testCat" + System.lineSeparator() +
                "added: item2 (Qty: 50 pcs, Buy: $1.00, Sell: $2.00) to testCat" + System.lineSeparator() +
                "Out-of-stock Items:" + System.lineSeparator() +
                "No items out of stock" + System.lineSeparator() +
                "Low-on-stock Items: (less than 5)" + System.lineSeparator() +
                "No items low on stock" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteWithItemsOutOfStockAndLowOnStock() {
        new AddCommand("Item1", 3, "pcs", "testCat", 1, 2).execute();
        new AddCommand("Item2", 10, "pcs", "testCat", 1, 2).execute();
        new LowStockCommand(5).execute();
        assertEquals("added: item1 (Qty: 3 pcs, Buy: $1.00, Sell: $2.00) to testCat" + System.lineSeparator() +
                "added: item2 (Qty: 10 pcs, Buy: $1.00, Sell: $2.00) to testCat" + System.lineSeparator() +
                "Out-of-stock Items:" + System.lineSeparator() +
                "No items out of stock" + System.lineSeparator() +
                "Low-on-stock Items: (less than 5)" + System.lineSeparator() +
                "Item1" + System.lineSeparator(), outputStreamCaptor.toString());
    }
}
