package command;

import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;

public abstract class Command  {

    public abstract void execute() throws CommandFormatException, InvalidDateException, EmptyListException;
}
