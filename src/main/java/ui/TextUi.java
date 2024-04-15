package ui;

import java.util.ArrayList;
import java.util.Scanner;

import common.Messages;
import item.Item;
import item.Transaction;
import promotion.Promotion;

/**
 * Represents a class that displays messages and command output to the user.
 * String <code>DIVIDER</code> A string to separate current message from the previous ones.
 * Scanner <code>in</code> A scanner to retrieve user's text inputs.
 */
public class TextUi {

    public static final String DIVIDER = "----------------";

    private final Scanner in;

    /**
     * Constructor for TextUi.
     */
    public TextUi() {
        this.in = new Scanner(System.in);
    }

    /**
     * Retrieve user's input as a string.
     */
    public static String getUserInput() {
        System.out.println("Enter Command:");
        Scanner in = new Scanner(System.in);
        String userInput = in.hasNextLine() ? in.nextLine() : "";
        if (shouldIgnore(userInput)) {
            return "Invalid Command"; //Might want to change this with Exceptions
        }
        return userInput;
    }

    /**
     * Determines if user's input is empty and should be ignored.
     *
     * @param userInput User's input.
     */
    public static boolean shouldIgnore(String userInput) {
        return userInput.trim().isEmpty();
    }

    public void showWelcomeMessage(String version, String storageFilePath) {
        replyToUser(
                DIVIDER,
                version,
                DIVIDER,
                "Data is being extracted from: " + storageFilePath,
                Messages.WELCOME_MESSAGE
        );
    }

    /**
     * Displays goodbye message to the user when exit the program.
     *
     * @param storageFilePath Directory of storage file.
     * @param transactionLogPath Directory of transaction log file.
     * @param promotionStoragePath Directory of promotion storage file.
     */
    public void showGoodByeMessage(String storageFilePath, String transactionLogPath, String promotionStoragePath) {
        replyToUser(
                DIVIDER,
                "Inventory is being saved to :" + storageFilePath,
                DIVIDER,
                "Transactions are being saved to:" + transactionLogPath,
                DIVIDER,
                "Promotions are being saved to: " + promotionStoragePath,
                DIVIDER,
                Messages.GOODBYE_MESSAGE
        );
    }

    /**
     * Displays message to the user.
     *
     * @param message Message to show to the user.
     */
    public static void replyToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Display the arraylist to the user.
     *
     * @param arrayList The arraylist to show to the user.
     */
    public static <T> void showList(ArrayList<T> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            replyToUser(Messages.EMPTY_LIST);
            return;
        } else {
            replyToUser("List: ");
            int index = 1;
            for (T item : arrayList) {
                if (item == null) {
                    break;
                }
                String listItem = index + ". " + item;
                replyToUser(listItem);
                index++;
            }
        }
    }

    /**
     * Displays message to the user when an item is sold.
     *
     * @param item The item sold.
     * @param sellQuantity Quantity of the item sold.
     * @param remainingQuantity Quantity remained for the item sold.
     * @param sellPrice The selling price of the item sold.
     */
    public static void showSellMessage(String item, int sellQuantity, int remainingQuantity, float sellPrice) {
        float totalValue = sellQuantity * sellPrice;
        replyToUser("Quantity of " + item + " sold: " + sellQuantity + ", for: $" + sellPrice + "\n" +
                "Quantity remaining: " + remainingQuantity + "\n" +
                "Total value sold: " + totalValue
        );
    }

    //@@author Fureimi
    /**
     * Displays a list that contains items of a certain category.
     *
     * @param arrayList The arraylist to show to the user.
     * @param category The category to be listed to the user.
     * @param isListMarked Whether the items listed are marked items.
     */
    public static void showCustomizedList(ArrayList<Item> arrayList, String category, boolean isListMarked) {
        // case 1: user wants to list all items of a certain category
        if (!category.equals("NA") && !isListMarked) {
            int flag = 0;
            int counter = 1;
            for (Item item : arrayList) {
                if (item.getCategory().equals(category)) {
                    replyToUser(counter + ". Item Index: " + (arrayList.indexOf(item) + 1) + ". " + item);
                    counter++;
                    flag = 1;
                }
            }
            if (flag == 0) {
                replyToUser("No items were found within the category " + category + ".");
            }
            // case 2: user wants to list all marked items
        } else if (category.equals("NA") && isListMarked) {
            int flag = 0;
            int counter = 1;
            for (Item item : arrayList) {
                if (item.isMark) {
                    replyToUser(counter + ". Item Index: " + (arrayList.indexOf(item) + 1) + ". " + item);
                    counter++;
                    flag = 1;
                }
            }
            if (flag == 0) {
                replyToUser("There are no marked items in your inventory list!");
            }
            // case 3: user wants to list all marked items of a certain category
        } else if (!category.equals("NA") && isListMarked) {
            int flag = 0;
            int counter = 1;
            for (Item item : arrayList) {
                if (item.isMark && item.getCategory().equals(category)) {
                    replyToUser(counter + ". Item Index: " + (arrayList.indexOf(item) + 1) + ". " + item);
                    counter++;
                    flag = 1;
                }
            }
            if (flag == 0) {
                replyToUser("There are no marked items of category '" + category + "' in your inventory list!");
            }
        }
    }

    public static void showTransactionList(ArrayList<Transaction> transactions) {
        int counter = 0;
        for (Transaction t: transactions) {
            counter++;
            replyToUser(counter + ". " + t.toString());
        }
    }

    public static void showPromotionList(ArrayList<Promotion> promotionList) {
        int counter = 0;
        for (Promotion p: promotionList) {
            counter++;
            replyToUser(counter + ". " + p.toString());
        }
    }

    public static void showEditMessage(String item, String editedParameter, String oldParameter, String newParameter) {
        switch (editedParameter) {
        case "newItemName":
            replyToUser("Name of " + item + " from " + oldParameter + " to " + newParameter);
            break;
        case "newQuantity":
            replyToUser("Quantity of " + item + " from " + oldParameter + " to " + newParameter);
            break;
        case "newUnitOfMeasurement":
            replyToUser("Unit of Measurement of " + item + " from " + oldParameter + " to " + newParameter);
            break;
        case "newCategory":
            replyToUser("Category of " + item + " from " + oldParameter + " to " + newParameter);
            break;
        case "newBuyPrice":
            replyToUser("Buy Price of " + item + " from " + oldParameter + " to " + newParameter);
            break;
        case "newSellPrice":
            replyToUser("Sell Price of " + item + " from " + oldParameter + " to " + newParameter);
            break;
        default:
            break;
        }
    }
}
