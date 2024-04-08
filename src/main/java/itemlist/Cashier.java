package itemlist;

import item.Item;
import item.Transaction;

import java.util.ArrayList;

public class Cashier extends Itemlist {

    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    public static void addItem(Transaction transaction) {
        transactions.add(transaction);
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public static ArrayList<Transaction> getTransactions(Item item) {
        ArrayList<Transaction> results = new ArrayList<>();
        if (!transactions.isEmpty()) {
            for (Transaction t : transactions) {
                if (t.getItem() == item) {
                    results.add(t);
                }
            }
            return results;
        }
        else {
            return null;
        }
    }

    public static float getTotalRevenue() {
        float totalRevenue = 0;
        ArrayList<Transaction> allTransactions = getTransactions();
        if (allTransactions != null) {
            for (Transaction t : allTransactions) {
                if (!t.getIsVoided()) {
                    totalRevenue += t.getTotalPrice();
                }
            }
        }
        return totalRevenue;
    }

    public static float getTotalProfit() {
        float totalProfit = 0;
        if (!transactions.isEmpty()) {
            for (Transaction t : transactions) {
                if (!t.getIsVoided()) {
                    totalProfit += t.getProfit();
                }
            }
            return totalProfit;
        }
        else {
            System.out.println("No transactions found.");
            return 0;
        }
    }

    public static Transaction getTransaction(int index) {
        try {
            return transactions.get(index);
        } catch (IndexOutOfBoundsException e) {
            if (index == 0) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("Index " + index + " entered is out of bound.");
            }
            return null;
        }
    }

    public static Item getBestseller() {
        Item bestSeller = Itemlist.getItem(0);
        float[] profits = new float[Itemlist.items.size()];
        for (Transaction t: transactions) {
            if (!t.getIsVoided()) {
                profits[Itemlist.getIndex(t.getItem())] += t.getProfit();
            }
        }
        for (int i = 1; i < Itemlist.items.size(); i++) {
            if (profits[i] > profits[Itemlist.getIndex(bestSeller)]) {
                bestSeller = Itemlist.getItem(i);
            }
        }
        return bestSeller;
    }
}
