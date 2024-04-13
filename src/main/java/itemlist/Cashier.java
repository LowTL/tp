package itemlist;

import exceptions.EmptyListException;
import item.Item;
import item.Transaction;

import java.util.ArrayList;
import java.util.logging.Logger;

import static ui.TextUi.replyToUser;

public class Cashier extends Itemlist {

    protected static final Logger LOGGER = Logger.getLogger(Cashier.class.getName());
    protected static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void addItem(Transaction transaction) {
        transactions.add(transaction);
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    //Overloading of function allows for getting of Transactions with the specific itemName
    public static ArrayList<Transaction> getTransactions(String itemName) {
        ArrayList<Transaction> results = new ArrayList<>();
        if (!transactions.isEmpty()) {
            for (Transaction t : transactions) {
                if (t.getItem().getItemName().equals(itemName)) {
                    results.add(t);
                }
            }
            LOGGER.info("Transactions filtered.");
            return results;
        } else {
            LOGGER.warning("No transactions found.");
            return null;
        }
    }

    public static float getTotalRevenue() {
        float totalRevenue = 0;
        try {
            ArrayList<Transaction> allTransactions = getTransactions();
            if (allTransactions.isEmpty()) {
                throw new EmptyListException("Transaction");
            }
            for (Transaction t : allTransactions) {
                totalRevenue += t.getTotalPrice();
            }
        } catch (EmptyListException e) {
            LOGGER.warning("No transactions found.");
            return 0;
        }
        return totalRevenue;
    }

    public static float getTotalProfit() {
        float totalProfit = 0;
        try {
            if (transactions.isEmpty()) {
                throw new EmptyListException("Transaction");
            }
            for (Transaction t : transactions) {
                totalProfit += t.getProfit();
            }
        } catch (EmptyListException e) {
            LOGGER.warning("No transactions found.");
            return 0;
        }
        return totalProfit;
    }

    public static Transaction getTransaction(int index) {
        try {
            return transactions.get(index);
        } catch (IndexOutOfBoundsException e) {
            if (index == 0) {
                LOGGER.warning("No transactions found.");
                System.out.println("No transactions found.");
            } else {
                LOGGER.warning("Index out of bounds.");
                System.out.println("Index " + index + " entered is out of bound.");
            }
            return null;
        }
    }

    public static Item getBestseller() {
        Item bestSeller = null;
        float[] profits = new float[Itemlist.items.size()];
        if (transactions.isEmpty()) {
            return null;
        }
        assert(Itemlist.noOfItems > 0);
        bestSeller = Itemlist.getItem(0);
        for (Transaction t: transactions) {
            profits[Itemlist.getIndex(t.getItem())] += t.getProfit();
        }
        for (int i = 1; i < Itemlist.items.size(); i++) {
            if (profits[i] > profits[Itemlist.getIndex(bestSeller)]) {
                bestSeller = Itemlist.getItem(i);
            }
        }
        return bestSeller;
    }
}
