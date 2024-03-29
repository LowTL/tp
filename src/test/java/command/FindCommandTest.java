package command;

import exceptions.CommandFormatException;
import itemlist.Itemlist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FindCommandTest {

    @Test
    public void findCommandTest() throws CommandFormatException {
        Command addCommandTest1 = new AddCommand("testItem", 1, "EA",
                "NA", 1, 10);
        Command findCommand = new FindCommand("testItem");
        Command findCommand2 = new FindCommand("failFindCommand");

        addCommandTest1.execute();
        findCommand.execute();
        assert(Itemlist.itemIsExist("testItem"));
        assert(!Itemlist.itemIsExist("failFindCommand"));
        findCommand2.execute();
        fail("Item failFindCommand does not exist.");
    }
}
