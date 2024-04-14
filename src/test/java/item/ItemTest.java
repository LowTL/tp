package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        // Initialize Item object before each test
        item = new Item("Apple", 10, "Kg", "Fruit", 0.50f, 1.00f);
    }

    @Test
    void testItemCreation() {
        assertNotNull(item, "Item should not be null after creation");
        assertEquals("Apple", item.getItemName(), "Check item name");
        assertEquals(10, item.getQuantity(), "Check quantity");
        assertEquals("Kg", item.getUnitOfMeasurement(), "Check unit of measurement");
        assertEquals("Fruit", item.getCategory(), "Check category");
        assertEquals(0.50f, item.getBuyPrice(), "Check buy price");
        assertEquals(1.00f, item.getSellPrice(), "Check sell price");
        assertFalse(item.getIsOOS(), "Item should not be out of stock");
    }

    @Test
    void testSetItemName() {
        item.setItemName("Banana");
        assertEquals("Banana", item.getItemName(), "Item name should be updated to Banana");
    }

    @Test
    void testSetQuantity() {
        item.setQuantity(20);
        assertEquals(20, item.getQuantity(), "Quantity should be updated to 20");
        assertFalse(item.getIsOOS(), "Item should not be marked out of stock with quantity 20");

        // Testing out of stock scenario
        item.setQuantity(0);
        assertEquals(0, item.getQuantity(), "Quantity should be updated to 0");
        assertTrue(item.getIsOOS(), "Item should be marked out of stock");
    }

    @Test
    void testSetUnitOfMeasurement() {
        item.setUnitOfMeasurement("Boxes");
        assertEquals("Boxes", item.getUnitOfMeasurement(), "Unit of measurement should be updated to Boxes");
    }

    @Test
    void testSetCategory() {
        item.setCategory("Snacks");
        assertEquals("Snacks", item.getCategory(), "Category should be updated to Snacks");
    }

    @Test
    void testSetBuyPrice() {
        item.setBuyPrice(0.75f);
        assertEquals(0.75f, item.getBuyPrice(), "Buy price should be updated to 0.75");
    }

    @Test
    void testSetSellPrice() {
        item.setSellPrice(1.50f);
        assertEquals(1.50f, item.getSellPrice(), "Sell price should be updated to 1.50");
    }

    @Test
    void testMarkUnmarkItem() {
        item.mark();
        assertTrue(item.getMarkStatus(), "Item should be marked");

        item.unmark();
        assertFalse(item.getMarkStatus(), "Item should be unmarked");
    }
}

