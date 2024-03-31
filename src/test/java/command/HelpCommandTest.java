package command;

import common.Messages;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void testExecute() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute();
        String expectedOutput = Messages.HELP + System.lineSeparator();
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
