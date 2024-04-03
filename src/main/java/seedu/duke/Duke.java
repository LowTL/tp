package seedu.duke;

import command.Command;
import command.ExitCommand;
import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import parser.Parser;
import reminder.LowStockReminder;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;
import ui.TextUi;

import itemlist.Itemlist;

import java.io.IOException;

public class Duke {
    private static final String STORAGE_FILE = "./StockMasterData.txt";
    private static final String TRANSACTION_FILE = "./TransactionLogs.txt";
    private static final String PROMOTION_STORAGE_FILE = "./PromotionStorage.txt";
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
        ui.showWelcomeMessage("StockMaster v2.0", STORAGE_FILE);
        Storage.updateFile("", true);
        Storage.readFromFile(STORAGE_FILE);
        TransactionLogs.updateFile("", true);
        TransactionLogs.readFromFile(TRANSACTION_FILE);
        PromotionStorage.updateFile("", true);
        PromotionStorage.readFromFile(PROMOTION_STORAGE_FILE);
        LowStockReminder.execute();
        this.normalOperation();
        ui.showGoodByeMessage(STORAGE_FILE, TRANSACTION_FILE, PROMOTION_STORAGE_FILE);
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
