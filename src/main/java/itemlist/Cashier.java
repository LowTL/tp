package itemlist;

import exceptions.EmptyListException;
import item.Transaction;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Cashier extends Itemlist {

    public static ArrayList<Transaction> transactions = new ArrayList<>();
    protected static final Logger LOGGER = Logger.getLogger(Cashier.class.getName());

    public static void addItem(Transaction transaction) {
        transactions.add(transaction);
    }

    public static void deleteItem(int index) {
        transactions.remove(index);
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    //Overloading of function allows for getting of Transactions with the specific itemName
    public static ArrayList<Transaction> getTransactions(String itemName) {
        ArrayList<Transaction> results = new ArrayList<>();
        if (!transactions.isEmpty()) {
            for (Transaction t : transactions) {
                if (t.getItemName().equals(itemName)) {
                    results.add(t);
                }
            }
        }
        return results;
    }

    public static float getTotalRevenue() {
        float totalRevenue = 0;
        try {
            ArrayList<Transaction> allTransactions = getTransactions();
            if (allTransactions.isEmpty()) {
                throw new EmptyListException("Bestseller");
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
                throw new EmptyListException("Bestseller");
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

    public static String getBestseller() {
        if (transactions.isEmpty()) {
            return null;
        }
        String bestSeller = transactions.get(0).getItemName();
        ArrayList<String> tempNames = new ArrayList<>();
        float[] profits = new float[transactions.size()];
        assert(Itemlist.noOfItems > 0);
        for (Transaction t: transactions) {
            if (!tempNames.contains(t.getItemName())) {
                tempNames.add(t.getItemName());
            }
            profits[tempNames.indexOf(t.getItemName())] += t.getProfit();
        }
        for (int i = 1; i < tempNames.size(); i++) {
            if (profits[i] > profits[tempNames.indexOf(bestSeller)]) {
                System.out.println(tempNames.get(i));
                bestSeller = tempNames.get(i);
            }
        }
        return bestSeller;
    }
}
