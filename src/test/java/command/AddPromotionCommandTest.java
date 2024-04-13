package command;

import common.Messages;
import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;
import promotion.Month;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddPromotionCommandTest {

    @Test
    public void addPromotionCommandTest1() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest1 = new AddCommand("apple", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest1 = new AddPromotionCommand("apple", 0.30F, 29, Month.valueOf("FEB"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            addCommandTest1.execute();
            promotionTest1.execute();
            String expectedOutput1 = "added: apple (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    "The following promotion has been added" + System.lineSeparator() +
                    "apple have a 30.00% discount" + System.lineSeparator() +
                    "Period: 29 FEB 2024 to 4 APR 2024" + System.lineSeparator() +
                    "Time: 0000 to 1100" + System.lineSeparator();
            assertEquals(expectedOutput1, outputStreamCaptor.toString());
        }  catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addPromotionCommandTest2() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest2 = new AddCommand("durian", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest2 = new AddPromotionCommand("durian", 0.30F, 2, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            addCommandTest2.execute();
            promotionTest2.execute();
            String expectedOutput2 = "added: durian (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    "The following promotion has been added" + System.lineSeparator() +
                    "durian have a 30.00% discount" + System.lineSeparator() +
                    "Period: 2 APR 2024 to 4 APR 2024" + System.lineSeparator() +
                    "Time: 0000 to 1100" + System.lineSeparator();
            assertEquals(expectedOutput2, outputStreamCaptor.toString());
        }  catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addPromotionCommandTest3() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest3 = new AddCommand("mango", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest3 = new AddPromotionCommand("mango", 0.30F, 29, Month.valueOf("FEB"),
                    2024, 4, Month.valueOf("APR"), 2026, 0000, 1100);
            addCommandTest3.execute();
            promotionTest3.execute();
            String expectedOutput3 = "added: mango (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    "The following promotion has been added" + System.lineSeparator() +
                    "mango have a 30.00% discount" + System.lineSeparator() +
                    "Period: 29 FEB 2024 to 4 APR 2026" + System.lineSeparator() +
                    "Time: 0000 to 1100" + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        }  catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void itemUnavailablePromotionCommandTest() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command promotionTest1 = new AddPromotionCommand("random", 0.30F, 2, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            promotionTest1.execute();
            String expectedOutput3 =
                    Messages.ITEM_NOT_FOUND + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        }  catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidDiscountPromotionCommandTest() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest1 = new AddCommand("banana", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest1 = new AddPromotionCommand("banana", 100F, 2, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            Command promotionTest2 = new AddPromotionCommand("banana", -1F, 2, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            addCommandTest1.execute();
            promotionTest1.execute();
            promotionTest2.execute();
            String expectedOutput3 = "added: banana (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    Messages.INVALID_DISCOUNT + System.lineSeparator() +
                    Messages.INVALID_DISCOUNT + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        }  catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidTimePromotionCommandTest() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest1 = new AddCommand("lemon", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest1 = new AddPromotionCommand("lemon", 0.3F, 29, Month.valueOf("FEB"),
                    2024, 4, Month.valueOf("APR"), 2024, -1000, 1100);
            Command promotionTest2 = new AddPromotionCommand("lemon", 0.3F, 1, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 1200, 1100);
            addCommandTest1.execute();
            promotionTest1.execute();
            promotionTest2.execute();
            String expectedOutput3 = "added: lemon (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    Messages.INVALID_TIME + System.lineSeparator() +
                    Messages.INVALID_TIME + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        } catch (InvalidDateException | CommandFormatException e) {
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidDatePromotionCommandTest() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest1 = new AddCommand("cherry", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest1 = new AddPromotionCommand("cherry", 0.3F, 30, Month.valueOf("FEB"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            Command promotionTest2 = new AddPromotionCommand("cherry", 0.3F, -1, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            addCommandTest1.execute();
            promotionTest1.execute();
            promotionTest2.execute();
            String expectedOutput3 = "added: cherry (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    Messages.INVALID_DATE + System.lineSeparator() +
                    Messages.INVALID_DATE + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        } catch (InvalidDateException | CommandFormatException e) {
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void invalidDurationPromotionCommandTest() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command addCommandTest1 = new AddCommand("orange", 1, "fruits",
                    "NA", 1, 1);
            Command promotionTest1 = new AddPromotionCommand("orange", 0.3F, 4, Month.valueOf("APR"),
                    2024, 2, Month.valueOf("APR"), 2024, 0000, 1100);
            Command promotionTest2 = new AddPromotionCommand("orange", 0.3F, 4, Month.valueOf("APR"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 2300);
            Command promotionTest3 = new AddPromotionCommand("orange", 0.3F, 4, Month.valueOf("DEC"),
                    2024, 5, Month.valueOf("APR"), 2024, 0000, 2300);
            Command promotionTest4 = new AddPromotionCommand("orange", 0.3F, 4, Month.valueOf("APR"),
                    2025, 5, Month.valueOf("APR"), 2024, 0000, 2300);
            addCommandTest1.execute();
            promotionTest1.execute();
            promotionTest2.execute();
            promotionTest3.execute();
            promotionTest4.execute();
            String expectedOutput3 = "added: orange (Qty: 1 fruits, Buy: $1.00, Sell: $1.00)" + System.lineSeparator() +
                    Messages.INVALID_PERIOD + System.lineSeparator() +
                    Messages.INVALID_PERIOD + System.lineSeparator() +
                    Messages.INVALID_PERIOD + System.lineSeparator() +
                    Messages.INVALID_PERIOD + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        } catch (InvalidDateException | CommandFormatException e) {
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void itemIsOnPromoPromotionCommandTest() {
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            Command promotionTest1 = new AddPromotionCommand("apple", 0.3F, 5, Month.valueOf("FEB"),
                    2024, 4, Month.valueOf("APR"), 2024, 0000, 1100);
            promotionTest1.execute();
            String expectedOutput3 =
                    Messages.ITEM_IS_PROMO + System.lineSeparator();
            assertEquals(expectedOutput3, outputStreamCaptor.toString());
        } catch (InvalidDateException | CommandFormatException e) {
            System.out.print("");
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }
}
