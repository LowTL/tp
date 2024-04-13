package itemlist;

import exceptions.EmptyListException;
import item.Item;
import item.Transaction;

import java.util.ArrayList;

import static ui.TextUi.replyToUser;

public class Cashier extends Itemlist {

    private static final ArrayList<Transaction> transactions = new ArrayList<>();

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
            return results;
        } else {
            return null;
        }
    }

    public static float getTotalRevenue() {
        float totalRevenue = 0;
        ArrayList<Transaction> allTransactions = getTransactions();
        if (!allTransactions.isEmpty()) {
            for (Transaction t : allTransactions) {
                totalRevenue += t.getTotalPrice();
            }
        }
        return totalRevenue;
    }

    public static float getTotalProfit() {
        float totalProfit = 0;
        try {
            if (transactions.isEmpty()) {
                throw new EmptyListException("Transaction");
            }
        } catch (EmptyListException e) {
            return 0;
        }
        for (Transaction t : transactions) {
            totalProfit += t.getProfit();
        }
        return totalProfit;
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
        int transaction_index = 0;
        try {
            for (Transaction t : transactions) {
                transaction_index = transactions.indexOf(t);
                profits[Itemlist.getIndex(t.getItem())] += t.getProfit();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            replyToUser("Item at Transaction ID " + (transaction_index + 1) +
                    " no longer found in the item list.");
            return null;
        }
        for (int i = 1; i < Itemlist.items.size(); i++) {
            if (profits[i] > profits[Itemlist.getIndex(bestSeller)]) {
                bestSeller = Itemlist.getItem(i);
            }
        }
        return bestSeller;
    }
}
