package itemlist;

import item.Item;
import item.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import promotion.Promotionlist;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;

public class CashierTest {

    @Test
    public void addTransactionTest() {
        Item testItem = new Item("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        Transaction testTransaction = new Transaction("testTransaction", 1, 1.00F, 2.00F);
        Cashier.addItem(testTransaction);
        assert(Cashier.getTransactions().contains(testTransaction));
        Cashier.deleteItem(Cashier.getTransactions().size() - 1);
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
