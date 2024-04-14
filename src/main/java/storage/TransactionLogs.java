package storage;

import item.Transaction;
import itemlist.Cashier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class that stores and writes transaction information of a list of items to a file.
 * String <code>LOGNAME</code> represents the designated relative file path for the file.
 */
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

    /**
     * Write contents to the file.
     *
     * @param scanner The scanner to read the file.
     * @throws NumberFormatException If number is found to be invalid type.
     */
    public static void interpretLines(Scanner scanner) throws NumberFormatException{
        String commandQty = "";
        String commandProfit = "";
        String commandTotalSell = "";
        String commandSell = "";
        String commandName = "";
        String commandDate = "";
        while (scanner.hasNext()) {
            String fileLine = scanner.nextLine();
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
                float buyAsFloat = (Float.parseFloat(commandTotalSell) - Float.parseFloat(commandProfit))
                        / (float) quantityAsInt;
                Transaction toAdd = new Transaction(commandName, quantityAsInt,
                        buyAsFloat, Float.parseFloat(commandSell), commandDate);
                Cashier.addItem(toAdd);
                LOGGER.info("Transaction added successfully.");
            }
        }
    }

    public static void readFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            interpretLines(scanner);
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch(NumberFormatException e) {
            System.out.println("Invalid numbers found!!!");
        }
    }

    /**
     * Writes to the indicated file without overwriting the previous information.
     *
     * @param transactions List of transactions to write to the file.
     */
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
        LOGGER.info("Stored transaction.");
    }
}
