package ui;

import java.util.ArrayList;
import java.util.Scanner;

import common.Messages;
import item.Item;

public class TextUi {

    public static final String DIVIDER = "----------------";

    private final Scanner in;

    public TextUi() {
        this.in = new Scanner(System.in);
    }

    public static String getUserInput() {
        System.out.println("Enter Command:");
        Scanner in = new Scanner(System.in);
        String userInput = in.hasNextLine() ? in.nextLine() : "";
        if (shouldIgnore(userInput)) {
            return "Invalid Command"; //Might want to change this with Exceptions
        }
        return userInput;
    }

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

    public static void replyToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public static <T> void showList(ArrayList<T> arrayList) {
        if (arrayList.isEmpty()) {
            replyToUser(Messages.EMPTY_LIST);
        } else {
            replyToUser("List: ");
            for (T item : arrayList) {
                if (item == null) {
                    break;
                }
                replyToUser(arrayList.indexOf(item) + 1 + ". " + item);
            }
        }
    }

    public static void showSellMessage(String item, int sellQuantity, int remainingQuantity, float sellPrice) {
        float totalValue = sellQuantity * sellPrice;
        replyToUser("Quantity of " + item + " sold: " + sellQuantity + ", for: $" + sellPrice + "\n" +
                "Quantity remaining: " + remainingQuantity + "\n" +
                "Total value sold: " + totalValue
        );
    }

    //@@author Fureimi
    public static void showCustomizedList(ArrayList<Item> arrayList, String category, boolean isListMarked) {
        if (arrayList.isEmpty()) {
            replyToUser(Messages.EMPTY_LIST);
            // case 1: user wants to list all items of a certain category
        } else if (!category.equals("NA") && !isListMarked) {
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
