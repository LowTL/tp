package storage;

import item.Transaction;
import itemlist.Cashier;
import org.junit.jupiter.api.Test;
import promotion.Promotion;
import promotion.Promotionlist;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class PromotionStorageTest {
    @Test
    public void readFromFileTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String directory = "./testFile.txt";
        File testFile = new File(directory);
        String aPromotion = "testItem have a 30.00% discount\n" +
                "Period: 15 MAR 2024 to 16 MAY 2024\n" +
                "Time: 1200 to 2359";
        try {
            PromotionStorage.writeToFile(directory, aPromotion, true);
            PromotionStorage.readFromFile(directory);
        } catch (IOException e) {
            fail("File not found");
        }
        assert testFile.delete();
        assertNotEquals("Read Promotion File Error", outputStream.toString());
    }
}
