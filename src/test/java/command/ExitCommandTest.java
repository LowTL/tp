package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    @Test
    public void testGetIsExit() {
        ExitCommand exitCommand = new ExitCommand(true);
        assertTrue(ExitCommand.getIsExit());
    }

    @Test
    public void testExecute() {
        ExitCommand exitCommand = new ExitCommand(false);
        exitCommand.execute();
        assertFalse(ExitCommand.getIsExit());
    }
}
