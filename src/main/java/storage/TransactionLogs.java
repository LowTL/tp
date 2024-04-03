package storage;

import item.Transaction;
import itemlist.Cashier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionLogs extends Storage {
    private static final String LOGNAME = "./TransactionLogs.txt";

    public static String getFileDirectory() {
        return LOGNAME;
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(getFileDirectory(), inputText, ifAppend);
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
        }
    }

    public static void readFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String commandQty = "";
            String commandProfit = "";
            String commandTotalSell = "";
            String commandSell = "";
            String commandName = "";
            String commandDate = "";
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                //System.out.println(fileLine);
                if (fileLine.contains("Quantity: ")) {
                    commandQty = fileLine.replace("Quantity: ", "");
                } else if (fileLine.contains("Date: ")) {
                    commandDate = fileLine.replace("Date: ", "");
                } else if (fileLine.contains("Unit Price: ")) {
                    commandSell = fileLine.replace("Unit Price: ", "");
                } else if (fileLine.contains("Total Price: ")) {
                    commandTotalSell = fileLine.replace("Total Price: ", "");
                } else if (fileLine.contains("Item Name: ")){
                    commandName = fileLine.replace("Item Name: ", "");
                } else if (fileLine.contains("Profit: ")) {
                    commandProfit = fileLine.replace("Profit: ", "");
                    int quantityAsInt = Integer.parseInt(commandQty);
                    int buyAsInt = (Integer.parseInt(commandTotalSell) - Integer.parseInt(commandProfit))
                            / quantityAsInt;
                    Transaction toAdd = new Transaction(commandName, quantityAsInt,
                            buyAsInt, Integer.parseInt(commandSell), commandDate);
                    Cashier.addItem(toAdd);
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch(NumberFormatException e) {
            System.out.println("Invalid numbers found");
        }
    }

    public static void addToLog(ArrayList<Transaction> transactions) {
        assert transactions != null : "Transactions cannot be null.";
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        String descriptionAdded = "";
        descriptionAdded += "Date: " + lastTransaction.getDateTime() + "\n";
        descriptionAdded += "Transaction ID: " + transactions.size() + "\n";
        descriptionAdded += "Item Name: " + lastTransaction.getItemName() + "\n";
        descriptionAdded += "Quantity: " + lastTransaction.getQuantity() + "\n";
        descriptionAdded += "Unit Price: " + lastTransaction.getSellPrice() + "\n";
        descriptionAdded += "Total Price: " + lastTransaction.getTotalPrice() + "\n";
        descriptionAdded += "Profit: " + lastTransaction.getProfit() + "\n";
        descriptionAdded += "\n";
        updateFile(descriptionAdded, true);
    }
}
