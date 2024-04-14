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

public class AddCommandTest {


    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testExecuteNewItemAddedSuccessfully() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory"
                + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
        assertEquals(1, Itemlist.getItems().size());
    }
    @Test
    public void testExecuteNewItemAddedWithDefaultCategory() {
        new AddCommand("TestItem", 10, "pcs", "NA", 5.0f, 10.0f).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00)" + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
        assertEquals(1, Itemlist.getItems().size());
    }

    @Test
    public void testExecuteExistingItemEditedSuccessfully() {
        new AddCommand("TestItem", 10, "pcs", "NA", 5.0f, 10.0f).execute();
        new AddCommand("TestItem", 15, "pcs", "NA", 6.0f, 12.0f).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00)" + System.lineSeparator() +
                "Item already exists and item information has been updated" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Quantity of TestItem from 10 to 25" + System.lineSeparator() +
                "Buy Price of TestItem from 5.0 to 6.00" + System.lineSeparator() +
                "Sell Price of TestItem from 10.0 to 12.00" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
        assertEquals(1, Itemlist.getItems().size());
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
