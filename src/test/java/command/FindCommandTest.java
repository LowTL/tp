package command;

import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import itemlist.Itemlist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {

    @Test
    public void findCommandTest() throws CommandFormatException, InvalidDateException, EmptyListException {
        Command addCommandTest1 = new AddCommand("testItem", 1, "EA",
                "NA", 1, 10);
        Command findCommand = new FindCommand("item","testItem");
        Command findCommand2 = new FindCommand("NA","failFindCommand");
        addCommandTest1.execute();
        findCommand.execute();
        findCommand2.execute();
        assertFalse(Itemlist.itemIsExist("failFindCommand"));
    }
}
