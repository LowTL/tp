//@@author HengShuHong
package storage;

import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import promotion.Month;
import promotion.Promotion;
import promotion.Promotionlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Represents a class that stores and writes information of a list of promotions existed to a file.
 * String <code>PROMOTIONSTORAGE</code> represents the designated relative file path for the file.
 */
public class PromotionStorage extends Storage{

    private static final String PRMOTIONSTORAGE = "./PromotionStorage.txt";

    public static String getFileDirectory() {
        return PRMOTIONSTORAGE;
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(getFileDirectory(), inputText, ifAppend);
            LOGGER.info("Stored promotion.");
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
            LOGGER.warning(e.toString());
        }
    }

    /**
     * Read lines from the file and identify promotions written inside.
     * Add the identified promotions into a list of existing promotions.
     *
     * @param fileName Name of the file to read from.
     */
    public static void readFromFile(String fileName) {
        int count = 0;
        String itemName = "";
        float discount = 0;
        int startDate = 0;
        int startYear = 0;
        int endDate = 0;
        int endYear = 0;
        Month startMonth = null;
        Month endMonth = null;
        int startTime = 0;
        int endTime = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                switch (count) {
                case (0):
                    String[] itemNameParts = fileLine.split(" have a ");
                    itemName = itemNameParts[0];
                    discount = Float.parseFloat(itemNameParts[1].split("%")[0]) / 100;
                    count = count + 1;
                    break;
                case (1):
                    String[] periodParts = fileLine.split("Period: ");
                    String[] startEndPeriod = periodParts[1].split(" to ");
                    String[] getStart = startEndPeriod[0].split(" ");
                    String[] getEnd = startEndPeriod[1].split(" ");
                    startDate = Integer.parseInt(getStart[0]);
                    startMonth = Month.valueOf(getStart[1]);
                    startYear = Integer.parseInt(getStart[2]);
                    endDate = Integer.parseInt(getEnd[0]);
                    endMonth = Month.valueOf(getEnd[1]);
                    endYear = Integer.parseInt(getEnd[2]);
                    count = count + 1;
                    break;
                case(2):
                    String[] getTime = fileLine.split("Time: ");
                    String[] startEndTime = getTime[1].split(" to ");
                    startTime = Integer.parseInt(startEndTime[0]);
                    endTime = Integer.parseInt(startEndTime[1]);
                    Promotion toAdd = new Promotion(itemName, discount, startDate, startMonth, startYear,
                            endDate, endMonth, endYear, startTime, endTime);
                    Promotionlist.addPromotion(toAdd);
                    count = 0;
                    break;
                default:
                    System.out.println("Read Promotion File Error");
                    LOGGER.warning("Promotions not read from file.");
                }
            }
            scanner.close();
            LOGGER.info("Promotions successfully read from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            LOGGER.warning("File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid numbers found.");
            LOGGER.warning("Invalid numbers found.");
        } catch (InvalidDateException | CommandFormatException e) {
            LOGGER.log(Level.WARNING, "Other exception occurred.", e);
            System.out.println(e);;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(fileName + " is not written in a correct format.");
            LOGGER.warning("Incorrect documentation format.");
        }
        scanner.close();
    }

    /**
     * Writes to the indicated file and overwrite previous data
     *
     * @param promotions List of promotions with relevant details to write
     */
    public static void overwritePromotionFile(ArrayList<Promotion> promotions) {
        assert promotions != null : "Promotions cannot be null.";
        int length = promotions.size();
        for (Promotion promotion: promotions) {
            String descriptionAdded = (promotion.getItemName() + " have a " +
                    String.format("%.2f", (promotion.getDiscount()*100)) + "% discount" + "\n" + "Period: " +
                    promotion.getStartDay() + " " + promotion.getStartMonth() + " " + promotion.getStartYear() +
                    " to " + promotion.getEndDay()+ " " + promotion.getEndMonth() + " " + promotion.getEndYear() +
                    "\n" + "Time: " + promotion.getStartTime() + " to " + promotion.getEndTime() + "\n");
            if (promotions.indexOf(promotion) == 0) {
                updateFile(descriptionAdded, false);
            } else {
                updateFile(descriptionAdded, true);
            }
        }
    }
}
