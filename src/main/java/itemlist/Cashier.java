package itemlist;

import item.Item;
import item.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cashier extends Itemlist {
    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    public static void addItem(Transaction transaction) {
        transactions.add(transaction);
    }

    public static void deleteItem(int index) {
        transactions.remove(index);
    }
}
