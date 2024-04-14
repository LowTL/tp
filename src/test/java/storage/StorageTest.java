package storage;

import exceptions.InvalidDateException;
import item.Item;
import itemlist.Itemlist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static storage.Storage.interpretLines;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class StorageTest {
    @Test
    public void readFromFile_fileNotFound() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String directory = "./testFile1.txt";
        File testFile = new File(directory);
        try {
            Storage.writeToFile(directory, "", true);
            testFile.delete();
            Storage.readFromFile(directory);
            assertEquals("File does not exist. Creating a new Text File" +
                    System.lineSeparator(), outputStream.toString());
        } catch (IOException e) {
            fail("failed to create a file.");
        }
    }

    @Test
    public void writeToFile_aLine_writeSuccessful() {
        String directory = "./testFile2.txt";
        File testFile = new File(directory);
        String aLine = "A line";
        try {
            Storage.writeToFile(directory, aLine, true);
            Scanner scanner = new Scanner(testFile);
            String lineSkipped = scanner.nextLine();
            scanner.close();
            testFile.delete();
            assertEquals(aLine, lineSkipped);
        } catch (IOException e) {
            fail("File not found");
        }
    }

    @Test
    public void interpretLinesTest() {
        String directory = "./testFile3.txt";
        File testFile = new File(directory);
        String aLine = "1. | [ ]  | testItem | Qty: 1 unit | Cat: interpretLines | BuyPrice: $1.00 | SellPrice: $1.00";
        try {
            Storage.writeToFile(directory, aLine, true);
            Scanner scanner = new Scanner(testFile);
            interpretLines(scanner);
            scanner.close();
        } catch (IOException e) {
            fail("File not found");
        } catch (NumberFormatException e) {
            fail("Incorrect number format");
        }
        assert testFile.delete();
        Item itemAdded = Itemlist.getItem(Itemlist.getItems().size() - 1);
        Itemlist.deleteItem(Itemlist.getIndex(Itemlist.getItem("testItem")));
        assertEquals("testItem", itemAdded.getItemName());
    }

    @Test
    public void getFileDirectory_correct() {
        assertEquals(Storage.getFileDirectory(), "./StockMasterData.txt");
    }
}
