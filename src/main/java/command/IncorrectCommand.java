package command;


public class IncorrectCommand extends Command {

    public IncorrectCommand() {
        LOGGER.warning("Incorrect Command received.");
    }

    @Override
    public void execute() {
    }

}
