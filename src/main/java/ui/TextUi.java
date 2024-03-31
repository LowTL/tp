package ui;

import java.util.ArrayList;
import java.util.Scanner;

import item.Item;

public class TextUi {

    public static final String DIVIDER = "----------------";

    public static final String WELCOME_MESSAGE = "Welcome to StockMaster, where you can master the knowledge on your " +
            "Stock!";

    public static final String GOODBYE_MESSAGE = "Thank you for using StockMaster, hope we have helped your lazy ass!";
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
                WELCOME_MESSAGE
        );
    }

    public void showGoodByeMessage(String storageFilePath) {
        replyToUser(
                DIVIDER,
                "Data is being saved to :" + storageFilePath,
                DIVIDER,
                GOODBYE_MESSAGE
        );
    }

    public static void replyToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public static <T> void showInventoryList(ArrayList<T> arrayList) {
        if (arrayList.isEmpty()) {
            replyToUser("There is nothing here! Time to spend some money and stock em up!");
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

    public static void showCategoryList(ArrayList<Item> arrayList, String category) {
        if (arrayList.isEmpty()) {
            replyToUser("There is nothing here! Time to spend some money and stock em up!");
        } else {
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
        case "newUom":
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

    public static void showSellMessage(String item, int sellQuantity, int remainingQuantity, float sellPrice) {
        replyToUser("Quantity of " + item + " sold: " + sellQuantity + ", for: $" + sellPrice + "\n" +
                "Quantity remaining: " + remainingQuantity
        );
    }
}
