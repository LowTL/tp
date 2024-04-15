package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import command.EditCommand;
import item.Item;
import itemlist.Itemlist;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class EditCommandTest {

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
    public void testExecuteEditItemNotFound() {
        new EditCommand("NonExistentItem", "NewTestItem", -1, "NA", "NA", -1, -1).execute();
        String expectedOutput = "item not found!" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditItemName() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NewTestItem", -1, "NA", "NA", -1, -1).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Name of TestItem from TestItem to NewTestItem" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditQuantity() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NA", 50, "NA", "NA", -1, -1).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Quantity of TestItem from 10 to 50" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditInvalidQuantity() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NA", -100, "NA", "NA", -1, -1).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Quantity of TestItem from 10 to -100" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditUnitOfMeasurement() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NA", -1, "kg", "NA", -1, -1).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Unit of Measurement of TestItem from pcs to kg" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditCategory() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NA", -1, "NA", "NewTestCategory", -1, -1).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Category of TestItem from TestCategory to NewTestCategory" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditBuyPrice() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NA", -1, "NA", "NA", 6.0f, -1).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Buy Price of TestItem from 5.0 to 6.00" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditSellPrice() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NA", -1, "NA", "NA", -1, 12.0f).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Sell Price of TestItem from 10.0 to 12.00" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testExecuteEditMultipleAttributes() {
        new AddCommand("TestItem", 10, "pcs", "TestCategory", 5.0f, 10.0f).execute();
        new EditCommand("TestItem", "NewTestItem", 50, "kg", "NewTestCategory", 6.0f, 12.0f).execute();
        String expectedOutput = "added: testitem (Qty: 10 pcs, Buy: $5.00, Sell: $10.00) to TestCategory" + System.lineSeparator() +
                "\n" +
                "Edited: " + System.lineSeparator() +
                "Name of TestItem from TestItem to NewTestItem" + System.lineSeparator() +
                "Quantity of TestItem from 10 to 50" + System.lineSeparator() +
                "Unit of Measurement of TestItem from pcs to kg" + System.lineSeparator() +
                "Category of TestItem from TestCategory to NewTestCategory" + System.lineSeparator() +
                "Buy Price of TestItem from 5.0 to 6.00" + System.lineSeparator() +
                "Sell Price of TestItem from 10.0 to 12.00" + System.lineSeparator() +
                "End of Edits" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

}
