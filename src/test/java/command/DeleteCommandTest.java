package command;

import exceptions.CommandFormatException;
import itemlist.Itemlist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    Command addCommandTest1 = new AddCommand("testItem", 1, "EA",
            "NA", 1, 10);
    Command deleteCommand = new DeleteCommand("testItem");

    @Test
    public void delCommandTest_success() throws CommandFormatException {
        try {
            addCommandTest1.execute();
            deleteCommand.execute();
            assert(!Itemlist.itemIsExist("testItem"));
            deleteCommand.execute();
        } catch (CommandFormatException e) {
            fail("Unable to delete.");
        }

    }
}
