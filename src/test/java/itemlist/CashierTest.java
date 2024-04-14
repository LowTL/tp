package itemlist;

import item.Item;
import item.Transaction;
import org.junit.jupiter.api.Test;

public class CashierTest {

    @Test
    public void addTransactionTest() {
        Item testItem = new Item("testItem", 1, "ea", "NA", 1.00F, 2.00F);
        Transaction testTransaction = new Transaction("testTransaction", 1, 1.00F, 2.00F);
        Cashier.addItem(testTransaction);
        assert(Cashier.getTransactions().contains(testTransaction));
        Cashier.deleteItem(Cashier.getTransactions().size() - 1);
    }
}
