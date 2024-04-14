package storage;

import item.Item;
import item.Transaction;
import itemlist.Cashier;
import itemlist.Itemlist;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static storage.Storage.interpretLines;

public class TransactionLogsTest {
    @Test
    public void interpretLinesTest() {
        String directory = "./testFile.txt";
        File testFile = new File(directory);
        String aTransaction = "Date: 2024-04-14 15:25:47\n" +
                "Transaction ID: 1\n" +
                "Item Name: testItem\n" +
                "Quantity: 10\n" +
                "Unit Price: 20.0\n" +
                "Total Price: 200.0\n" +
                "Profit: 100.0";
        try {
            TransactionLogs.writeToFile(directory, aTransaction, true);
            Scanner scanner = new Scanner(testFile);
            TransactionLogs.interpretLines(scanner);
            scanner.close();
        } catch (IOException e) {
            fail("File not found");
        } catch (NumberFormatException e) {
            fail("Incorrect number format");
        }
        assert testFile.delete();
        Transaction transactionAdded = Cashier.getTransaction(Cashier.getTransactions().size() - 1);
        Cashier.deleteItem(Cashier.getTransactions().size() - 1);
        assertEquals(200.0, transactionAdded.getTotalPrice());
    }
}
