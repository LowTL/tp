package command;

import org.junit.jupiter.api.Test;

public class IncorrectCommandTest {

    private static final IncorrectCommand incorrectCommand = new IncorrectCommand();

    @Test
    public void incorrectCommandTest() {
        incorrectCommand.execute();
    }
}
