package command;

import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;

import java.util.logging.Logger;

public abstract class Command  {

    protected static final Logger LOGGER = Logger.getLogger(Command.class.getName());
    public abstract void execute() throws CommandFormatException, InvalidDateException, EmptyListException;

}
