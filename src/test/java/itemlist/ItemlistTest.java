package itemlist;

import item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ItemlistTest {

    private Item testItem;

    @BeforeEach
    void setUp() {
        // This will be run before each test, setting up test conditions
        float buyPrice = 0.5F;
        float sellPrice = 1.2F;
        testItem = new Item("TestItem", 10, "test UOM", "test Cat", buyPrice,
                sellPrice);
        Itemlist.addItem(testItem);
    }

    @AfterEach
    void tearDown() {
        // This will be run after each test, cleaning up
        Itemlist.getItems().clear(); // clear the list for next test
    }

    @Test
    void addItem_shouldIncreaseListSize() {
        assertEquals(10, Itemlist.getItems().size());
    }

    @Test
    void deleteItem_shouldDecreaseListSize() {
        Itemlist.deleteItem(0);
        assertEquals(0, Itemlist.getItems().size());
    }

    @Test
    void editQuantity_shouldUpdateQuantity() {
        Itemlist.editQuantity(0, 5);
        assertEquals(5, testItem.getQuantity());
        assertFalse(testItem.getIsOOS());
    }

    @Test
    void editQuantity_shouldMarkOutOfStock() {
        Itemlist.editQuantity(0, 0);
        assertTrue(testItem.getIsOOS());
    }

    @Test
    void itemIsExist_shouldReturnTrueForExistingItem() {
        assertTrue(Itemlist.itemIsExist("TestItem"));
    }

    @Test
    void itemIsExist_shouldReturnFalseForNonExistingItem() {
        assertFalse(Itemlist.itemIsExist("NonExistingItem"));
    }

    @Test
    void getItem_shouldReturnCorrectItem() {
        Item item = Itemlist.getItem(0);
        assertEquals("TestItem", item.getItemName());
        assertEquals(10, item.getQuantity());
    }

    @Test
    void getIndex_shouldReturnCorrectIndex() {
        int index = Itemlist.getIndex(testItem);
        assertEquals(0, index);
    }
}
