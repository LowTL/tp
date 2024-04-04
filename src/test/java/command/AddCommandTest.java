package command;

import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {

    @Test
    public void addItemTest() throws CommandFormatException {
        try {
            Command addCommandTest1 = new AddCommand("testItem", 1, "EA",
                    "NA", 1, 10);
            Command addCommandTest2 = new AddCommand("testItem", 7, "EA",
                    "NA", 1, 10);
            addCommandTest1.execute();
            addCommandTest2.execute();
        } catch (CommandFormatException e) {
            fail("Unable to add item.");
        } catch (InvalidDateException e) {
            throw new RuntimeException(e);
        }
    }

}
