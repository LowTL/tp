package command;

import exceptions.CommandFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class SellCommandTest {

    @Test
    public void sellItemTest() throws CommandFormatException {
        try {
            Command sellCommandTest1 = new SellCommand("testItem", 1, 3);
            Command sellCommandTest2 = new SellCommand("testItem", 7, 14);
            sellCommandTest1.execute();
            sellCommandTest2.execute();
        } catch (CommandFormatException e) {
            fail("Unable to sell item.");
        }
    }

}