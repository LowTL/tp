package seedu.duke;

import command.Command;
import command.ExitCommand;
import command.LowStockCommand;
import exceptions.CommandFormatException;
import exceptions.EmptyListException;
import exceptions.InvalidDateException;
import itemlist.Cashier;
import parser.Parser;
import storage.PromotionStorage;
import storage.Storage;
import storage.TransactionLogs;
import ui.TextUi;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class StockMaster {
    private static final String STORAGE_FILE = "./StockMasterData.txt";
    private static final String TRANSACTION_FILE = "./TransactionLogs.txt";
    private static final String PROMOTION_STORAGE_FILE = "./PromotionStorage.txt";
    private static final Logger logger = Logger.getLogger(StockMaster.class.getName());
    private final TextUi ui = new TextUi();
    private final Parser parser = new Parser();



    /**
     * Main entry-point for the java.duke.StockMaster application.
     */
    public static void main(String[] args) throws IOException, CommandFormatException,
            InvalidDateException, EmptyListException {
        new StockMaster().run();
    }

    public void run() throws IOException, CommandFormatException, InvalidDateException, EmptyListException {
        initLogger();
        logger.finest("Run begin");
        ui.showWelcomeMessage("StockMaster v2.0", STORAGE_FILE);
        Storage.updateFile("", true);
        Storage.readFromFile(STORAGE_FILE);
        TransactionLogs.updateFile("", true);
        TransactionLogs.readFromFile(TRANSACTION_FILE);
        PromotionStorage.updateFile("", true);
        PromotionStorage.readFromFile(PROMOTION_STORAGE_FILE);
        new LowStockCommand().execute();
        this.normalOperation();
        ui.showGoodByeMessage(STORAGE_FILE, TRANSACTION_FILE, PROMOTION_STORAGE_FILE);
    }

    private static void initLogger() {
        Logger parserLogger = Logger.getLogger(Parser.class.getName());
        Logger commandLogger = Logger.getLogger(Command.class.getName());
        Logger cashierLogger = Logger.getLogger(Cashier.class.getName());
        Logger storageLogger = Logger.getLogger(Storage.class.getName());
        LogManager.getLogManager().reset(); //clears out any default settings
        ConsoleHandler ch = new ConsoleHandler(); //to print errors to console
        logger.addHandler(ch);
        logger.setLevel(Level.ALL); //to allow all logs to be logged
        ch.setLevel(Level.SEVERE); //only print severe logs to console
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileHandler fh = new FileHandler("logs/StockMasterLogs.log");
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.INFO); //log info and above logs
            logger.addHandler(fh);
            parserLogger.addHandler(fh);
            commandLogger.addHandler(fh);
            cashierLogger.addHandler(fh);
            storageLogger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to create FileHandler", e);
        }
    }

    private void normalOperation() throws CommandFormatException,
            InvalidDateException, EmptyListException {
        String userInput;
        do {
            userInput = TextUi.getUserInput();
            logger.info("Input received: " + userInput);
            Command command = parser.parseInput(userInput);
            command.execute();
        } while (!ExitCommand.getIsExit());
    }

}
