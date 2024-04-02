package storage;

import item.Item;
import itemlist.Itemlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an add command where a new task is added to the existing list of task.
 * String <code>FILENAME</code> represents the designated relative file path for the file.
 * File <code>dukeData</code> represents the File object created to be updated.
 */
public class Storage {
    private static final String FILENAME = "./StockMasterData.txt";

    /**
     * Write contents to the file.
     *
     * @param filePath  File path where the file is located.
     * @param textToAdd The line of text to write to the file.
     * @param ifAppend  Indicate if append the text at the end of the file (true)
     *                  or overwrite the file (false).
     * @throws IOException If file is not found at the indicated file path.
     */
    public static void writeToFile(String filePath, String textToAdd, boolean ifAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, ifAppend);
        writer.write(textToAdd);
        writer.close();
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(getFileDirectory(), inputText, ifAppend);
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
        }
    }

    /**
     * Returns the private file directory of storage.
     */
    public static String getFileDirectory() {
        return FILENAME;
    }

    /**
     * Set the private File dukeData.
     */
    public static File setFile() {
        return new File(FILENAME);
    }

    /**
     * Read lines from the file and identify tasks written inside.
     * Add the identified tasks into a list of existing tasks.
     *
     * @param fileName Name of the file to read from.
     */
    public static void readFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                String[] keyCommands = fileLine.split(" \\| ");
                String commandQty = "";
                String commandCat = "";
                String commandUom = "";
                String commandBuy = "";
                String commandSell = "";
                String commandName = "";
                String commandIsMarked = "";
                for (String keyCommand : keyCommands) {
                    if (keyCommand.contains("add")) {
                        //do nothing.
                    } else if (keyCommand.contains("Qty: ")) {
                        String[] commandQtyUnit = keyCommand.replace("Qty: ", "").split(" ");
                        assert commandQtyUnit.length == 2 : "length not 2!";
                        commandQty = commandQtyUnit[0];
                        commandUom = commandQtyUnit[1];
                    } else if (keyCommand.contains("Cat: ")) {
                        commandCat = keyCommand.replace("Cat: ", "");
                    } else if (keyCommand.contains("BuyPrice: $")) {
                        commandBuy = keyCommand.replace("BuyPrice: $", "");
                    } else if (keyCommand.contains("SellPrice: $")) {
                        commandSell = keyCommand.replace("SellPrice: $", "");
                    } else if (keyCommand.contains("[")) {
                        commandIsMarked = keyCommand.contains("X") ? "true" : "false";
                    } else {
                        commandName = keyCommand.trim();
                    }
                }
                Item toAdd = new Item(commandName, Integer.parseInt(commandQty), commandUom, commandCat,
                        Float.parseFloat(commandBuy), Float.parseFloat(commandSell));
                if (commandIsMarked.equals("true")) {
                    toAdd.mark();
                } else if (commandIsMarked.equals("false")) {
                    toAdd.unmark();
                }
                Itemlist.addItem(toAdd);

            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch(NumberFormatException e) {
            System.out.println("Invalid numbers found.");
        }
    }

    /**
     * Writes to the indicated file without overwriting the previous information.
     *
     * @param items List of items with relevant details to write.
     */
    public static void addToFile(ArrayList<Item> items) {
        assert items != null : "Items cannot be null.";
        Item lastItem = items.get(items.size() - 1);
        String markString = (lastItem.getMarkStatus()) ? "[X] " : "[ ] ";
        String descriptionAdded = (items.size()) + "." + " | " + markString + " | " +
                lastItem.getItemName() + " | " + "Qty: " + lastItem.getQuantity() + " " +
                lastItem.getUnitOfMeasurement() + " | " + "Cat: " + lastItem.getCategory() +
                " | " + "BuyPrice: $" + lastItem.getBuyPrice() + " | " + "SellPrice: $" +
                lastItem.getSellPrice() + "\n";
        updateFile(descriptionAdded, true);
    }

    /**
     * Writes to the indicated file and overwrite previous data.
     *
     * @param items List of items with relevant details to write.
     */
    public static void overwriteFile(ArrayList<Item> items) {
        assert items != null : "Items cannot be null.";
        int length = items.size();
        for (int index = 0; index < length; index++) {
            String markString = (items.get(index).getMarkStatus()) ? "[X] " : "[ ] ";
            String descriptionAdded = (index + 1) + "." + " | " + markString + " | " +
                    items.get(index).getItemName() + " | " + "Qty: " + items.get(index).getQuantity() +
                    " " + items.get(index).getUnitOfMeasurement() + " | " + "Cat: " +
                    items.get(index).getCategory() + " | " + "BuyPrice: $" + items.get(index).getBuyPrice() +
                    " | " + "SellPrice: $" + items.get(index).getSellPrice() + "\n";
            if (index == 0) {
                updateFile(descriptionAdded, false);
            } else {
                updateFile(descriptionAdded, true);
            }
        }
    }
}
