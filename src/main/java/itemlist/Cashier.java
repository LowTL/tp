package itemlist;

import item.Item;
import item.Transaction;

import java.util.ArrayList;

public class Cashier extends Itemlist {

    private static final ArrayList<Transaction> transactions = new ArrayList<>();

    public static void addItem(Transaction transaction) {
        transactions.add(transaction);
    }

    public static void deleteItem(int index) {
        transactions.remove(index);
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static float getTotalRevenue() {
        float revenue = 0;
        for (Transaction t : getTransactions()) {
            if (!t.getIsVoided()) {
                revenue += t.getTotalPrice();
            }
        }
        return revenue;
    }

    public float getTotalProfit() {
        float totalProfit = 0;
        for (Transaction t: transactions) {
            if (!t.getIsVoided()) {
                totalProfit += t.getProfit();
            }
        }
        return totalProfit;
    }

    public static Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    public Item getBestseller() {
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
