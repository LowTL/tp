package seedu.duke;

import command.Command;
import command.ExitCommand;
import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import parser.Parser;
import storage.Storage;
import storage.TransactionLogs;
import ui.TextUi;

import itemlist.Itemlist;

import java.io.IOException;

public class Duke {
    private final TextUi ui = new TextUi();
    private final Parser parser = new Parser();
    private Itemlist itemlist = new Itemlist();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws IOException, CommandFormatException, InvalidDateException {
        new Duke().run();
    }

    public void run() throws IOException, CommandFormatException, InvalidDateException {
        ui.showWelcomeMessage("StockMaster v2.0", "./StockMasterData.txt");
        Storage.updateFile("", true);
        Storage.readFromFile("./StockMasterData.txt");
        TransactionLogs.updateFile("", true);
        TransactionLogs.readFromFile("./TransactionLogs.txt");
        this.normalOperation();
        ui.showGoodByeMessage("./StockMasterData.txt");
    }

    private void normalOperation() throws IOException, CommandFormatException, InvalidDateException {
        String userInput;
        do {
            userInput = ui.getUserInput();
            Command command = parser.parseInput(userInput);
            command.execute();
        } while (!ExitCommand.getIsExit());
    }

}
